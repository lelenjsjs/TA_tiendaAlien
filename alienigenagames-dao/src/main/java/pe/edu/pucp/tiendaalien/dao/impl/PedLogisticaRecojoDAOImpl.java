package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.PedLogisticaRecojoDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogisticaRecojo;
import pe.edu.pucp.tiendaalien.model.logistica.EstadoLogistica;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedLogisticaRecojoDAOImpl implements PedLogisticaRecojoDAO {

    @Override
    public List<PedLogisticaRecojo> listAll() {
        List<PedLogisticaRecojo> list = new ArrayList<>();
        String sql = "SELECT p.ped_logistica_id, p.estado_logistico, p.receptor_nombre, p.receptor_tipo_doc, p.receptor_nro_doc, p.receptor_cel, " +
                "r.fec_lim_recojo, r.fec_recojo_real " +
                "FROM ped_logistica p " +
                "INNER JOIN ped_logistica_recojo r ON p.ped_logistica_id = r.recojo_id";

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
    public PedLogisticaRecojo load(Integer id) {
        String sql = "SELECT p.ped_logistica_id, p.estado_logistico, p.receptor_nombre, p.receptor_tipo_doc, p.receptor_nro_doc, p.receptor_cel, " +
                "r.fec_lim_recojo, r.fec_recojo_real " +
                "FROM ped_logistica p " +
                "INNER JOIN ped_logistica_recojo r ON p.ped_logistica_id = r.recojo_id " +
                "WHERE p.ped_logistica_id = ?";

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
    public PedLogisticaRecojo save(PedLogisticaRecojo recojo) {
        String sqlPadre = "INSERT INTO ped_logistica (estado_logistico, receptor_nombre, receptor_tipo_doc, receptor_nro_doc, receptor_cel) VALUES (?, ?, ?, ?, ?)";
        String sqlHijo = "INSERT INTO ped_logistica_recojo (recojo_id, fec_lim_recojo, fec_recojo_real) VALUES (?, ?, ?)";

        try(Connection connection = DBManager.getInstance().getConnection()) {

            // 1. Insertar en el Padre
            try(PreparedStatement pstmtPadre = connection.prepareStatement(sqlPadre, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPadre.setString(1, recojo.getEstadoLogistica() != null ? recojo.getEstadoLogistica().name() : null);
                pstmtPadre.setString(2, recojo.getReceptorNombre());
                pstmtPadre.setString(3, recojo.getReceptorTipoDoc());
                pstmtPadre.setString(4, recojo.getReceptorNroDoc());
                pstmtPadre.setString(5, recojo.getReceptorCel());
                pstmtPadre.executeUpdate();

                try (ResultSet generatedKeys = pstmtPadre.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        recojo.setPedLogisticaId(newId);
                        recojo.setRecojoId(newId);
                    }
                }
            }

            // 2. Insertar en el Hijo
            try(PreparedStatement pstmtHijo = connection.prepareStatement(sqlHijo)) {
                pstmtHijo.setInt(1, recojo.getRecojoId());
                pstmtHijo.setDate(2, recojo.getFecLimRecojo() != null ? new java.sql.Date(recojo.getFecLimRecojo().getTime()) : null);
                pstmtHijo.setDate(3, recojo.getFecRecojoReal() != null ? new java.sql.Date(recojo.getFecRecojoReal().getTime()) : null);
                pstmtHijo.executeUpdate();
            }

            return recojo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PedLogisticaRecojo update(PedLogisticaRecojo recojo) {
        String sqlPadre = "UPDATE ped_logistica SET estado_logistico = ?, receptor_nombre = ?, receptor_tipo_doc = ?, receptor_nro_doc = ?, receptor_cel = ? WHERE ped_logistica_id = ?";
        String sqlHijo = "UPDATE ped_logistica_recojo SET fec_lim_recojo = ?, fec_recojo_real = ? WHERE recojo_id = ?";

        try(Connection connection = DBManager.getInstance().getConnection()) {

            // Actualizar Padre
            try(PreparedStatement pstmtPadre = connection.prepareStatement(sqlPadre)) {
                pstmtPadre.setString(1, recojo.getEstadoLogistica() != null ? recojo.getEstadoLogistica().name() : null);
                pstmtPadre.setString(2, recojo.getReceptorNombre());
                pstmtPadre.setString(3, recojo.getReceptorTipoDoc());
                pstmtPadre.setString(4, recojo.getReceptorNroDoc());
                pstmtPadre.setString(5, recojo.getReceptorCel());
                pstmtPadre.setInt(6, recojo.getPedLogisticaId());
                pstmtPadre.executeUpdate();
            }

            // Actualizar Hijo
            try(PreparedStatement pstmtHijo = connection.prepareStatement(sqlHijo)) {
                pstmtHijo.setDate(1, recojo.getFecLimRecojo() != null ? new java.sql.Date(recojo.getFecLimRecojo().getTime()) : null);
                pstmtHijo.setDate(2, recojo.getFecRecojoReal() != null ? new java.sql.Date(recojo.getFecRecojoReal().getTime()) : null);
                pstmtHijo.setInt(3, recojo.getRecojoId());
                pstmtHijo.executeUpdate();
            }
            return recojo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(PedLogisticaRecojo recojo) {
        // Eliminado Lógico
        recojo.setEstadoLogistica(EstadoLogistica.DEVUELTO_A_ORIGEN); // O INTENTO_FALLIDO
        String sql = "UPDATE ped_logistica SET estado_logistico = ? WHERE ped_logistica_id = ?";

        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recojo.getEstadoLogistica().name());
            pstmt.setInt(2, recojo.getPedLogisticaId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<PedLogisticaRecojo> listarPorEstado(EstadoLogistica estado) {
//        List<PedLogisticaRecojo> list = new ArrayList<>();
//        String sql = "{CALL SP_ListarRecojosPorEstado(?)}";
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
//
//    @Override
//    public List<PedLogisticaRecojo> listarPorVencer(Date fechaActual) {
//        List<PedLogisticaRecojo> list = new ArrayList<>();
//        String sql = "{CALL SP_ListarRecojosPorVencer(?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            // Pasamos la fecha como java.sql.Date
//            cstmt.setDate(1, new java.sql.Date(fechaActual.getTime()));
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

    private PedLogisticaRecojo mapearObjeto(ResultSet rs) throws SQLException {
        PedLogisticaRecojo recojo = new PedLogisticaRecojo();

        // Mapeo atributos del PADRE
        recojo.setPedLogisticaId(rs.getInt("ped_logistica_id"));
        recojo.setRecojoId(rs.getInt("ped_logistica_id"));

        String estadoStr = rs.getString("estado_logistico");
        if(estadoStr != null) recojo.setEstadoLogistica(EstadoLogistica.valueOf(estadoStr));

        recojo.setReceptorNombre(rs.getString("receptor_nombre"));
        recojo.setReceptorTipoDoc(rs.getString("receptor_tipo_doc"));
        recojo.setReceptorNroDoc(rs.getString("receptor_nro_doc"));
        recojo.setReceptorCel(rs.getString("receptor_cel"));

        // Mapeo atributos del HIJO
        recojo.setFecLimRecojo(rs.getDate("fec_lim_recojo"));
        recojo.setFecRecojoReal(rs.getDate("fec_recojo_real"));

        return recojo;
    }
}