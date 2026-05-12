package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.ComprobantePagoDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;
import pe.edu.pucp.tiendaalien.model.facturacion.EstadoSunat;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprobantePagoDAOImpl implements ComprobantePagoDAO {

    @Override
    public List<ComprobantePago> listAll() {
        List<ComprobantePago> list = new ArrayList<>();
        String sql = "SELECT * FROM comprobante_pago WHERE es_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapearObjeto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar comprobantes", e);
        }
        return list;
    }

    @Override
    public ComprobantePago loadById(Integer id) {
        String sql = "SELECT * FROM comprobante_pago WHERE comprobante_id = ? AND es_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return mapearObjeto(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar comprobante", e);
        }
        return null;
    }

    @Override
    public ComprobantePago save(ComprobantePago comp) {
        String sql = "INSERT INTO comprobante_pago (nro_serie, correlativo, cliente_nro_doc, cliente_denominacion, " +
                "direccion_fiscal, monto_total, monto_igv, monto_gravado, estado_sunat, url_xml, url_pdf, " +
                "fec_emision, pedido_id, tipo_comprobante_id, tipo_doc_identidad_id, es_activo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, comp.getNro_serie());
            pst.setString(2, comp.getCorrelativo());
            pst.setString(3, comp.getCliente_nro_doc());
            pst.setString(4, comp.getCliente_denominacion());
            pst.setString(5, comp.getDireccion_fiscal());
            pst.setDouble(6, comp.getMonto_total());
            pst.setDouble(7, comp.getMonto_igv());
            pst.setDouble(8, comp.getMonto_gravado());
            pst.setString(9, comp.getEstado_sunat() != null ? comp.getEstado_sunat().name() : "PENDIENTE");
            pst.setString(10, comp.getUrl_xml());
            pst.setString(11, comp.getUrl_pdf());
            pst.setTimestamp(12, comp.getFec_emision() != null ? new Timestamp(comp.getFec_emision().getTime()) : null);
            pst.setInt(13, comp.getPedido().getPedidoId());
            pst.setInt(14, comp.getTipoComprobante().getTipo_comprobante_id());
            pst.setInt(15, comp.getTipoDocIdentidad().getTipoDocId());

            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) comp.setComprobante_id(rs.getInt(1));
            }
            return comp;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar", e);
        }
    }

    @Override
    public ComprobantePago update(ComprobantePago comp) {
        String sql = "UPDATE comprobante_pago SET nro_serie=?, correlativo=?, cliente_nro_doc=?, cliente_denominacion=?, " +
                "direccion_fiscal=?, monto_total=?, monto_igv=?, monto_gravado=?, estado_sunat=?, url_xml=?, url_pdf=?, " +
                "fec_emision=?, pedido_id=?, tipo_comprobante_id=?, tipo_doc_identidad_id=? WHERE comprobante_id=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, comp.getNro_serie());
            pst.setString(2, comp.getCorrelativo());
            pst.setString(3, comp.getCliente_nro_doc());
            pst.setString(4, comp.getCliente_denominacion());
            pst.setString(5, comp.getDireccion_fiscal());
            pst.setDouble(6, comp.getMonto_total());
            pst.setDouble(7, comp.getMonto_igv());
            pst.setDouble(8, comp.getMonto_gravado());
            pst.setString(9, comp.getEstado_sunat().name());
            pst.setString(10, comp.getUrl_xml());
            pst.setString(11, comp.getUrl_pdf());
            pst.setTimestamp(12, new Timestamp(comp.getFec_emision().getTime()));
            pst.setInt(13, comp.getPedido().getPedidoId());
            pst.setInt(14, comp.getTipoComprobante().getTipo_comprobante_id());
            pst.setInt(15, comp.getTipoDocIdentidad().getTipoDocId());
            pst.setInt(16, comp.getComprobante_id());

            pst.executeUpdate();
            return comp;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar", e);
        }
    }

    @Override
    public void remove(ComprobantePago comp) {
        // REGLA: UPDATE es_activo = 0 (Borrado Lógico)
        String sql = "UPDATE comprobante_pago SET es_activo = 0, estado_sunat = 'ANULADO' WHERE comprobante_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, comp.getComprobante_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al anular (borrado lógico)", e);
        }
    }

    private ComprobantePago mapearObjeto(ResultSet rs) throws SQLException {
        ComprobantePago comp = new ComprobantePago();
        comp.setComprobante_id(rs.getInt("comprobante_id"));
        comp.setNro_serie(rs.getString("nro_serie"));
        comp.setCorrelativo(rs.getString("correlativo"));
        comp.setCliente_nro_doc(rs.getString("cliente_nro_doc"));
        comp.setCliente_denominacion(rs.getString("cliente_denominacion"));
        comp.setDireccion_fiscal(rs.getString("direccion_fiscal"));
        comp.setMonto_total(rs.getDouble("monto_total"));
        comp.setMonto_igv(rs.getDouble("monto_igv"));
        comp.setMonto_gravado(rs.getDouble("monto_gravado"));

        String estadoStr = rs.getString("estado_sunat");
        if (estadoStr != null) comp.setEstado_sunat(EstadoSunat.valueOf(estadoStr));

        comp.setUrl_xml(rs.getString("url_xml"));
        comp.setUrl_pdf(rs.getString("url_pdf"));
        comp.setFec_emision(rs.getTimestamp("fec_emision"));

        Pedido p = new Pedido(); p.setPedidoId(rs.getInt("pedido_id")); comp.setPedido(p);
        TipoComprobante tc = new TipoComprobante(); tc.setTipo_comprobante_id(rs.getInt("tipo_comprobante_id")); comp.setTipoComprobante(tc);
        TipoDocIdentidad tdi = new TipoDocIdentidad(); tdi.setTipoDocId(rs.getInt("tipo_doc_identidad_id")); comp.setTipoDocIdentidad(tdi);

        return comp;
    }
}