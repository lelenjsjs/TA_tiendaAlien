package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogisticaEnvio;
//import pe.edu.pucp.tiendaalien.model.logistica.ModalidadPago;
//import pe.edu.pucp.tiendaalien.model.logistica.EstadoLogistica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PedLogisticaEnvioDAO extends BaseDAO<PedLogisticaEnvio, Integer> {
    List<PedLogisticaEnvio> listAll();
    PedLogisticaEnvio save(PedLogisticaEnvio envio, Connection con) throws SQLException;

//    PedLogisticaEnvio buscarPorTracking(String codTracking);
//
//    List<PedLogisticaEnvio> listarPorModalidadPago(ModalidadPago modalidad);
//
//    List<PedLogisticaEnvio> listarPorAgencia(int agenciaId);
//
//    List<PedLogisticaEnvio> listarPorEstado(EstadoLogistica estado);

}