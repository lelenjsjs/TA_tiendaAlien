package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.comprobante.TipoComprobante;
import java.util.List;

public interface ITipoComprobanteBL {
    List<TipoComprobante> listarTiposComprobante() throws BusinessLogicException;
    TipoComprobante cargarTipoPorId(Integer id) throws BusinessLogicException;
    TipoComprobante registrarTipo(TipoComprobante tipo) throws BusinessLogicException;
    TipoComprobante modificarTipo(TipoComprobante tipo) throws BusinessLogicException;
    void eliminarTipo(TipoComprobante tipo) throws BusinessLogicException;
}