package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.DireccionUsuarioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionUsuarioDAOImpl implements DireccionUsuarioDAO {

    @Override
    public DireccionUsuario save(DireccionUsuario d) {
        // En tu script la tabla es: direccion_usuario
        // Columnas: usuario_id, ubigeo_id, direccion, principal, referencia
        String sql = "INSERT INTO direccion_usuario (usuario_id, ubigeo_id, direccion, principal, referencia) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Extraemos los IDs de los objetos internos
            ps.setInt(1, d.getUsuario().getUsuarioId());
            ps.setInt(2, d.getUbigeo().getUbigeoId());
            ps.setString(3, d.getDireccion());
            ps.setBoolean(4, d.getEsPrincipal());
            ps.setString(5, d.getReferencia());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        d.setDireccionId(rs.getInt(1));
                    }
                }
            }
            return d;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la dirección del usuario", e);
        }
    }

    @Override
    public DireccionUsuario load(Integer id) {
        String sql = "SELECT direccion_id, usuario_id, ubigeo_id, direccion, principal, referencia FROM direccion_usuario WHERE direccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar dirección con ID: " + id, e);
        }
        return null;
    }

    @Override
    public List<DireccionUsuario> listAll() {
        List<DireccionUsuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM direccion_usuario";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar direcciones", e);
        }
        return lista;
    }

    @Override
    public DireccionUsuario update(DireccionUsuario d) {
        String sql = "UPDATE direccion_usuario SET usuario_id = ?, ubigeo_id = ?, direccion = ?, principal = ?, referencia = ? WHERE direccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getUsuario().getUsuarioId());
            ps.setInt(2, d.getUbigeo().getUbigeoId());
            ps.setString(3, d.getDireccion());
            ps.setBoolean(4, d.getEsPrincipal());
            ps.setString(5, d.getReferencia());
            ps.setInt(6, d.getDireccionId());

            ps.executeUpdate();
            return d;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la dirección", e);
        }
    }

    @Override
    public void remove(DireccionUsuario d) {
        String sql = "DELETE FROM direccion_usuario WHERE direccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getDireccionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la dirección", e);
        }
    }

    private DireccionUsuario mapResultSet(ResultSet rs) throws SQLException {
        DireccionUsuario d = new DireccionUsuario();
        d.setDireccionId(rs.getInt("direccion_id"));
        d.setDireccion(rs.getString("direccion"));
        d.setEsPrincipal(rs.getBoolean("principal"));
        d.setReferencia(rs.getString("referencia"));

        // Creamos objetos "cascarón" solo con el ID
        Usuario user = new Usuario();
        user.setUsuarioId(rs.getInt("usuario_id"));
        d.setUsuario(user);

        Ubigeo ubi = new Ubigeo();
        ubi.setUbigeoId(rs.getInt("ubigeo_id"));
        d.setUbigeo(ubi);

        return d;
    }
}