package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.logistica.SucursalAgencia;
import java.util.List;

public interface SucursalDAO extends BaseDAO<SucursalAgencia, Integer> {
    List<SucursalAgencia> listAll();

    List<SucursalAgencia> listByAgency(int agenciaId);
}
