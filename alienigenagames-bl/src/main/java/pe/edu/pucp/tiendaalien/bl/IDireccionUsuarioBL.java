package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;

import java.util.List;

public interface IDireccionUsuarioBL {
    List<DireccionUsuario> listarDireccionesPorUsuario(Integer idUsuario) throws BusinessLogicException;
    DireccionUsuario cargarDireccionPorId(Integer id) throws BusinessLogicException;
    DireccionUsuario registrarDireccion(DireccionUsuario direccion) throws BusinessLogicException;
    DireccionUsuario modificarDireccion(DireccionUsuario direccion) throws BusinessLogicException;
    void eliminarDireccion(DireccionUsuario direccion) throws BusinessLogicException;
}
