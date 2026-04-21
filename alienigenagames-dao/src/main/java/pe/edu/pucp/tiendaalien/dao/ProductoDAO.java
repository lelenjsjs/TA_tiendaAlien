package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.Producto;

import java.util.List;

public interface ProductoDAO extends BaseDAO<Producto,Integer> {
    List<Producto> listAll();
}
