package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import java.util.List;

public interface UbigeoDAO extends BaseDAO<Ubigeo, Integer> {
    Ubigeo load(Integer id);
    Ubigeo save(Ubigeo t);
    Ubigeo update(Ubigeo t);
    void remove(Ubigeo t);
    List<Ubigeo> listAll();
}
