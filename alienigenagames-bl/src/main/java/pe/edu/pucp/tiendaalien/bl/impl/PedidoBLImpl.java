package pe.edu.pucp.tiendaalien.bl.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.bl.IPedidoBL;
import pe.edu.pucp.tiendaalien.dao.*;
import pe.edu.pucp.tiendaalien.dao.impl.*;

// Importaciones de Modelos (Ajusta según tus paquetes reales)
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogistica;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogisticaEnvio;
import pe.edu.pucp.tiendaalien.model.ventas.*;

public class PedidoBLImpl implements IPedidoBL {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private DetallePedDAO detalleDAO = new DetallePedDAOImpl();
    private ProductoDAO productoDAO = new ProductoDAOImpl();
    private HistorialEstadoPedDAO historialDAO = new HistorialEstadoPedDAOImpl();
    private PedLogisticaDAO logisticaDAO = new PedLogisticaDAOImpl();
    private PedLogisticaEnvioDAO envioDAO = new PedLogisticaEnvioDAOImpl();

    @Override
    public Pedido registrarPedidoCompleto(Pedido pedido, PedLogisticaEnvio envio) throws Exception {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false); // BLOQUEO PARA TRANSACCIÓN

            // 1. VALIDACIÓN DE STOCK
            for (DetallePed det : pedido.getDetalles()) {
                Producto pBD = productoDAO.loadById(det.getProducto().getProductoId());
                if (pBD == null) throw new Exception("Producto no encontrado: ID " + det.getProducto().getProductoId());

                if (pBD.getStock() < det.getCantidad()) {
                    throw new Exception("Stock insuficiente para: " + pBD.getNombre() +
                            " (Disponible: " + pBD.getStock() + ", Solicitado: " + det.getCantidad() + ")");
                }
            }

            // 2. GUARDAR CABECERA (PEDIDO)
            // El DAO actualiza el objeto 'pedido' con el ID de la base de datos
            pedido = pedidoDAO.save(pedido, con);

            // 3. GUARDAR DETALLES Y DESCONTAR STOCK
            for (DetallePed det : pedido.getDetalles()) {
                det.setPedido(pedido); // Asignamos el pedido ya con su ID
                detalleDAO.save(det, con);

                // Descontamos stock: pasamos la cantidad como NEGATIVO
                productoDAO.actualizarStock(det.getProducto().getProductoId(), -det.getCantidad(), con);
            }

            // 4. GUARDAR HISTORIAL INICIAL
            HistorialEstadoPed historial = new HistorialEstadoPed();
            historial.setPedido(pedido);
            historial.setEstado("Pendiente");
            historialDAO.save(historial, con);

            // 5. LOGÍSTICA (Solo si es WEB)
            if (pedido.getCanalVenta() == CanalVenta.WEB) {
                // Sincronizamos el pedido con la logística
                envio.setPedido(pedido);

                // Primero el PADRE (PedLogistica)
                // Usamos la misma instancia de 'envio' ya que hereda de PedLogistica
                PedLogistica logPadre = logisticaDAO.save(envio, con);

                // Segundo el HIJO (PedLogisticaEnvio)
                // El ID del hijo es el mismo que el del padre
                envio.setEnvioId(logPadre.getPedLogisticaId());
                envioDAO.save(envio, con);
            }

            con.commit(); // TODO SALIÓ BIEN, GUARDAMOS
            return pedido;

        } catch (Exception e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            throw e; // Relanzamos para que el Main capture el mensaje de error
        } finally {
            if (con != null) {
                try { con.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

    // --- MÉTODOS PENDIENTES ---
    @Override public Pedido modificarPedido(Pedido pedido) { return pedidoDAO.update(pedido); }
    @Override public void eliminarPedido(Pedido pedido) { pedidoDAO.remove(pedido); }
    @Override public Pedido obtenerPedidoPorId(Integer id) { return pedidoDAO.loadById(id); }
}