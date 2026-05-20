package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;

import java.util.List;

public interface TarifaEnvioDAO extends BaseDAO<TarifaEnvio, Integer> {
    TarifaEnvio loadById(Integer id);
    TarifaEnvio save(TarifaEnvio t);
    TarifaEnvio update(TarifaEnvio t);
    void remove(TarifaEnvio t);
    List<TarifaEnvio> listAll();
}
