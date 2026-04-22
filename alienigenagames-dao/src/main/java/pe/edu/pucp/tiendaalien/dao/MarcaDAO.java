package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Marca;
import java.util.List;

public interface MarcaDAO extends BaseDAO<Marca, Integer> {
    Marca load(Integer id);
    Marca save(Marca t);
    Marca update(Marca t);
    void remove(Marca t);
    List<Marca> listAll();
}