package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.PedLogisticaEnvioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogisticaEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.EstadoLogistica;
import pe.edu.pucp.tiendaalien.model.logistica.TipoEnvio;
import pe.edu.pucp.tiendaalien.model.logistica.ModalidadPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedLogisticaEnvioDAOImpl implements PedLogisticaEnvioDAO {

    @Override
    public List<PedLogisticaEnvio> listAll() {
        List<PedLogisticaEnvio> list = new ArrayList<>();
        // Hacemos INNER JOIN para traer los datos del Padre y del Hijo a la vez
        String sql = "SELECT * " +
                "FROM ped_logistica p " +
                "INNER JOIN ped_logistica_envio e ON p.ped_logistica_id = e.envio_id";

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
    public PedLogisticaEnvio load(Integer id) {
        String sql = "SELECT * " +
                "FROM ped_logistica p " +
                "INNER JOIN ped_logistica_envio e ON p.ped_logistica_id = e.envio_id " +
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
    public PedLogisticaEnvio save(PedLogisticaEnvio envio) {
        // 1. Insertar primero en la tabla PADRE (ped_logistica)
        String sqlPadre = "INSERT INTO ped_logistica (estado_logistico, receptor_nombre, receptor_tipo_doc, receptor_nro_doc, receptor_cel) VALUES (?, ?, ?, ?, ?)";
        String sqlHijo = "INSERT INTO ped_logistica_envio (envio_id, tipo_envio, modalidad_pago, direccion_entrega, referencia, cod_tracking, costo_envio, fec_estimada, notas_adicionales, fec_entrega_real) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DBManager.getInstance().getConnection()) {

            // Iniciamos la inserción en el padre
            try(PreparedStatement pstmtPadre = connection.prepareStatement(sqlPadre, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPadre.setString(1, envio.getEstadoLogistica() != null ? envio.getEstadoLogistica().name() : null);
                pstmtPadre.setString(2, envio.getReceptorNombre());
                pstmtPadre.setString(3, envio.getReceptorTipoDoc());
                pstmtPadre.setString(4, envio.getReceptorNroDoc());
                pstmtPadre.setString(5, envio.getReceptorCel());

                pstmtPadre.executeUpdate();

                // Obtener el ID generado para el padre
                try (ResultSet generatedKeys = pstmtPadre.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        envio.setPedLogisticaId(newId); // ID de la clase padre
                        envio.setEnvioId(newId);        // ID de la clase hija
                    }
                }
            }

            // 2. Insertar en la tabla HIJA usando el ID que acabamos de generar
            try(PreparedStatement pstmtHijo = connection.prepareStatement(sqlHijo)) {
                pstmtHijo.setInt(1, envio.getEnvioId());
                pstmtHijo.setString(2, envio.getTipoEnvio() != null ? envio.getTipoEnvio().name() : null);
                pstmtHijo.setString(3, envio.getModalidad() != null ? envio.getModalidad().name() : null);
                pstmtHijo.setString(4, envio.getDireccionEntrega());
                pstmtHijo.setString(5, envio.getReferenciaEnvio());
                pstmtHijo.setString(6, envio.getCodTracking());
                pstmtHijo.setDouble(7, envio.getCostoEnvio());

                // Manejo de Fechas (util.Date a sql.Date)
                pstmtHijo.setDate(8, envio.getFec_estimadaEntrega() != null ? new java.sql.Date(envio.getFec_estimadaEntrega().getTime()) : null);
                pstmtHijo.setString(9, envio.getNotasAdicionales());
                pstmtHijo.setDate(10, envio.getFecEntregaReal() != null ? new java.sql.Date(envio.getFecEntregaReal().getTime()) : null);

                pstmtHijo.executeUpdate();
            }

            return envio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PedLogisticaEnvio update(PedLogisticaEnvio envio) {
        String sqlPadre = "UPDATE ped_logistica SET estado_logistico = ?, receptor_nombre = ?, receptor_tipo_doc = ?, receptor_nro_doc = ?, receptor_cel = ? WHERE ped_logistica_id = ?";
        String sqlHijo = "UPDATE ped_logistica_envio SET tipo_envio = ?, modalidad_pago = ?, direccion_entrega = ?, referencia = ?, cod_tracking = ?, costo_envio = ?, fec_estimada = ?, notas_adicionales = ?, fec_entrega_real = ? WHERE envio_id = ?";

        try(Connection connection = DBManager.getInstance().getConnection()) {

            // Actualizar Padre
            try(PreparedStatement pstmtPadre = connection.prepareStatement(sqlPadre)) {
                pstmtPadre.setString(1, envio.getEstadoLogistica() != null ? envio.getEstadoLogistica().name() : null);
                pstmtPadre.setString(2, envio.getReceptorNombre());
                pstmtPadre.setString(3, envio.getReceptorTipoDoc());
                pstmtPadre.setString(4, envio.getReceptorNroDoc());
                pstmtPadre.setString(5, envio.getReceptorCel());
                pstmtPadre.setInt(6, envio.getPedLogisticaId());
                pstmtPadre.executeUpdate();
            }

            // Actualizar Hijo
            try(PreparedStatement pstmtHijo = connection.prepareStatement(sqlHijo)) {
                pstmtHijo.setString(1, envio.getTipoEnvio() != null ? envio.getTipoEnvio().name() : null);
                pstmtHijo.setString(2, envio.getModalidad() != null ? envio.getModalidad().name() : null);
                pstmtHijo.setString(3, envio.getDireccionEntrega());
                pstmtHijo.setString(4, envio.getReferenciaEnvio());
                pstmtHijo.setString(5, envio.getCodTracking());
                pstmtHijo.setDouble(6, envio.getCostoEnvio());
                pstmtHijo.setDate(7, envio.getFec_estimadaEntrega() != null ? new java.sql.Date(envio.getFec_estimadaEntrega().getTime()) : null);
                pstmtHijo.setString(8, envio.getNotasAdicionales());
                pstmtHijo.setDate(9, envio.getFecEntregaReal() != null ? new java.sql.Date(envio.getFecEntregaReal().getTime()) : null);
                pstmtHijo.setInt(10, envio.getEnvioId());
                pstmtHijo.executeUpdate();
            }
            return envio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(PedLogisticaEnvio envio) {
        // Eliminado Lógico: Tu clase no tiene campo "activo", así que en
        // e-commerce lo normal es cambiar el estado a "INTENTO_FALLIDO" o "DEVUELTO_A_ORIGEN"
        envio.setEstadoLogistica(EstadoLogistica.DEVUELTO_A_ORIGEN);

        String sql = "UPDATE ped_logistica SET estado_logistico = ? WHERE ped_logistica_id = ?";
        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, envio.getEstadoLogistica().name());
            pstmt.setInt(2, envio.getPedLogisticaId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PedLogisticaEnvio mapearObjeto(ResultSet rs) throws SQLException {
        PedLogisticaEnvio envio = new PedLogisticaEnvio();

        // Mapeo atributos del PADRE
        envio.setPedLogisticaId(rs.getInt("ped_logistica_id"));
        envio.setEnvioId(rs.getInt("id_ped_logistica_id")); // Asumimos que comparten el mismo ID

        String estadoStr = rs.getString("estado_logistico");
        if(estadoStr != null) envio.setEstadoLogistica(EstadoLogistica.valueOf(estadoStr));

        envio.setReceptorNombre(rs.getString("receptor_nombre"));
        envio.setReceptorTipoDoc(rs.getString("receptor_tipo_doc"));
        envio.setReceptorNroDoc(rs.getString("receptor_nro_doc"));
        envio.setReceptorCel(rs.getString("receptor_cel"));

        // Mapeo atributos del HIJO
        String tipoStr = rs.getString("tipo_envio");
        if(tipoStr != null) envio.setTipoEnvio(TipoEnvio.valueOf(tipoStr));

        String modStr = rs.getString("modalidad_pago");
        if(modStr != null) envio.setModalidad(ModalidadPago.valueOf(modStr));

        envio.setDireccionEntrega(rs.getString("direccion_entrega"));
        envio.setReferenciaEnvio(rs.getString("referencia"));
        envio.setCodTracking(rs.getString("cod_tracking"));
        envio.setCostoEnvio(rs.getDouble("costo_envio"));
        envio.setFec_estimadaEntrega(rs.getDate("fec_estimada"));
        envio.setNotasAdicionales(rs.getString("notas_adicionales"));
        envio.setFecEntregaReal(rs.getDate("fec_entrega_real"));

        return envio;
    }

//    @Override
//    public PedLogisticaEnvio buscarPorTracking(String codTracking) {
//        // La sintaxis de CallableStatement usa llaves {} y la palabra CALL
//        String sql = "{CALL SP_BuscarEnvioPorTracking(?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            cstmt.setString(1, codTracking);
//
//            try(ResultSet rs = cstmt.executeQuery()) {
//                if (rs.next()) {
//                    return mapearObjeto(rs);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null; // Si no encuentra el tracking, devuelve null
//    }

//    @Override
//    public List<PedLogisticaEnvio> listarPorModalidadPago(ModalidadPago modalidad) {
//        List<PedLogisticaEnvio> list = new ArrayList<>();
//        String sql = "{CALL SP_ListarEnviosPorModalidad(?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            // Convertimos el Enum a String para mandarlo a la base de datos
//            cstmt.setString(1, modalidad.name());
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

//    @Override
//    public List<PedLogisticaEnvio> listarPorAgencia(int agenciaId) {
//        List<PedLogisticaEnvio> list = new ArrayList<>();
//        // Llamamos al Stored Procedure de la base de datos
//        String sql = "{CALL SP_ListarEnviosPorAgencia(?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            cstmt.setInt(1, agenciaId);
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

//    @Override
//    public List<PedLogisticaEnvio> listarPorEstado(EstadoLogistica estado) {
//        List<PedLogisticaEnvio> list = new ArrayList<>();
//        // Llamamos al Stored Procedure de la base de datos
//        String sql = "{CALL SP_ListarEnviosPorEstado(?)}";
//
//        try(Connection connection = DBManager.getInstance().getConnection();
//            CallableStatement cstmt = connection.prepareCall(sql)) {
//
//            // Pasamos el Enum como String
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
}