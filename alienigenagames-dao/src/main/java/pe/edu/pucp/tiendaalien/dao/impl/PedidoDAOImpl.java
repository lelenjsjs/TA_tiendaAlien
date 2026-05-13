package pe.edu.pucp.tiendaalien.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.PedidoDAO;
import pe.edu.pucp.tiendaalien.model.ventas.*;

public class PedidoDAOImpl implements PedidoDAO {

    /* --- MÉTODOS PARA LA TRANSACCIÓN (Reciben Connection) --- */

    /**
     * Inserta la cabecera del pedido usando una conexión externa.
     * Devuelve el objeto Pedido con el ID autogenerado.
     */
    @Override
    public Pedido save(Pedido pedido, Connection con) throws SQLException {
        // 1. Añadimos las columnas faltantes al SQL
        String sql = "INSERT INTO pedido (cod_pedido, canal_venta, subtotal, monto_total, " +
                "estado_pedido, usuario_id, fec_creacion, metodo_pago, estado_pago, " +
                "cliente_email, cliente_cel) " + // <--- Columnas nuevas
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?)"; // <--- Dos '?' más

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pedido.getCodPedido());
            ps.setString(2, pedido.getCanalVenta().name());
            ps.setDouble(3, pedido.getSubtotal());
            ps.setDouble(4, pedido.getMontoTotal());
            ps.setString(5, pedido.getEstadoPedido().name());
            ps.setInt(6, pedido.getUsuario().getUsuarioId());
            ps.setString(7, pedido.getMetodoPago().name());
            ps.setString(8, pedido.getEstadoPago().name());

            // 2. Seteamos el email y celular del cliente desde el objeto Usuario
            ps.setString(9, pedido.getUsuario().getEmail()); // <--- Corregido
            ps.setString(10, pedido.getUsuario().getCelular()); // <--- Corregido

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setIdPedido(rs.getInt(1));
                }
            }
        }
        return pedido;
    }

    /* --- MÉTODOS CRUD NORMALES (Gestionan su propia conexión) --- */

    @Override
    public Pedido save(Pedido pedido) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            return save(pedido, con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Pedido loadById(Integer id) {
        Pedido p = null;
        String sql = "SELECT * FROM pedido WHERE pedido_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Pedido();
                    // 1. Identificadores básicos
                    p.setIdPedido(rs.getInt("pedido_id"));
                    p.setCodPedido(rs.getString("cod_pedido"));
                    p.setFecCreacion(rs.getTimestamp("fec_creacion"));

                    // 2. Mapeo de ENUMS (Es vital usar valueOf para convertir String a Enum)
                    p.setCanalVenta(CanalVenta.valueOf(rs.getString("canal_venta")));
                    p.setMetodoPago(MetodoPago.valueOf(rs.getString("metodo_pago")));
                    p.setEstadoPago(EstadoPago.valueOf(rs.getString("estado_pago")));
                    p.setEstadoPedido(EstadoPedido.valueOf(rs.getString("estado_pedido")));

                    // 3. Datos de contacto y pasarela
                    p.setClienteEmail(rs.getString("cliente_email"));
                    p.setClienteCel(rs.getString("cliente_cel"));
                    p.setPasarelaTransaccionId(rs.getString("pasarela_transaccion_id"));

                    // 4. Montos (Double/double)
                    p.setSubtotal(rs.getDouble("subtotal"));
                    p.setCargoServicio(rs.getDouble("cargo_servicio"));
                    p.setMontoAdelanto(rs.getDouble("monto_adelanto"));
                    p.setMontoTotal(rs.getDouble("monto_total"));
                    // Rellenar demás campos...
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public Pedido update(Pedido p) {
        String sql = "UPDATE pedido SET estado_pedido = ? WHERE pedido_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getEstadoPedido().name());
            ps.setInt(2, p.getIdPedido());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public void remove(Pedido p) {
        // En pedidos usualmente se hace un borrado lógico o anulación de estado
        String sql = "UPDATE pedido SET estado_pedido = 'ANULADO' WHERE pedido_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdPedido());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Pedido> listAll() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("pedido_id"));
                p.setCodPedido(rs.getString("cod_pedido"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}