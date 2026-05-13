package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.ISucursalBL;
import pe.edu.pucp.tiendaalien.dao.SucursalDAO;
import pe.edu.pucp.tiendaalien.dao.impl.SucursalDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.SucursalAgencia;
import java.util.List;

public class SucursalBLImpl implements ISucursalBL {

    private SucursalDAO sucursalDAO = new SucursalDAOImpl();

    @Override
    public List<SucursalAgencia> listarSucursales() {
        return sucursalDAO.listAll();
    }

    @Override
    public List<SucursalAgencia> listarPorAgencia(int agenciaId) {
        return sucursalDAO.listByAgency(agenciaId);
    }

    @Override
    public SucursalAgencia obtenerPorId(int id) {
        return sucursalDAO.loadById(id);
    }

    @Override
    public SucursalAgencia registrarSucursal(SucursalAgencia sucursal) {
        return sucursalDAO.save(sucursal);
    }
}