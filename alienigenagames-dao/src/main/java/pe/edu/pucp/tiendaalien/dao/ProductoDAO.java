package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO extends BaseDAO<Producto,Integer> {
    List<Producto> listAll() throws SQLException;
}
