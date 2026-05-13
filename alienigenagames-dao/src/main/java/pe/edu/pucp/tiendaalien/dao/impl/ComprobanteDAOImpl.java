package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.ComprobanteDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;
import pe.edu.pucp.tiendaalien.model.facturacion.EstadoSunat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprobanteDAOImpl implements ComprobanteDAO {

    @Override
    public ComprobantePago save(ComprobantePago cp) {
        String sql = "INSERT INTO comprobante_pago (pedido_id, tipo_comprobante_id, tipo_doc_id, " +
                "nro_serie, correlativo, cliente_nro_doc, cliente_denominacion, direccion_fiscal, " +
                "monto_total, monto_igv, monto_gravado, estado_sunat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cp.getPedido().getIdPedido());
            ps.setInt(2, cp.getTipoComprobante().getTipoComprobanteId());

            if (cp.getTipoDocIdentidad() != null) ps.setInt(3, cp.getTipoDocIdentidad().getTipoDocId());
            else ps.setNull(3, Types.INTEGER);

            ps.setString(4, cp.getNroSerie());
            ps.setString(5, cp.getCorrelativo());
            ps.setString(6, cp.getClienteNroDoc());
            ps.setString(7, cp.getClienteDenominacion());
            ps.setString(8, cp.getDireccionFiscal());
            ps.setDouble(9, cp.getMontoTotal());
            ps.setDouble(10, cp.getMontoIgv());
            ps.setDouble(11, cp.getMontoGravado());
            ps.setString(12, cp.getEstadoSunat().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) cp.setComprobanteId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cp;
    }

    @Override
    public ComprobantePago loadById(Integer id) {
        String sql = "SELECT * FROM comprobante_pago WHERE comprobante_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ComprobantePago cp = new ComprobantePago();
                    cp.setComprobanteId(rs.getInt("comprobante_id"));
                    cp.setNroSerie(rs.getString("nro_serie"));
                    cp.setCorrelativo(rs.getString("correlativo"));
                    cp.setEstadoSunat(EstadoSunat.valueOf(rs.getString("estado_sunat")));
                    // Nota: Aquí deberías cargar los objetos Pedido, TipoDoc, etc.
                    return cp;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public ComprobantePago update(ComprobantePago cp) {
        String sql = "UPDATE comprobante_pago SET estado_sunat = ?, url_xml = ?, url_pdf = ? WHERE comprobante_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cp.getEstadoSunat().name());
            ps.setString(2, cp.getUrlXml());
            ps.setString(3, cp.getUrlPdf());
            ps.setInt(4, cp.getComprobanteId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
        return cp;
    }

    @Override
    public void remove(ComprobantePago cp) {
        // Generalmente los comprobantes no se borran, se anulan (Update estado a ANULADO)
        String sql = "UPDATE comprobante_pago SET estado_sunat = 'ANULADO' WHERE comprobante_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cp.getComprobanteId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
