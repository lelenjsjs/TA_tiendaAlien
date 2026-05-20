package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;

public interface ITipoDocIdentidadBL {
    TipoDocIdentidad registrarTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad);
    TipoDocIdentidad modificarTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad);
    TipoDocIdentidad cargarTipoDocIdentidadPorId(Integer id);
    void eliminarTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad);

}
