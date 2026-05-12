package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;
import java.util.List;

public interface ITarifaEnvioBL {
    // Listamos por agencia para mayor eficiencia
    List<TarifaEnvio> listarTarifasPorAgencia(Integer idAgencia) throws BusinessLogicException;
    TarifaEnvio cargarTarifaPorId(Integer id) throws BusinessLogicException;
    TarifaEnvio registrarTarifa(TarifaEnvio tarifa) throws BusinessLogicException;
    TarifaEnvio modificarTarifa(TarifaEnvio tarifa) throws BusinessLogicException;
    void eliminarTarifa(TarifaEnvio tarifa) throws BusinessLogicException;
}