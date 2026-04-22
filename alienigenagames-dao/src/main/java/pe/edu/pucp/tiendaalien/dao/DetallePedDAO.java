package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;

import java.util.List;


public interface DetallePedDAO extends BaseDAO<DetallePed,Integer> {
    DetallePed load(Integer id);
    DetallePed save(DetallePed t);
    DetallePed update(DetallePed t);
    void remove(DetallePed t);
    List<DetallePed> listAll();
}
