package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.ITipoDocIdentidadBL;
import pe.edu.pucp.tiendaalien.dao.TipoDocIdentidadDAO;
import pe.edu.pucp.tiendaalien.dao.impl.TipoDocIdentidadDAOImpl;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;

public class TipoDocIdentidadBLImpl implements ITipoDocIdentidadBL {
    private TipoDocIdentidadDAO tipoDocIdentidadDAO =  new TipoDocIdentidadDAOImpl();

    @Override
    public TipoDocIdentidad registrarTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad) {
        return tipoDocIdentidadDAO.save(tipoDocIdentidad);
    }

    @Override
    public TipoDocIdentidad modificarTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad) {
        return tipoDocIdentidadDAO.update(tipoDocIdentidad);
    }

    @Override
    public TipoDocIdentidad cargarTipoDocIdentidadPorId(Integer id) {
        return tipoDocIdentidadDAO.loadById(id);
    }

    @Override
    public void eliminarTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad) {
        tipoDocIdentidadDAO.remove(tipoDocIdentidad);
    }
}
