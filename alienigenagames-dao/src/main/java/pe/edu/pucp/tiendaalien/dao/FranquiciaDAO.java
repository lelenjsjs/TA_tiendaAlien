package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;
import java.util.List;

public interface FranquiciaDAO extends BaseDAO<Franquicia, Integer> {

    List<Franquicia> listAll();
}