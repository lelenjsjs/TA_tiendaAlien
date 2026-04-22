package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.FranquiciaDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FranquiciaDAOImpl implements FranquiciaDAO {

    @Override
    public List<Franquicia> listAll() {
        List<Franquicia> lista = new ArrayList<>();
        String sql = "SELECT franquicia_id, nombre FROM franquicias";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Franquicias", e);
        }
        return lista;
    }

    @Override
    public Franquicia load(Integer id) {
        String sql = "SELECT franquicia_id, nombre FROM franquicias WHERE franquicia_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Franquicia con ID: " + id, e);
        }
        return null;
    }

    @Override
    public Franquicia save(Franquicia f) {
        String sql = "INSERT INTO franquicias (nombre) VALUES (?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, f.getNombre());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        f.setFranquiciaId(rs.getInt(1));
                    }
                }
            }
            return f;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Franquicia", e);
        }
    }

    @Override
    public Franquicia update(Franquicia f) {
        String sql = "UPDATE franquicias SET nombre = ? WHERE franquicia_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, f.getNombre());
            ps.setInt(2, f.getFranquiciaId());

            ps.executeUpdate();
            return f;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Franquicia", e);
        }
    }

    @Override
    public void remove(Franquicia f) {
        String sql = "DELETE FROM franquicias WHERE franquicia_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getFranquiciaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Franquicia", e);
        }
    }

    private Franquicia mapResultSet(ResultSet rs) throws SQLException {
        Franquicia f = new Franquicia();
        f.setFranquiciaId(rs.getInt("franquicia_id"));
        f.setNombre(rs.getString("nombre"));
        return f;
    }
}