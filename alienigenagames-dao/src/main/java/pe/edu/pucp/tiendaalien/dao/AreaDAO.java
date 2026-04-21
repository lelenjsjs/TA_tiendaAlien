package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;

import java.util.List;

public interface AreaDAO extends BaseDAO<Area, Integer> {
    List<Area> listAll();
}
