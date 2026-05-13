package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.util.List;
public interface PedidoDAO extends BaseDAO<Pedido,Integer> {
    List<Pedido> listAll();
}
