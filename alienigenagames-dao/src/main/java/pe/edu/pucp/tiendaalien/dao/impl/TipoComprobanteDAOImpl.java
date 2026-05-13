package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.TipoComprobanteDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoComprobanteDAOImpl implements TipoComprobanteDAO {

    @Override
    public List<TipoComprobante> listAll() {
        List<TipoComprobante> lista = new ArrayList<>();
        // Eliminado el WHERE es_activo = 1
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
    public TipoComprobante loadById(Integer id) { // Cambiado a int primitivo según la Interfaz
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

    // Nota: Si tu interfaz no tiene save/update/remove, estos métodos son opcionales
    // pero los dejo corregidos con el nuevo Modelo CamelCase

    public TipoComprobante save(TipoComprobante tc) {
        String sql = "INSERT INTO tipo_comprobante (codigo_sunat, descripcion) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tc.getCodigoSunat()); // Corregido CamelCase
            ps.setString(2, tc.getDescripcion());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tc.setTipoComprobanteId(rs.getInt(1)); // Corregido CamelCase
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

            // Usamos los nombres de los métodos corregidos (CamelCase)
            ps.setString(1, tc.getCodigoSunat());
            ps.setString(2, tc.getDescripcion());
            ps.setInt(3, tc.getTipoComprobanteId());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                // Opcional: podrías lanzar una excepción si intentan actualizar un ID que no existe
                System.out.println("No se encontró el tipo de comprobante con ID: " + tc.getTipoComprobanteId());
            }

            return tc;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar tipo de comprobante", e);
        }
    }

    @Override
    public void remove(TipoComprobante t) {
        // Cambiado a DELETE físico ya que no existe la columna es_activo
        String sql = "DELETE FROM tipo_comprobante WHERE tipo_comprobante_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getTipoComprobanteId());
            ps.executeUpdate();

        } catch (SQLException e) {
            // Este error saltará si intentas borrar un tipo que ya está siendo usado por un comprobante (por el FK)
            throw new RuntimeException("No se puede eliminar el tipo de comprobante porque tiene registros asociados o error de red.", e);
        }
    }

    private TipoComprobante mapResultSet(ResultSet rs) throws SQLException {
        TipoComprobante tc = new TipoComprobante();
        // Sincronizado con el modelo CamelCase corregido
        tc.setTipoComprobanteId(rs.getInt("tipo_comprobante_id"));
        tc.setCodigoSunat(rs.getString("codigo_sunat"));
        tc.setDescripcion(rs.getString("descripcion"));
        return tc;
    }
}