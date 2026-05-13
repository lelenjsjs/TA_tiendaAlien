package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.HistorialEstadoPedDAO;
import pe.edu.pucp.tiendaalien.model.ventas.EstadoPedido;
import pe.edu.pucp.tiendaalien.model.ventas.HistorialEstadoPed;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialEstadoPedDAOImpl implements HistorialEstadoPedDAO {

    // --- MÉTODO TRANSACCIONAL (EL QUE USA EL BL) ---
    @Override
    public HistorialEstadoPed save(HistorialEstadoPed h, Connection con) throws SQLException {
        // Usamos NOW() o dejamos que la BD use CURRENT_TIMESTAMP
        String sql = "INSERT INTO historial_estado_ped (pedido_id, estado, fec_actualizacion) VALUES (?, ?, NOW())";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, h.getPedido().getIdPedido());
            // Si usas Enum, recuerda el .name()
            ps.setString(2, h.getEstado());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    h.setId(rs.getInt(1));
                }
            }
        }
        return h;
    }

    // --- MÉTODOS CRUD NORMALES ---

    @Override
    public HistorialEstadoPed save(HistorialEstadoPed h) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            return save(h, con);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Historial", e);
        }
    }

    @Override
    public HistorialEstadoPed loadById(Integer id) {
        String sql = "SELECT * FROM historial_estado_ped WHERE historial_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearHistorial(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Historial", e);
        }
        return null;
    }

    @Override
    public HistorialEstadoPed update(HistorialEstadoPed h) {
        String sql = "UPDATE historial_estado_ped SET pedido_id = ?, estado = ? WHERE historial_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getPedido().getIdPedido());
            ps.setString(2, h.getEstado());
            ps.setInt(3, h.getId());
            ps.executeUpdate();
            return h;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Historial", e);
        }
    }

    @Override
    public void remove(HistorialEstadoPed h) {
        String sql = "DELETE FROM historial_estado_ped WHERE historial_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Historial", e);
        }
    }

    // Método auxiliar para no repetir código
    private HistorialEstadoPed mapearHistorial(ResultSet rs) throws SQLException {
        HistorialEstadoPed h = new HistorialEstadoPed();
        h.setId(rs.getInt("historial_id"));

        Pedido ped = new Pedido();
        ped.setIdPedido(rs.getInt("pedido_id"));
        h.setPedido(ped);

        // Convertir String a Enum (ajusta si no usas Enum)
        h.setEstado(String.valueOf(EstadoPedido.valueOf(rs.getString("estado"))));
        h.setFecActualizacion(rs.getTimestamp("fec_actualizacion"));
        return h;
    }
}