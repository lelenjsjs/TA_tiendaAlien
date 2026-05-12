package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.ITarifaEnvioBL;
import pe.edu.pucp.tiendaalien.dao.TarifaEnvioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.TarifaEnvioDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;

import java.util.ArrayList;
import java.util.List;

public class TarifaEnvioBLImpl implements ITarifaEnvioBL {

    private TarifaEnvioDAO tarifaDAO = new TarifaEnvioDAOImpl();

    @Override
    public List<TarifaEnvio> listarTarifas() throws BusinessLogicException {
        List<TarifaEnvio> lista = tarifaDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public TarifaEnvio registrarTarifa(TarifaEnvio t) throws BusinessLogicException {
        validar(t, false);
        return tarifaDAO.save(t);
    }

    @Override
    public TarifaEnvio modificarTarifa(TarifaEnvio t) throws BusinessLogicException {
        validar(t, true);
        return tarifaDAO.update(t);
    }

    @Override
    public void eliminarTarifa(TarifaEnvio t) throws BusinessLogicException {
        if (t == null || t.getTarifaId() <= 0) throw new BusinessLogicException("ID no válido.");
        tarifaDAO.remove(t);
    }

    private void validar(TarifaEnvio t, boolean esModif) throws BusinessLogicException {
        if (t == null) throw new BusinessLogicException("La tarifa no puede ser nula.");
        if (esModif && t.getTarifaId() <= 0) throw new BusinessLogicException("ID requerido.");

        if (t.getCosto() < 0) throw new BusinessLogicException("El costo no puede ser negativo.");
        if (t.getDiasEstimados() < 0) throw new BusinessLogicException("Los días no pueden ser negativos.");

        if (t.getAgenciaEnvio() == null || t.getAgenciaEnvio().getAgenciaId() <= 0) {
            throw new BusinessLogicException("Debe estar asociado a una Agencia de Envío válida.");
        }
    }
}