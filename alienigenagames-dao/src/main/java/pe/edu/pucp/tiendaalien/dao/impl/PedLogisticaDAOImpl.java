package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.PedLogisticaDAO;
import pe.edu.pucp.tiendaalien.model.logistica.EstadoLogistica;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogistica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedLogisticaDAOImpl implements PedLogisticaDAO {

    @Override
    public List<PedLogistica> listAll() {

        List<PedLogistica> lista = new ArrayList<>();

        String sql = """
            SELECT ped_logistica_id,estado_logistica,receptor_nombre,receptor_tipo_doc,receptor_nro_doc,receptor_cel
            FROM ped_logistica
            WHERE activo = 1
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearPedLogistica(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public PedLogistica load(Integer id) {

        String sql = """
            SELECT ped_logistica_id,estado_logistica,receptor_nombre,receptor_tipo_doc,receptor_nro_doc,receptor_cel
            FROM ped_logistica
            WHERE ped_logistica_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPedLogistica(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public PedLogistica save(PedLogistica ped) {

        String sql = """
            INSERT INTO ped_logistica(
                estado_logistica,
                receptor_nombre,
                receptor_tipo_doc,
                receptor_nro_doc,
                receptor_cel,
                activo
            )
            VALUES (?, ?, ?, ?, ?, 1)
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            llenarPreparedStatement(ps, ped);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ped.setPedLogisticaId(rs.getInt(1));
                }
            }

            return ped;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PedLogistica update(PedLogistica ped) {

        String sql = """
            UPDATE ped_logistica SET
                estado_logistica = ?,
                receptor_nombre = ?,
                receptor_tipo_doc = ?,
                receptor_nro_doc = ?,
                receptor_cel = ?
            WHERE ped_logistica_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarPreparedStatement(ps, ped);
            ps.setInt(6, ped.getPedLogisticaId());

            ps.executeUpdate();

            return ped;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(PedLogistica ped) {

        String sql = """
            UPDATE ped_logistica
            SET activo = 0
            WHERE ped_logistica_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ped.getPedLogisticaId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PedLogistica mapearPedLogistica(ResultSet rs)
            throws SQLException {

        PedLogistica p = new PedLogistica();

        p.setPedLogisticaId(rs.getInt("ped_logistica_id"));
        p.setEstadoLogistica(
                EstadoLogistica.valueOf(
                        rs.getString("estado_logistica")
                )
        );
        p.setReceptorNombre(rs.getString("receptor_nombre"));
        p.setReceptorTipoDoc(rs.getString("receptor_tipo_doc"));
        p.setReceptorNroDoc(rs.getString("receptor_nro_doc"));
        p.setReceptorCel(rs.getString("receptor_cel"));

        return p;
    }

    private void llenarPreparedStatement(
            PreparedStatement ps,
            PedLogistica ped) throws SQLException {

        ps.setString(1, ped.getEstadoLogistica().name());
        ps.setString(2, ped.getReceptorNombre());
        ps.setString(3, ped.getReceptorTipoDoc());
        ps.setString(4, ped.getReceptorNroDoc());
        ps.setString(5, ped.getReceptorCel());
    }
}