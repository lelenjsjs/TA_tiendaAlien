package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.logistica.SucursalAgencia;

import java.util.List;

public interface ISucursalBL {
    List<SucursalAgencia> listarSucursales();
    List<SucursalAgencia> listarPorAgencia(int agenciaId);
    SucursalAgencia obtenerPorId(int id);
    SucursalAgencia registrarSucursal(SucursalAgencia sucursal);
}