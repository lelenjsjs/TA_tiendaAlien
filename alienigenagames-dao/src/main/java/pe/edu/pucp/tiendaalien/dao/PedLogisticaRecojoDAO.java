package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogisticaRecojo;
import pe.edu.pucp.tiendaalien.model.logistica.EstadoLogistica;

import java.util.Date;
import java.util.List;

public interface PedLogisticaRecojoDAO extends BaseDAO<PedLogisticaRecojo, Integer> {

    List<PedLogisticaRecojo> listAll();

//    List<PedLogisticaRecojo> listarPorEstado(EstadoLogistica estado);
//
//    List<PedLogisticaRecojo> listarPorVencer(Date fechaActual);
}