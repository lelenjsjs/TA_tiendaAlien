package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogistica;

import java.util.List;

public interface PedLogisticaDAO extends BaseDAO<PedLogistica,Integer> {
    PedLogistica load(Integer id);
    PedLogistica save(PedLogistica t);
    PedLogistica update(PedLogistica t);
    void remove(PedLogistica t);
    List<PedLogistica> listAll();
}
