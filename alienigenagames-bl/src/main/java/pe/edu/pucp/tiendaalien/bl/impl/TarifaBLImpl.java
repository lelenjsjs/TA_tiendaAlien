package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.ITarifaBL;
import pe.edu.pucp.tiendaalien.dao.TarifaEnvioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.TarifaEnvioDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;

public class TarifaBLImpl implements ITarifaBL {
    private TarifaEnvioDAO tarifaEnvioDAO = new TarifaEnvioDAOImpl();

    @Override
    public TarifaEnvio agregarTarifa(TarifaEnvio t) {
        return tarifaEnvioDAO.save(t);
    }

    @Override
    public TarifaEnvio cargarTarifaPorId(Integer id) {
        return tarifaEnvioDAO.loadById(id);
    }

    @Override
    public TarifaEnvio modificarTarifa(TarifaEnvio t) {
        return tarifaEnvioDAO.update(t);
    }

    @Override
    public void eliminarTarifa(TarifaEnvio t) {
        tarifaEnvioDAO.remove(t);
    }
}
