package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.AgenciaEnvioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio; // Verifica que esté en este paquete

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaEnvioDAOImpl implements AgenciaEnvioDAO {

    @Override
    public List<AgenciaEnvio> listAll() {
        List<AgenciaEnvio> lista = new ArrayList<>();
        String sql = "SELECT agencia_id, nombre, url_tracking FROM agencias_de_envio";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Agencias de Envío", e);
        }
        return lista;
    }

    @Override
    public AgenciaEnvio load(Integer id) {
        String sql = "SELECT agencia_id, nombre, url_tracking FROM agencias_de_envio WHERE agencia_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Agencia con ID: " + id, e);
        }
        return null;
    }

    @Override
    public AgenciaEnvio save(AgenciaEnvio a) {
        String sql = "INSERT INTO agencias_de_envio (nombre, url_tracking) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getUrlTracking());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        a.setAgenciaId(rs.getInt(1));
                    }
                }
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Agencia", e);
        }
    }

    @Override
    public AgenciaEnvio update(AgenciaEnvio a) {
        String sql = "UPDATE agencias_de_envio SET nombre = ?, url_tracking = ? WHERE agencia_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getUrlTracking());
            ps.setInt(3, a.getAgenciaId());

            ps.executeUpdate();
            return a;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Agencia", e);
        }
    }

    @Override
    public void remove(AgenciaEnvio a) {
        String sql = "DELETE FROM agencias_de_envio WHERE agencia_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getAgenciaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Agencia", e);
        }
    }

    private AgenciaEnvio mapResultSet(ResultSet rs) throws SQLException {
        AgenciaEnvio a = new AgenciaEnvio();
        a.setAgenciaId(rs.getInt("agencia_id"));
        a.setNombre(rs.getString("nombre"));
        a.setUrlTracking(rs.getString("url_tracking"));
        return a;
    }
}