package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;
import java.util.List;

public interface ITipoComprobanteBL {
    List<TipoComprobante> listarTiposComprobante() throws BusinessLogicException;
    TipoComprobante cargarTipoCompPorId(Integer id) throws BusinessLogicException;
    TipoComprobante registrarTipoComprobante(TipoComprobante tipo) throws BusinessLogicException;
    TipoComprobante modificarTipoComprobante(TipoComprobante tipo) throws BusinessLogicException;
    void eliminarTipoComprobante(TipoComprobante tipo) throws BusinessLogicException;
}
