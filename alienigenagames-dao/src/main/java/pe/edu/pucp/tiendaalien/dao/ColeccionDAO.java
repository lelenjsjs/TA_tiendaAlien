package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;
import java.util.List;

public interface ColeccionDAO extends BaseDAO<Coleccion, Integer> {
    List<Coleccion> listAll();
}