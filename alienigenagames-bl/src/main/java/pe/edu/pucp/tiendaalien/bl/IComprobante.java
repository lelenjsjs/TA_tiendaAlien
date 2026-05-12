package pe.edu.pucp.tiendaalien.bl;


import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;
import java.util.List;

public interface IComprobantePagoBL {
    List<ComprobantePago> listarComprobantes() throws BusinessLogicException;
    ComprobantePago cargarComprobantePorId(Integer id) throws BusinessLogicException;
    ComprobantePago registrarComprobante(ComprobantePago comprobante) throws BusinessLogicException;
    ComprobantePago modificarComprobante(ComprobantePago comprobante) throws BusinessLogicException;
    void eliminarComprobante(ComprobantePago comprobante) throws BusinessLogicException;
}