package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface DetallePedDAO extends BaseDAO<DetallePed,Integer> {
    DetallePed loadById(Integer id);
    DetallePed save(DetallePed t, Connection con) throws SQLException;
    DetallePed update(DetallePed t, Connection con) throws SQLException;
    void  remove(DetallePed t, Connection con) throws SQLException;
    List<DetallePed> listAll();
}
