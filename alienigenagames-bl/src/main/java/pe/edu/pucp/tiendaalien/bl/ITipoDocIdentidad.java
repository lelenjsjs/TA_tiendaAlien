package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.usuario.TipoDocIdentidad;
import java.util.List;

public interface ITipoDocIdentidadBL {
    List<TipoDocIdentidad> listarTiposDocIdentidad() throws BusinessLogicException;
    TipoDocIdentidad cargarTipoDocPorId(Integer id) throws BusinessLogicException;
    TipoDocIdentidad registrarTipoDoc(TipoDocIdentidad tipoDoc) throws BusinessLogicException;
    TipoDocIdentidad modificarTipoDoc(TipoDocIdentidad tipoDoc) throws BusinessLogicException;
    void eliminarTipoDoc(TipoDocIdentidad tipoDoc) throws BusinessLogicException;
}