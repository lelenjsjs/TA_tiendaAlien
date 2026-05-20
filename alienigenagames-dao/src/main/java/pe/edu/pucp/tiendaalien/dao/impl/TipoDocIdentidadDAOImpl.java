package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.TipoDocIdentidadDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDocIdentidadDAOImpl implements TipoDocIdentidadDAO {

    @Override
    public List<TipoDocIdentidad> listAll() {
        List<TipoDocIdentidad> lista = new ArrayList<>();
        String sql = "SELECT tipo_doc_id, codigo_sunat, descripcion FROM tipo_doc_identidad";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar tipos de documento", e);
        }
        return lista;
    }

    @Override
    public TipoDocIdentidad loadById(Integer id) {
        String sql = "SELECT tipo_doc_id, codigo_sunat, descripcion FROM tipo_doc_identidad WHERE tipo_doc_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar tipo de documento", e);
        }
        return null;
    }

    @Override
    public TipoDocIdentidad save(TipoDocIdentidad t) {
        String sql = "INSERT INTO tipo_doc_identidad (codigo_sunat, descripcion) VALUES (?, ?)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getCodigoSunat());
            ps.setString(2, t.getDescripcion());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) t.setTipoDocId(rs.getInt(1));
            }
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar tipo de documento", e);
        }
    }

    @Override
    public TipoDocIdentidad update(TipoDocIdentidad t) {
        String sql = "UPDATE tipo_doc_identidad SET codigo_sunat = ?, descripcion = ? WHERE tipo_doc_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getCodigoSunat());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getTipoDocId());
            ps.executeUpdate();
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar tipo de documento", e);
        }
    }

    @Override
    public void remove(TipoDocIdentidad t) {
        String sql = "DELETE FROM tipo_doc_identidad WHERE tipo_doc_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, t.getTipoDocId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar tipo de documento (Verifique si tiene comprobantes asociados)", e);
        }
    }

    private TipoDocIdentidad mapResultSet(ResultSet rs) throws SQLException {
        TipoDocIdentidad t = new TipoDocIdentidad();
        t.setTipoDocId(rs.getInt("tipo_doc_id"));
        t.setCodigoSunat(rs.getString("codigo_sunat"));
        t.setDescripcion(rs.getString("descripcion"));
        return t;
    }
}