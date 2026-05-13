package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;

import java.util.List;

public interface IUsuarioBL {
    // Create
    Usuario registrarUsuario(Usuario u);

    // Read
    Usuario cargarUsuarioPorId(Integer id);
    Usuario cargarUsuarioPorEmail(String email);

    // Update
    Usuario modificarUsuario(Usuario u);

    // Delete
    void eliminarUsuario(Usuario u);

    // Listar todos
    List<Usuario> listarUsuarios();

}
