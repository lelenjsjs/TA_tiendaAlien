package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.PedLogisticaDAO;
import pe.edu.pucp.tiendaalien.model.logistica.EstadoLogistica;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogistica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedLogisticaDAOImpl implements PedLogisticaDAO {

    //  Metodo para transaccion
    @Override
    public PedLogistica save(PedLogistica t, Connection con) throws SQLException {
        String sql = """
        INSERT INTO ped_logistica (
            pedido_id, estado_logistico, receptor_nombre, 
            receptor_cel, receptor_tipo_doc, receptor_nro_doc, direccion_id
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getPedido().getIdPedido());
            // name() funciona perfecto para el ENUM de la base de datos
            ps.setString(2, t.getEstadoLogistica().name());
            ps.setString(3, t.getReceptorNombre());
            ps.setString(4, t.getReceptorCel());
            ps.setString(5, t.getReceptorTipoDoc());
            ps.setString(6, t.getReceptorNroDoc());

            // Manejo de la FK direccion_id (si el objeto tiene la dirección, se pone el ID, sino NULL)
            if (t.getDireccion() != null) {
                ps.setInt(7, t.getDireccion().getId());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    t.setPedLogisticaId(rs.getInt(1));
                }
            }
        }
        return t;
    }

    // --- MÉTODO NORMAL (Llama al transaccional) ---
    @Override
    public PedLogistica save(PedLogistica ped) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            return save(ped, con); // Reutilizamos la lógica
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar logística: " + e.getMessage(), e);
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

            // Uso de tu método auxiliar corregido (ver abajo)
            llenarPreparedStatement(ps, ped);
            ps.setInt(6, ped.getPedLogisticaId());

            ps.executeUpdate();
            return ped;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PedLogistica> listAll() {
        List<PedLogistica> lista = new ArrayList<>();
        String sql = "SELECT * FROM ped_logistica WHERE activo = 1";

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
    public PedLogistica loadById(Integer id) {
        String sql = "SELECT * FROM ped_logistica WHERE ped_logistica_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearPedLogistica(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void remove(PedLogistica ped) {
        String sql = "UPDATE ped_logistica SET activo = 0 WHERE ped_logistica_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ped.getPedLogisticaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Auxiliares
    private PedLogistica mapearPedLogistica(ResultSet rs) throws SQLException {
        PedLogistica p = new PedLogistica();
        p.setPedLogisticaId(rs.getInt("ped_logistica_id"));
        p.setEstadoLogistica(EstadoLogistica.valueOf(rs.getString("estado_logistica")));
        p.setReceptorNombre(rs.getString("receptor_nombre"));
        p.setReceptorTipoDoc(rs.getString("receptor_tipo_doc"));
        p.setReceptorNroDoc(rs.getString("receptor_nro_doc"));
        p.setReceptorCel(rs.getString("receptor_cel"));
        return p;
    }

    private void llenarPreparedStatement(PreparedStatement ps, PedLogistica ped) throws SQLException {
        ps.setString(1, ped.getEstadoLogistica().name());
        ps.setString(2, ped.getReceptorNombre());
        ps.setString(3, ped.getReceptorTipoDoc());
        ps.setString(4, ped.getReceptorNroDoc());
        ps.setString(5, ped.getReceptorCel());
    }
}