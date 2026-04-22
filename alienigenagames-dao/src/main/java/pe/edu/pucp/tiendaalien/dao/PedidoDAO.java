package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.util.List;
public interface PedidoDAO extends BaseDAO<Pedido,Integer> {
    Pedido load(Integer id);
    Pedido save(Pedido t);
    Pedido update(Pedido t);
    void remove(Pedido t);
    List<Pedido> listAll();
}
