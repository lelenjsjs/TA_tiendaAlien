package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.UbigeoDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UbigeoDAOImpl implements UbigeoDAO {

    @Override
    public List<Ubigeo> listAll() {
        List<Ubigeo> lista = new ArrayList<>();
        String sql = "SELECT ubigeo_id, codigo, departamento, provincia, distrito FROM ubigeo";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Ubigeos", e);
        }
        return lista;
    }

    @Override
    public Ubigeo load(Integer id) {
        String sql = "SELECT ubigeo_id, codigo, departamento, provincia, distrito FROM ubigeo WHERE ubigeo_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Ubigeo con ID: " + id, e);
        }
        return null;
    }

    @Override
    public Ubigeo save(Ubigeo u) {
        String sql = "INSERT INTO ubigeo (codigo, departamento, provincia, distrito) VALUES (?, ?, ?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getCodigo());
            ps.setString(2, u.getDepartamento());
            ps.setString(3, u.getProvincia());
            ps.setString(4, u.getDistrito());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        u.setUbigeoId(rs.getInt(1));
                    }
                }
            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Ubigeo", e);
        }
    }

    @Override
    public Ubigeo update(Ubigeo u) {
        String sql = "UPDATE ubigeo SET codigo = ?, departamento = ?, provincia = ?, distrito = ? WHERE ubigeo_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCodigo());
            ps.setString(2, u.getDepartamento());
            ps.setString(3, u.getProvincia());
            ps.setString(4, u.getDistrito());
            ps.setInt(5, u.getUbigeoId());

            ps.executeUpdate();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Ubigeo", e);
        }
    }

    @Override
    public void remove(Ubigeo u) {
        String sql = "DELETE FROM ubigeo WHERE ubigeo_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, u.getUbigeoId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Ubigeo", e);
        }
    }

    // Método auxiliar para evitar repetir el llenado del objeto
    private Ubigeo mapResultSet(ResultSet rs) throws SQLException {
        Ubigeo u = new Ubigeo();
        u.setUbigeoId(rs.getInt("ubigeo_id"));
        u.setCodigo(rs.getString("codigo"));
        u.setDepartamento(rs.getString("departamento"));
        u.setProvincia(rs.getString("provincia"));
        u.setDistrito(rs.getString("distrito"));
        return u;
    }
}