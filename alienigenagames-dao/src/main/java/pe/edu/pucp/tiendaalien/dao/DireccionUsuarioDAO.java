package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;

import java.util.List;

public interface DireccionUsuarioDAO extends BaseDAO<DireccionUsuario, Integer> {
    DireccionUsuario load(Integer id);
    DireccionUsuario save(DireccionUsuario t);
    DireccionUsuario update(DireccionUsuario t);
    void remove(DireccionUsuario t);
    List<DireccionUsuario> listAll();
}
