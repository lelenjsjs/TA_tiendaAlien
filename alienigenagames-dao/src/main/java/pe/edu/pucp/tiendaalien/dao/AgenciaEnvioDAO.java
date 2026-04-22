package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import java.util.List;

public interface AgenciaEnvioDAO extends BaseDAO<AgenciaEnvio, Integer> {
    AgenciaEnvio load(Integer id);
    AgenciaEnvio save(AgenciaEnvio t);
    AgenciaEnvio update(AgenciaEnvio t);
    void remove(AgenciaEnvio t);
    List<AgenciaEnvio> listAll();
}
