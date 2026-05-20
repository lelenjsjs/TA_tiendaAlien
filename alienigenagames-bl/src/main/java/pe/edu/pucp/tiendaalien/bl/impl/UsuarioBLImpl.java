package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IUsuarioBL;
import pe.edu.pucp.tiendaalien.dao.UsuarioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UsuarioDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;

import java.util.List;

public class UsuarioBLImpl implements IUsuarioBL {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    // Create
    @Override
    public Usuario registrarUsuario(Usuario u) {
        return usuarioDAO.save(u);
    }

    // Read
    @Override
    public Usuario cargarUsuarioPorId(Integer id) {
        return usuarioDAO.loadById(id);
    }

    @Override
    public Usuario cargarUsuarioPorEmail(String email) {
        return usuarioDAO.loadByEmail(email);
    }

    // Update
    @Override
    public Usuario modificarUsuario(Usuario u) {
        return usuarioDAO.update(u);
    }

    // Delete
    @Override
    public void eliminarUsuario(Usuario u) {
        usuarioDAO.remove(u);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listAll();
    }
}