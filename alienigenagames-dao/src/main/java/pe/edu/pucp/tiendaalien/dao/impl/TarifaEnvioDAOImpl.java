package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.TarifaEnvioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.TipoEnvio; // Tu Enum
import pe.edu.pucp.tiendaalien.model.logistica.ModalidadPago; // Tu Enum

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaEnvioDAOImpl implements TarifaEnvioDAO {

    @Override
    public TarifaEnvio save(TarifaEnvio t) {
        String sql = "INSERT INTO tarifa_envio (agencia_id, tipo_envio, modalidad_pago, costo, dias_estimados, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getAgenciaEnvio().getAgenciaId());
            // Convertimos el Enum de Java a String para MySQL
            ps.setString(2, t.getTipoEnvio().name());
            ps.setString(3, t.getModalidad().name());
            ps.setDouble(4, t.getCosto());
            ps.setInt(5, t.getDiasEstimados());
            ps.setBoolean(6, t.getActivo());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setTarifaId(rs.getInt(1));
                    }
                }
            }
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar tarifa de envío", e);
        }
    }

    @Override
    public TarifaEnvio load(Integer id) {
        String sql = "SELECT * FROM tarifa_envio WHERE tarifa_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar tarifa con ID: " + id, e);
        }
        return null;
    }

    @Override
    public List<TarifaEnvio> listAll() {
        List<TarifaEnvio> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarifa_envio";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar tarifas", e);
        }
        return lista;
    }

    @Override
    public TarifaEnvio update(TarifaEnvio t) {
        String sql = "UPDATE tarifa_envio SET agencia_id = ?, tipo_envio = ?, modalidad_pago = ?, costo = ?, dias_estimados = ?, activo = ? WHERE tarifa_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getAgenciaEnvio().getAgenciaId());
            ps.setString(2, t.getTipoEnvio().name());
            ps.setString(3, t.getModalidad().name());
            ps.setDouble(4, t.getCosto());
            ps.setInt(5, t.getDiasEstimados());
            ps.setBoolean(6, t.getActivo());
            ps.setInt(7, t.getTarifaId());

            ps.executeUpdate();
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la tarifa", e);
        }
    }

    @Override
    public void remove(TarifaEnvio t) {
        // En tu script tiene una columna 'activo', así que haremos eliminación LÓGICA
        String sql = "UPDATE tarifa_envio SET activo = 0 WHERE tarifa_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getTarifaId());
            ps.executeUpdate();
            t.setActivo(false);
        } catch (SQLException e) {
            throw new RuntimeException("Error al desactivar la tarifa", e);
        }
    }

    private TarifaEnvio mapResultSet(ResultSet rs) throws SQLException {
        TarifaEnvio t = new TarifaEnvio();
        t.setTarifaId(rs.getInt("tarifa_id"));
        t.setCosto(rs.getDouble("costo"));
        t.setDiasEstimados(rs.getInt("dias_estimados"));
        t.setActivo(rs.getBoolean("activo"));

        // Mapeo de Enums: String de BD -> Enum de Java
        t.setTipoEnvio(TipoEnvio.valueOf(rs.getString("tipo_envio")));
        t.setModalidad(ModalidadPago.valueOf(rs.getString("modalidad_pago")));

        // Objeto Agencia "cascarón"
        AgenciaEnvio agencia = new AgenciaEnvio();
        agencia.setAgenciaId(rs.getInt("agencia_id"));
        t.setAgenciaEnvio(agencia);

        return t;
    }
}