package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;
import java.util.List;

public interface FranquiciaDAO extends BaseDAO<Franquicia, Integer> {
    Franquicia load(Integer id);
    Franquicia save(Franquicia t);
    Franquicia update(Franquicia t);
    void remove(Franquicia t);
    List<Franquicia> listAll();
}