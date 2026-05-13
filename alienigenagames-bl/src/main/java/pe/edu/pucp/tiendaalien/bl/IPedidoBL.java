package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

public interface IPedidoBL {
    Pedido registrarPedido(Pedido pedido);
    Pedido modificarPedido(Pedido pedido);
    Pedido eliminarPedido(Pedido pedido);
    Pedido obtenerPedidoPorId(Integer id);
}
