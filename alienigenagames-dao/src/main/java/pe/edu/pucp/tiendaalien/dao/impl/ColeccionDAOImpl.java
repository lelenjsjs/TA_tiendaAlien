package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.ColeccionDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColeccionDAOImpl implements ColeccionDAO {

    @Override
    public List<Coleccion> listAll() {
        List<Coleccion> lista = new ArrayList<>();
        // REGLA: Solo activos y usamos el nombre correcto de la tabla 'coleccion'
        String sql = "SELECT coleccion_id, nombre FROM coleccion WHERE es_activo = 1";

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
    public Coleccion loadById(Integer id) {
        String sql = "SELECT coleccion_id, nombre FROM coleccion WHERE coleccion_id = ? AND es_activo = 1";

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
        // Solo insertamos el nombre
        String sql = "INSERT INTO coleccion (nombre) VALUES (?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());

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
        String sql = "UPDATE coleccion SET nombre = ? WHERE coleccion_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getColeccionId());

            ps.executeUpdate();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Colección", e);
        }
    }

    @Override
    public void remove(Coleccion c) {
        // REGLA: Borrado lógico
        String sql = "UPDATE coleccion SET es_activo = 0 WHERE coleccion_id = ? AND es_activo = 1";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getColeccionId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("La colección a eliminar ya está desactivada o no existe.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Colección", e);
        }
    }

    private Coleccion mapResultSet(ResultSet rs) throws SQLException {
        Coleccion c = new Coleccion();
        c.setColeccionId(rs.getInt("coleccion_id"));
        c.setNombre(rs.getString("nombre"));
        // Ya no buscamos franquicia_id porque en tu script no existe
        return c;
    }
}