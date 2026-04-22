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
import java.util.Date;
import java.util.List;

public class ComprobantePagoDAOImpl implements ComprobantePagoDAO {

    @Override
    public List<ComprobantePago> listAll() {
        List<ComprobantePago> list = new ArrayList<>();
        String sql = "SELECT * FROM comprobante_pago";

        try(Connection connection = DBManager.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapearObjeto(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ComprobantePago load(Integer id) {
        String sql = "SELECT * FROM comprobante_pago WHERE comprobante_id = ?";
        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearObjeto(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ComprobantePago save(ComprobantePago comp) {
        String sql = "INSERT INTO comprobante_pago (nro_serie, correlativo, cliente_nro_doc, cliente_denominacion, direccion_fiscal, " +
                "monto_total, monto_igv, monto_gravado, estado_sunat, url_xml, url_pdf, fec_emision, pedido_id, tipo_comprobante_id, tipo_doc_identidad_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, comp.getNro_serie());
            pstmt.setString(2, comp.getCorrelativo());
            pstmt.setString(3, comp.getCliente_nro_doc());
            pstmt.setString(4, comp.getCliente_denominacion());
            pstmt.setString(5, comp.getDireccion_fiscal());
            pstmt.setDouble(6, comp.getMonto_total());
            pstmt.setDouble(7, comp.getMonto_igv());
            pstmt.setDouble(8, comp.getMonto_gravado());
            pstmt.setString(9, comp.getEstado_sunat() != null ? comp.getEstado_sunat().name() : null);
            pstmt.setString(10, comp.getUrl_xml());
            pstmt.setString(11, comp.getUrl_pdf());
            pstmt.setDate(12, comp.getFec_emision() != null ? new java.sql.Date(comp.getFec_emision().getTime()) : null);

            // Llaves foráneas
            if(comp.getPedido() != null) pstmt.setInt(13, comp.getPedido().getPedidoId());
            else pstmt.setNull(13, Types.INTEGER);

            if(comp.getTipoComprobante() != null) pstmt.setInt(14, comp.getTipoComprobante().getTipo_comprobante_id());
            else pstmt.setNull(14, Types.INTEGER);

            if(comp.getTipoDocIdentidad() != null) pstmt.setInt(15, comp.getTipoDocIdentidad().getTipoDocId());
            else pstmt.setNull(15, Types.INTEGER);

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comp.setComprobante_id(generatedKeys.getInt(1));
                }
            }
            return comp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ComprobantePago update(ComprobantePago comp) {
        String sql = "UPDATE comprobante_pago SET nro_serie=?, correlativo=?, cliente_nro_doc=?, cliente_denominacion=?, direccion_fiscal=?, " +
                "monto_total=?, monto_igv=?, monto_gravado=?, estado_sunat=?, url_xml=?, url_pdf=?, fec_emision=?, pedido_id=?, tipo_comprobante_id=?, tipo_doc_identidad_id=? " +
                "WHERE comprobante_id=?";

        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, comp.getNro_serie());
            pstmt.setString(2, comp.getCorrelativo());
            pstmt.setString(3, comp.getCliente_nro_doc());
            pstmt.setString(4, comp.getCliente_denominacion());
            pstmt.setString(5, comp.getDireccion_fiscal());
            pstmt.setDouble(6, comp.getMonto_total());
            pstmt.setDouble(7, comp.getMonto_igv());
            pstmt.setDouble(8, comp.getMonto_gravado());
            pstmt.setString(9, comp.getEstado_sunat() != null ? comp.getEstado_sunat().name() : null);
            pstmt.setString(10, comp.getUrl_xml());
            pstmt.setString(11, comp.getUrl_pdf());
            pstmt.setDate(12, comp.getFec_emision() != null ? new java.sql.Date(comp.getFec_emision().getTime()) : null);

            // Llaves foráneas
            if(comp.getPedido() != null) pstmt.setInt(13, comp.getPedido().getPedidoId());
            else pstmt.setNull(13, Types.INTEGER);

            if(comp.getTipoComprobante() != null) pstmt.setInt(14, comp.getTipoComprobante().getTipo_comprobante_id());
            else pstmt.setNull(14, Types.INTEGER);

            if(comp.getTipoDocIdentidad() != null) pstmt.setInt(15, comp.getTipoDocIdentidad().getTipoDocId());
            else pstmt.setNull(15, Types.INTEGER);

            pstmt.setInt(16, comp.getComprobante_id());
            pstmt.executeUpdate();

            return comp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(ComprobantePago comp) {
        // En un sistema contable real NUNCA se borran los comprobantes, se ANULAN
        comp.setEstado_sunat(EstadoSunat.ANULADO);
        String sql = "UPDATE comprobante_pago SET estado_sunat = ? WHERE comprobante_id = ?";
        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, comp.getEstado_sunat().name());
            pstmt.setInt(2, comp.getComprobante_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public ComprobantePago buscarPorPedido(int pedidoId) {
//        String sql = "{CALL SP_BuscarComprobantePorPedido(?)}";
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            cstmt.setInt(1, pedidoId);
//            try(ResultSet rs = cstmt.executeQuery()) {
//                if (rs.next()) {
//                    return mapearObjeto(rs);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    @Override
//    public List<ComprobantePago> listarPorFechas(Date fechaInicio, Date fechaFin) {
//        List<ComprobantePago> list = new ArrayList<>();
//        String sql = "{CALL SP_ListarComprobantesPorFechas(?, ?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            cstmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
//            cstmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
//
//            try(ResultSet rs = cstmt.executeQuery()) {
//                while (rs.next()) {
//                    list.add(mapearObjeto(rs));
//                }
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<ComprobantePago> listarPorEstadoSunat(EstadoSunat estado) {
//        List<ComprobantePago> list = new ArrayList<>();
//        String sql = "{CALL SP_ListarComprobantesPorEstado(?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            cstmt.setString(1, estado.name());
//
//            try(ResultSet rs = cstmt.executeQuery()) {
//                while (rs.next()) {
//                    list.add(mapearObjeto(rs));
//                }
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
        if(estadoStr != null) comp.setEstado_sunat(EstadoSunat.valueOf(estadoStr));

        comp.setUrl_xml(rs.getString("url_xml"));
        comp.setUrl_pdf(rs.getString("url_pdf"));
        comp.setFec_emision(rs.getDate("fec_emision"));

        // Mapear relación: Pedido
        int idPedido = rs.getInt("pedido_id");
        if (!rs.wasNull()) {
            Pedido pedido = new Pedido();
            pedido.setPedidoId(idPedido); // Asegúrate de que el método en tu clase se llame getPedidoId/setPedidoId
            comp.setPedido(pedido);
        }

        // Mapear relación: TipoComprobante (Como ya es una CLASE, creamos el objeto)
        int idTipoComp = rs.getInt("tipo_comprobante_id");
        if (!rs.wasNull()) {
            TipoComprobante tipoComp = new TipoComprobante();
            tipoComp.setTipo_comprobante_id(idTipoComp);
            comp.setTipoComprobante(tipoComp);
        }

        // Mapear relación: TipoDocIdentidad (Como ya es una CLASE, creamos el objeto)
        int idTipoDoc = rs.getInt("tipo_doc_identidad_id");
        if (!rs.wasNull()) {
            TipoDocIdentidad tipoDoc = new TipoDocIdentidad();
            tipoDoc.setTipoDocId(idTipoDoc);
            comp.setTipoDocIdentidad(tipoDoc);
        }

        return comp;
    }
}