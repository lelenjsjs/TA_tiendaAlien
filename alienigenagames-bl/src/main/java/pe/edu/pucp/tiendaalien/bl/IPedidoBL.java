package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.logistica.PedLogisticaEnvio;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

public interface IPedidoBL {
    Pedido registrarPedidoCompleto(Pedido pedido, PedLogisticaEnvio envio) throws Exception;
    Pedido modificarPedido(Pedido pedido);
    void eliminarPedido(Pedido pedido);
    Pedido obtenerPedidoPorId(Integer id);
}
