package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.pedido.HistorialEstadoPedido;
import java.util.List;

public interface IHistorialEstadoPedidoBL {
    // Es clave filtrar por pedido_id en lugar de traer toda la tabla
    List<HistorialEstadoPedido> listarHistorialPorPedido(Integer idPedido) throws BusinessLogicException;
    HistorialEstadoPedido cargarHistorialPorId(Integer id) throws BusinessLogicException;
    HistorialEstadoPedido registrarHistorial(HistorialEstadoPedido historial) throws BusinessLogicException;
    HistorialEstadoPedido modificarHistorial(HistorialEstadoPedido historial) throws BusinessLogicException;
    void eliminarHistorial(HistorialEstadoPedido historial) throws BusinessLogicException;
}