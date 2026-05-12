package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.pedido.DetallePedido;
import java.util.List;

public interface IDetallePedidoBL {
    List<DetallePedido> listarDetallesPorPedido(Integer idPedido) throws BusinessLogicException;
    DetallePedido cargarDetallePorId(Integer id) throws BusinessLogicException;
    DetallePedido registrarDetalle(DetallePedido detalle) throws BusinessLogicException;
    DetallePedido modificarDetalle(DetallePedido detalle) throws BusinessLogicException;
    void eliminarDetalle(DetallePedido detalle) throws BusinessLogicException;
}