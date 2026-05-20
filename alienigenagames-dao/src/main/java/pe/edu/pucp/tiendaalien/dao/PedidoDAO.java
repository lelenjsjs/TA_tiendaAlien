package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public interface PedidoDAO extends BaseDAO<Pedido,Integer> {
    Pedido save(Pedido pedido, Connection con) throws SQLException;
    List<Pedido> listAll();
}
