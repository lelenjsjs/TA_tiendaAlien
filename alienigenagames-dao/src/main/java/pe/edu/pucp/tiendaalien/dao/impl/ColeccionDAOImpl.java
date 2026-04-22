package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.ColeccionDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;
import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColeccionDAOImpl implements ColeccionDAO {

    @Override
    public List<Coleccion> listAll() {
        List<Coleccion> lista = new ArrayList<>();
        String sql = "SELECT coleccion_id, nombre, franquicia_id FROM colecciones";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Colecciones", e);
        }
        return lista;
    }

    @Override
    public Coleccion load(Integer id) {
        String sql = "SELECT coleccion_id, nombre, franquicia_id FROM colecciones WHERE coleccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Colección con ID: " + id, e);
        }
        return null;
    }

    @Override
    public Coleccion save(Coleccion c) {
        String sql = "INSERT INTO colecciones (nombre, franquicia_id) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getFranquicia().getFranquiciaId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        c.setColeccionId(rs.getInt(1));
                    }
                }
            }
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Colección", e);
        }
    }

    @Override
    public Coleccion update(Coleccion c) {
        String sql = "UPDATE colecciones SET nombre = ?, franquicia_id = ? WHERE coleccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getFranquicia().getFranquiciaId());
            ps.setInt(3, c.getColeccionId());

            ps.executeUpdate();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Colección", e);
        }
    }

    @Override
    public void remove(Coleccion c) {
        String sql = "DELETE FROM colecciones WHERE coleccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getColeccionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Colección", e);
        }
    }

    private Coleccion mapResultSet(ResultSet rs) throws SQLException {
        Coleccion c = new Coleccion();
        c.setColeccionId(rs.getInt("coleccion_id"));
        c.setNombre(rs.getString("nombre"));

        // Se crea el objeto Franquicia solo con el ID obtenido de la BD
        Franquicia f = new Franquicia();
        f.setFranquiciaId(rs.getInt("franquicia_id"));
        c.setFranquicia(f);

        return c;
    }
}