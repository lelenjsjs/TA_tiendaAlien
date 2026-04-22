package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.HistorialEstadoPedDAO;
import pe.edu.pucp.tiendaalien.model.ventas.HistorialEstadoPed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialEstadoPedDAOImpl implements HistorialEstadoPedDAO {

    @Override
    public List<HistorialEstadoPed> listAll() {

        List<HistorialEstadoPed> lista = new ArrayList<>();

        String sql = """
            SELECT historial_id,estado,fec_actualizacion
            FROM historial_estado_pedido
            WHERE activo = 1
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearHistorial(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public HistorialEstadoPed load(Integer id) {

        String sql = """
            SELECT historial_id,estado,fec_actualizacion
            FROM historial_estado_pedido
            WHERE historial_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearHistorial(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public HistorialEstadoPed save(HistorialEstadoPed historial) {

        String sql = """
            INSERT INTO historial_estado_pedido(
                estado,
                fec_actualizacion,
                activo
            )
            VALUES (?, ?, 1)
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, historial.getEstado());
            ps.setTimestamp(
                    2,
                    new Timestamp(
                            historial.getFecActualizacion().getTime()
                    )
            );

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    historial.setHistorialId(rs.getInt(1));
                }
            }

            return historial;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HistorialEstadoPed update(HistorialEstadoPed historial) {

        String sql = """
            UPDATE historial_estado_pedido SET
                estado = ?,
                fec_actualizacion = ?
            WHERE historial_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, historial.getEstado());
            ps.setTimestamp(
                    2,
                    new Timestamp(
                            historial.getFecActualizacion().getTime()
                    )
            );
            ps.setInt(3, historial.getHistorialId());

            ps.executeUpdate();

            return historial;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(HistorialEstadoPed historial) {

        String sql = """
            UPDATE historial_estado_pedido
            SET activo = 0
            WHERE historial_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, historial.getHistorialId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HistorialEstadoPed mapearHistorial(ResultSet rs)
            throws SQLException {

        HistorialEstadoPed h = new HistorialEstadoPed(
                rs.getString("estado"),
                rs.getTimestamp("fec_actualizacion")
        );

        h.setHistorialId(rs.getInt("historial_id"));

        return h;
    }
}