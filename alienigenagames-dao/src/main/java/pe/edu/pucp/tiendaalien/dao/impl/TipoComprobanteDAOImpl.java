package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.TipoComprobanteDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoComprobanteDAOImpl implements TipoComprobanteDAO {

    @Override
    public List<TipoComprobante> listAll() {
        List<TipoComprobante> lista = new ArrayList<>();
        String sql = "SELECT tipo_comprobante_id, codigo_sunat, descripcion FROM tipo_comprobante";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar tipos de comprobante", e);
        }
        return lista;
    }

    @Override
    public TipoComprobante load(Integer id) {
        String sql = "SELECT tipo_comprobante_id, codigo_sunat, descripcion FROM tipo_comprobante WHERE tipo_comprobante_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar tipo de comprobante con ID: " + id, e);
        }
        return null;
    }

    @Override
    public TipoComprobante save(TipoComprobante tc) {
        String sql = "INSERT INTO tipo_comprobante (codigo_sunat, descripcion) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tc.getCodigo_sunat());
            ps.setString(2, tc.getDescripcion());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        tc.setTipo_comprobante_id(rs.getInt(1));
                    }
                }
            }
            return tc;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar tipo de comprobante", e);
        }
    }

    @Override
    public TipoComprobante update(TipoComprobante tc) {
        String sql = "UPDATE tipo_comprobante SET codigo_sunat = ?, descripcion = ? WHERE tipo_comprobante_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tc.getCodigo_sunat());
            ps.setString(2, tc.getDescripcion());
            ps.setInt(3, tc.getTipo_comprobante_id());

            ps.executeUpdate();
            return tc;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar tipo de comprobante", e);
        }
    }

    @Override
    public void remove(TipoComprobante tc) {
        String sql = "DELETE FROM tipo_comprobante WHERE tipo_comprobante_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tc.getTipo_comprobante_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar tipo de comprobante", e);
        }
    }

    private TipoComprobante mapResultSet(ResultSet rs) throws SQLException {
        TipoComprobante tc = new TipoComprobante();
        tc.setTipo_comprobante_id(rs.getInt("tipo_comprobante_id"));
        tc.setCodigo_sunat(rs.getString("codigo_sunat"));
        tc.setDescripcion(rs.getString("descripcion"));
        return tc;
    }
}