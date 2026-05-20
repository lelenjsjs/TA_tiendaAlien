package pe.edu.pucp.tiendaalien.dao.impl;


import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.SucursalDAO;
import pe.edu.pucp.tiendaalien.model.logistica.SucursalAgencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAOImpl implements SucursalDAO {

    @Override
    public SucursalAgencia loadById(Integer id) {
        String sql = "SELECT * FROM sucursales_agencia WHERE sucursal_id = ? AND si_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<SucursalAgencia> listAll() {
        List<SucursalAgencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM sucursales_agencia WHERE si_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public List<SucursalAgencia> listByAgency(int agenciaId) {
        List<SucursalAgencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM sucursales_agencia WHERE agencia_id = ? AND si_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, agenciaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public SucursalAgencia save(SucursalAgencia s) {
        String sql = "INSERT INTO sucursales_agencia (agencia_id, ubigeo_id, si_activo) VALUES (?, ?, 1)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getAgenciaId());
            ps.setInt(2, s.getUbigeoId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setSucursalId(rs.getInt(1));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return s;
    }

    @Override
    public SucursalAgencia update(SucursalAgencia s) {
        String sql = "UPDATE sucursales_agencia SET agencia_id = ?, ubigeo_id = ? WHERE sucursal_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getAgenciaId());
            ps.setInt(2, s.getUbigeoId());
            ps.setInt(3, s.getSucursalId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
        return s;
    }

    @Override
    public void remove(SucursalAgencia s) {
        String sql = "UPDATE sucursales_agencia SET si_activo = 0 WHERE sucursal_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getSucursalId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private SucursalAgencia mapear(ResultSet rs) throws SQLException {
        SucursalAgencia s = new SucursalAgencia();
        s.setSucursalId(rs.getInt("sucursal_id"));
        s.setAgenciaId(rs.getInt("agencia_id"));
        s.setUbigeoId(rs.getInt("ubigeo_id"));
        s.setActivo(rs.getBoolean("si_activo"));
        return s;
    }
}