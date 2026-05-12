package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import java.util.List;

public interface IAgenciaEnvioBL {
    List<AgenciaEnvio> listarAgenciasDeEnvio() throws BusinessLogicException;
    AgenciaEnvio cargarAgenciaPorNombre(String nombre) throws BusinessLogicException;
    AgenciaEnvio cargarAgenciaPorId(Integer id) throws BusinessLogicException;
    AgenciaEnvio registrarAgencia(AgenciaEnvio agenciaEnvio) throws BusinessLogicException;
    AgenciaEnvio modificarAgencia(AgenciaEnvio agenciaEnvio) throws BusinessLogicException;
    void eliminarAgencia(AgenciaEnvio agenciaEnvio) throws BusinessLogicException;
}
