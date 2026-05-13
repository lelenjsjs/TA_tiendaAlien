package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.TarifaEnvioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.TipoEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.ModalidadPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaEnvioDAOImpl implements TarifaEnvioDAO {

    @Override
    public List<TarifaEnvio> listAll() {
        List<TarifaEnvio> lista = new ArrayList<>();
        // REGLA: Solo activos
        String sql = "SELECT * FROM tarifa_envio WHERE es_activo = 1";

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
    public TarifaEnvio loadById(Integer id) {
        // REGLA: Solo si está activo
        String sql = "SELECT * FROM tarifa_envio WHERE tarifa_id = ? AND es_activo = 1";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar tarifa", e);
        }
        return null;
    }

    @Override
    public TarifaEnvio save(TarifaEnvio t) {
        String sql = "INSERT INTO tarifa_envio (agencia_id, tipo_envio, modalidad_pago, costo, dias_estimados, activo) VALUES (?, ?, ?, ?, ?, 1)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getAgenciaEnvio().getAgenciaId());
            ps.setString(2, t.getTipoEnvio().name());
            ps.setString(3, t.getModalidad().name());
            ps.setDouble(4, t.getCosto());
            ps.setInt(5, t.getDiasEstimados());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) t.setTarifaId(rs.getInt(1));
            }
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar tarifa", e);
        }
    }

    @Override
    public TarifaEnvio update(TarifaEnvio t) {
        String sql = "UPDATE tarifa_envio SET agencia_id = ?, tipo_envio = ?, modalidad_pago = ?, costo = ?, dias_estimados = ? WHERE tarifa_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getAgenciaEnvio().getAgenciaId());
            ps.setString(2, t.getTipoEnvio().name());
            ps.setString(3, t.getModalidad().name());
            ps.setDouble(4, t.getCosto());
            ps.setInt(5, t.getDiasEstimados());
            ps.setInt(6, t.getTarifaId());

            ps.executeUpdate();
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar tarifa", e);
        }
    }

    @Override
    public void remove(TarifaEnvio t) {
        // REGLA CUMPLIDA: Borrado lógico
        String sql = "UPDATE tarifa_envio SET es_activo = 0 WHERE tarifa_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, t.getTarifaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al desactivar tarifa", e);
        }
    }

    private TarifaEnvio mapResultSet(ResultSet rs) throws SQLException {
        TarifaEnvio t = new TarifaEnvio();
        t.setTarifaId(rs.getInt("tarifa_id"));
        t.setCosto(rs.getDouble("costo"));
        t.setDiasEstimados(rs.getInt("dias_estimados"));
        t.setActivo(rs.getBoolean("activo"));
        t.setTipoEnvio(TipoEnvio.valueOf(rs.getString("tipo_envio")));
        t.setModalidad(ModalidadPago.valueOf(rs.getString("modalidad_pago")));

        AgenciaEnvio a = new AgenciaEnvio();
        a.setAgenciaId(rs.getInt("agencia_id"));
        t.setAgenciaEnvio(a);
        return t;
    }
}