package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.HistorialEstadoPed;

import java.util.List;

public interface HistorialEstadoPedDAO extends BaseDAO<HistorialEstadoPed,Integer> {
    HistorialEstadoPed load (Integer id);
    HistorialEstadoPed save (HistorialEstadoPed t);
    HistorialEstadoPed update (HistorialEstadoPed t);
    void remove (HistorialEstadoPed t);
    List<HistorialEstadoPed> listAll ();
}
