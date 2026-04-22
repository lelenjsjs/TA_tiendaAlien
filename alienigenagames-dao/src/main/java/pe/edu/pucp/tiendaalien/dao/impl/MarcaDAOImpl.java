package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.MarcaDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.catalogo.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements MarcaDAO {

    @Override
    public List<Marca> listAll() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT marca_id, nombre FROM marca";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Marcas", e);
        }
        return lista;
    }

    @Override
    public Marca load(Integer id) {
        String sql = "SELECT marca_id, nombre FROM marca WHERE marca_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Marca con ID: " + id, e);
        }
        return null;
    }

    @Override
    public Marca save(Marca m) {
        String sql = "INSERT INTO marca (nombre) VALUES (?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNombre());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        m.setMarcaId(rs.getInt(1));
                    }
                }
            }
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Marca", e);
        }
    }

    @Override
    public Marca update(Marca m) {
        String sql = "UPDATE marca SET nombre = ? WHERE marca_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getMarcaId());

            ps.executeUpdate();
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Marca", e);
        }
    }

    @Override
    public void remove(Marca m) {
        String sql = "DELETE FROM marca WHERE marca_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getMarcaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Marca", e);
        }
    }

    private Marca mapResultSet(ResultSet rs) throws SQLException {
        Marca m = new Marca();
        m.setMarcaId(rs.getInt("marca_id"));
        m.setNombre(rs.getString("nombre"));
        return m;
    }
}