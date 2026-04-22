package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.UsuarioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol; // Asumiendo que tienes un Enum Rol

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public List<Usuario> listAll() {
        List<Usuario> list = new ArrayList<>();
        String sql = "SELECT usuario_id, nombres, apellidos, email, contra_hash, celular, rol, fec_creacion FROM usuario";

        try (Connection connection = DBManager.getInstance().getConnection();
             Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = mapResultSetToUsuario(rs);
                list.add(usuario);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar usuarios", e);
        }
    }

    @Override
    public Usuario load(Integer id) {
        String sql = "SELECT usuario_id, nombres, apellidos, email, contra_hash, celular, rol, fec_creacion FROM usuario WHERE usuario_id = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar usuario con ID: " + id, e);
        }
        return null;
    }

    @Override
    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombres, apellidos, email, contra_hash, celular, rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNombres());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getContraHash());
            pstmt.setString(5, usuario.getCelular());
            // Se asume que el Enum Rol en Java coincide con el ENUM de MySQL
            pstmt.setString(6, usuario.getRol().name());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setUsuarioId(generatedKeys.getInt(1));
                    }
                }
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar usuario", e);
        }
    }

    @Override
    public Usuario update(Usuario usuario) {
        String sql = "UPDATE usuario SET nombres = ?, apellidos = ?, email = ?, contra_hash = ?, celular = ?, rol = ? WHERE usuario_id = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombres());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getContraHash());
            pstmt.setString(5, usuario.getCelular());
            pstmt.setString(6, usuario.getRol().name());
            pstmt.setInt(7, usuario.getUsuarioId());

            pstmt.executeUpdate();
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    @Override
    public void remove(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE usuario_id = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, usuario.getUsuarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    // Método auxiliar para no repetir código de mapeo
    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(rs.getInt("usuario_id"));
        usuario.setNombres(rs.getString("nombres"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setEmail(rs.getString("email"));
        usuario.setContraHash(rs.getString("contra_hash"));
        usuario.setCelular(rs.getString("celular"));
        // Mapeo del String de la BD al Enum de Java
        usuario.setRol(Rol.valueOf(rs.getString("rol")));
        usuario.setFechaCreacion(rs.getTimestamp("fec_creacion"));
        return usuario;
    }
}