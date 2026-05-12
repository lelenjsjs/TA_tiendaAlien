package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IAgenciaEnvioBL;
import pe.edu.pucp.tiendaalien.dao.AgenciaEnvioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.AgenciaEnvioDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import java.util.ArrayList;
import java.util.List;

public class AgenciaEnvioBLImpl implements IAgenciaEnvioBL {
    // Atributo DAO
    private AgenciaEnvioDAO agenciaEnvioDAO = new AgenciaEnvioDAOImpl();

    @Override
    public List<AgenciaEnvio> listarAgenciasDeEnvio() throws BusinessLogicException {
        // Verifico si la lista esta vacia
        List <AgenciaEnvio> lista = agenciaEnvioDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public AgenciaEnvio cargarAgenciaPorNombre(String nombre) throws BusinessLogicException {
        AgenciaEnvio agenciaEnvio = agenciaEnvioDAO.loadByName(nombre);
        return (agenciaEnvio != null) ? agenciaEnvio : new AgenciaEnvio();
    }

    @Override
    public AgenciaEnvio cargarAgenciaPorId(Integer id) throws BusinessLogicException {
        AgenciaEnvio agenciaEnvio = agenciaEnvioDAO.loadById(id);
        return (agenciaEnvio != null) ? agenciaEnvio : new AgenciaEnvio();
    }

    @Override
    public AgenciaEnvio registrarAgencia(AgenciaEnvio agenciaEnvio) throws BusinessLogicException {
        return agenciaEnvioDAO.save(agenciaEnvio);
    }

    @Override
    public AgenciaEnvio modificarAgencia(AgenciaEnvio agenciaEnvio) throws BusinessLogicException {
        return agenciaEnvioDAO.update(agenciaEnvio);
    }

    @Override
    public void eliminarAgencia(AgenciaEnvio agenciaEnvio) throws BusinessLogicException {
        agenciaEnvioDAO.remove(agenciaEnvio);
    }


}
