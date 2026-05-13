package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.ventas.HistorialEstadoPed;

import java.sql.Connection;
import java.sql.SQLException;

public interface HistorialEstadoPedDAO extends BaseDAO<HistorialEstadoPed, Integer> {
    HistorialEstadoPed save(HistorialEstadoPed h, Connection con) throws SQLException;
}
