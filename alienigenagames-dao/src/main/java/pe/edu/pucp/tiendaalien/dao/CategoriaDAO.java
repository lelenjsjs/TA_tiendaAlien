package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Categoria;
import java.util.List;

public interface CategoriaDAO extends BaseDAO<Categoria, Integer> {
    Categoria load(Integer id);
    Categoria save(Categoria t);
    Categoria update(Categoria t);
    void remove(Categoria t);
    List<Categoria> listAll();
}