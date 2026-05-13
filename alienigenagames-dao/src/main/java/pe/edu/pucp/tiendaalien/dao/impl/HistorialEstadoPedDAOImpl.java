package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IDetallePedidoBL;
import pe.edu.pucp.tiendaalien.dao.DetallePedidoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.DetallePedidoDAOImpl;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;

import java.util.ArrayList;
import java.util.List;

public class DetallePedidoBLImpl implements IDetallePedidoBL {

    private DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();

    @Override
    public List<DetallePed> listarDetallesPorPedido(Integer idPedido) throws BusinessLogicException {
        if (idPedido == null || idPedido <= 0) {
            throw new BusinessLogicException("Se requiere un ID de pedido válido para listar sus detalles.");
        }
        // Se corrigió DetallePedido a DetallePed
        List<DetallePed> lista = detallePedidoDAO.listByPedido(idPedido);
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public DetallePed cargarDetallePorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID del detalle no es válido.");
        }
        // Se corrigió DetallePedido a DetallePed
        DetallePed detalle = detallePedidoDAO.load(id);
        return (detalle != null) ? detalle : new DetallePed();
    }

    @Override
    public DetallePed registrarDetalle(DetallePed detalle) throws BusinessLogicException {
        // false = Nuevo registro
        validar(detalle, false);
        return detallePedidoDAO.save(detalle);
    }

    @Override
    public DetallePed modificarDetalle(DetallePed detalle) throws BusinessLogicException {
        // true = Edición
        validar(detalle, true);
        return detallePedidoDAO.update(detalle);
    }

    @Override
    public void eliminarDetalle(DetallePed detalle) throws BusinessLogicException {
        if (detalle == null || detalle.getDetallePedId() == null || detalle.getDetallePedId() <= 0) {
            throw new BusinessLogicException("Debe especificar un detalle válido para eliminar.");
        }
        detallePedidoDAO.remove(detalle);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES (REGLAS DE NEGOCIO)
    // =========================================================================
    // Se corrigió el parámetro de DetallePedido a DetallePed
    private void validar(DetallePed d, boolean esModificacion) throws BusinessLogicException {
        if (d == null) {
            throw new BusinessLogicException("El detalle del pedido no puede ser nulo.");
        }

        // 1. Validar ID en caso de modificación
        if (esModificacion && (d.getDetallePedId() == null || d.getDetallePedId() <= 0)) {
            throw new BusinessLogicException("El ID del detalle es obligatorio para modificar.");
        }

        // 2. Validar Relaciones (Foreign Keys)
        if (d.getPedido() == null || d.getPedido().getPedidoId() == null || d.getPedido().getPedidoId() <= 0) {
            throw new BusinessLogicException("El detalle debe estar asociado a un Pedido válido.");
        }

        if (d.getProducto() == null || d.getProducto().getProductoId() == null || d.getProducto().getProductoId() <= 0) {
            throw new BusinessLogicException("El detalle debe estar asociado a un Producto válido.");
        }

        // 3. Validar Cantidad (Regla: Siempre debe ser mayor a 0)
        if (d.getCantidad() == null || d.getCantidad() <= 0) {
            throw new BusinessLogicException("La cantidad del producto debe ser mayor a cero.");
        }

        // 4. Validar Precio Congelado (Regla: No puede ser negativo)
        if (d.getPrecioUnitarioCongelado() == null || d.getPrecioUnitarioCongelado() < 0) {
            throw new BusinessLogicException("El precio unitario no puede ser negativo.");
        }
    }
}