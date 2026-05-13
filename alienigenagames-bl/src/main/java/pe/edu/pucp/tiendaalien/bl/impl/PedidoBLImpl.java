package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IPedidoBL;
import pe.edu.pucp.tiendaalien.dao.PedidoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.PedidoDAOImpl;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

public class PedidoBLImpl implements IPedidoBL {
    private PedidoDAO pedidoDAO = new PedidoDAOImpl();
    @Override
    public Pedido registrarPedido(Pedido pedido) {
        return pedidoDAO.save(pedido);
    }

    @Override
    public Pedido modificarPedido(Pedido pedido) {
        return null;
    }

    @Override
    public Pedido eliminarPedido(Pedido pedido) {
        return null;
    }

    @Override
    public Pedido obtenerPedidoPorId(Integer id) {
        return null;
    }
}
