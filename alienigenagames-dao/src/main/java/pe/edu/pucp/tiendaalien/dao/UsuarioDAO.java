package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;

import java.util.List;

// T representa la Clase del Modelo (la entidad)
// ID representa el Tipo de dato de la llave primaria (normalmente Integer para los ID autoincrementales)
public interface UsuarioDAO extends BaseDAO<Usuario, Integer> {
    Usuario loadByEmail(String email);
    List<Usuario> listAll();
}
