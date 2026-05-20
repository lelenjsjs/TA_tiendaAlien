package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.UsuarioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol; // Asumiendo que tienes un Enum Rol

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    // Create
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
            // El Enum Rol en Java coincide con el ENUM de MySQL
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

    // Read
    @Override
    public Usuario loadById(Integer id) {
        String sql = "SELECT usuario_id, nombres, apellidos, email, contra_hash, celular, rol, fec_creacion FROM usuario WHERE usuario_id = ? AND es_activo=1";

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
    public Usuario loadByEmail(String email) {
        String sqlUser = "SELECT usuario_id, nombres, apellidos, email, contra_hash, celular, rol, fec_creacion FROM usuario WHERE email = ? AND es_active=1";
        String sqlAddress = "SELECT direccion_id, usuario_id, ubigeo_id, direccion,principal,referencia,es_activo FROM direccion_usuario WHERE usuario_id = ?";

        try (Connection connection = DBManager.getInstance().getConnection()) {
            // 1. Buscamos el Usuario
            try (PreparedStatement pstmtUser = connection.prepareStatement(sqlUser)) {
                pstmtUser.setString(1, email);
                try (ResultSet rsUser = pstmtUser.executeQuery()) {
                    if (rsUser.next()) {
                        Usuario usuario = mapResultSetToUsuario(rsUser);

                        // 2. Buscamos las direcciones del usuario encontrado
                        try (PreparedStatement pstmtAddress = connection.prepareStatement(sqlAddress)) {
                            pstmtAddress.setInt(1, usuario.getUsuarioId());
                            System.out.println(usuario.getUsuarioId());
                            try (ResultSet rsAddress = pstmtAddress.executeQuery()) {
                                List<DireccionUsuario> listaDirecciones = new ArrayList<>();
                                while (rsAddress.next()) {
                                    // Aquí asumo que tienes un método similar para mapear direcciones
                                    listaDirecciones.add(mapResultSetToDireccion(rsAddress));
                                }
                                // 3. Guardamos la lista en el objeto usuario
                                usuario.setDirecciones(listaDirecciones);
                            }
                        }
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar usuario y sus direcciones con email: " + email, e);
        }
        return null;
    }
    /*
    private Integer id;
    private Usuario usuario;
    private Ubigeo ubigeo;

    private String direccion;
    private Boolean esPrincipal;
    private String referencia;

    private Boolean esActivo;

    * */

    private DireccionUsuario mapResultSetToDireccion(ResultSet rsAddress) throws SQLException {
        DireccionUsuario direccionUsuario = new DireccionUsuario();
        //  direccion_id, usuario_id, ubigeo_id, direccion, principal, referencia
        direccionUsuario.setId(rsAddress.getInt(1));
        direccionUsuario.setIdUsuario(rsAddress.getInt(2));
        direccionUsuario.setIdUbigeo(rsAddress.getInt(3));
        direccionUsuario.setDireccion(rsAddress.getString(4));
        direccionUsuario.setEsPrincipal(rsAddress.getBoolean(5));
        direccionUsuario.setReferencia(rsAddress.getString(6));
        direccionUsuario.setEsActivo(true);

        return direccionUsuario;
    }

    // Update
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
    // Delete
    @Override
    public void remove(Usuario usuario) {
        String sql = "UPDATE usuario SET es_activo =0 WHERE usuario_id = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, usuario.getUsuarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }



    // Listar todos
    @Override
    public List<Usuario> listAll() {
        List<Usuario> list = new ArrayList<>();
        String sqlUser = "SELECT usuario_id, nombres, apellidos, email, contra_hash, celular, rol, fec_creacion FROM usuario WHERE es_activo=1";
        String sqlAddress = "SELECT direccion_id, usuario_id, ubigeo_id, direccion, principal, referencia FROM direccion_usuario WHERE usuario_id = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sqlUser)) {

            // Preparamos el statement de direcciones UNA SOLA VEZ fuera del bucle para ganar velocidad
            try (PreparedStatement pstmtAddress = connection.prepareStatement(sqlAddress)) {

                while (rs.next()) {
                    Usuario usuario = mapResultSetToUsuario(rs);

                    // Buscamos las direcciones para el usuario actual
                    pstmtAddress.setInt(1, usuario.getUsuarioId());
                    try (ResultSet rsAddress = pstmtAddress.executeQuery()) {
                        List<DireccionUsuario> direcciones = new ArrayList<>();
                        while (rsAddress.next()) {
                            direcciones.add(mapResultSetToDireccion(rsAddress));
                        }
                        usuario.setDirecciones(direcciones);
                    }

                    list.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar usuarios y sus direcciones", e);
        }
        return list;
    }

    // Método auxiliar para no repetir código de mapeo
    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        //  usuario_id, nombres, apellidos, email, contra_hash, celular, rol, fec_creacion
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