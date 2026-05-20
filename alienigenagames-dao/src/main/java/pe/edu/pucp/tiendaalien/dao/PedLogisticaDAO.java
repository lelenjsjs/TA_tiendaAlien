package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.PedLogistica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PedLogisticaDAO extends BaseDAO<PedLogistica,Integer> {
    PedLogistica save(PedLogistica t, Connection con) throws SQLException;
    PedLogistica loadById(Integer id);
    PedLogistica save(PedLogistica t);
    PedLogistica update(PedLogistica t);
    void remove(PedLogistica t);
    List<PedLogistica> listAll();
}
