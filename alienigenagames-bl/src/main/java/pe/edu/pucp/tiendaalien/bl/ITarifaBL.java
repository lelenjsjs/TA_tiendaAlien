package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.logistica.TarifaEnvio;

public interface ITarifaBL {
    TarifaEnvio agregarTarifa(TarifaEnvio t);
    TarifaEnvio cargarTarifaPorId(Integer id);
    TarifaEnvio modificarTarifa(TarifaEnvio t);
    void eliminarTarifa(TarifaEnvio t);
}
