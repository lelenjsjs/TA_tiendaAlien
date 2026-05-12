package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.HistorialEstadoPedDAO;
import pe.edu.pucp.tiendaalien.model.ventas.HistorialEstadoPed;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialEstadoPedDAOImpl implements HistorialEstadoPedDAO {

    @Override
    public List<HistorialEstadoPed> listAll() {
        List<HistorialEstadoPed> lista = new ArrayList<>();
        // REGLA: Filtramos por es_activo = 1 y nombre correcto 'historial_estado_ped'
        String sql = "SELECT historial_id, pedido_id, estado, fec_actualizacion FROM historial_estado_ped WHERE es_activo = 1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearHistorial(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar historial", e);
        }
        return lista;
    }

    @Override
    public HistorialEstadoPed loadById(Integer id) {
        String sql = "SELECT historial_id, pedido_id, estado, fec_actualizacion FROM historial_estado_ped " +
                     "WHERE historial_id = ? AND es_activo = 1";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearHistorial(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar historial", e);
        }
        return null;
    }

    @Override
    public HistorialEstadoPed save(HistorialEstadoPed h) {
        String sql = "INSERT INTO historial_estado_ped (pedido_id, estado, fec_actualizacion, es_activo) VALUES (?, ?, ?, 1)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, h.getPedido().getPedidoId());
            ps.setString(2, h.getEstado());
            ps.setTimestamp(3, new Timestamp(h.getFecActualizacion().getTime()));

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) h.setHistorialId(rs.getInt(1));
            }
            return h;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar historial", e);
        }
    }

    @Override
    public HistorialEstadoPed update(HistorialEstadoPed h) {
        String sql = "UPDATE historial_estado_ped SET pedido_id = ?, estado = ?, fec_actualizacion = ? WHERE historial_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, h.getPedido().getPedidoId());
            ps.setString(2, h.getEstado());
            ps.setTimestamp(3, new Timestamp(h.getFecActualizacion().getTime()));
            ps.setInt(4, h.getHistorialId());

            ps.executeUpdate();
            return h;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar historial", e);
        }
    }

    @Override
    public void remove(HistorialEstadoPed h) {
        // REGLA: Borrado lógico con es_activo = 0
        String sql = "UPDATE historial_estado_ped SET es_activo = 0 WHERE historial_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getHistorialId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar historial", e);
        }
    }

    private HistorialEstadoPed mapearHistorial(ResultSet rs) throws SQLException {
        HistorialEstadoPed h = new HistorialEstadoPed();
        h.setHistorialId(rs.getInt("historial_id"));
        h.setEstado(rs.getString("estado"));
        h.setFecActualizacion(rs.getTimestamp("fec_actualizacion"));

        Pedido p = new Pedido();
        p.setPedidoId(rs.getInt("pedido_id"));
        h.setPedido(p);

        return h;
    }
}