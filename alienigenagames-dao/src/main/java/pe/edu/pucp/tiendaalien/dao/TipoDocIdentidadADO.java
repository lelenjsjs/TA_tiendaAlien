package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;
import java.util.List;

public interface TipoDocIdentidadDAO extends BaseDAO<TipoDocIdentidad, Integer> {
    List<TipoDocIdentidad> listAll();
    TipoDocIdentidad loadById(Integer id);
    TipoDocIdentidad save(TipoDocIdentidad t);
    TipoDocIdentidad update(TipoDocIdentidad t);
    void remove(TipoDocIdentidad t);
}