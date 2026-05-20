package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;

public interface IComprobanteBL {
    ComprobantePago registrarComprobantePago(ComprobantePago comprobantePago);
    ComprobantePago cargarComprobantePagoPorId(Integer id);
    ComprobantePago modificarComprobantePago(ComprobantePago comprobantePago);
    void eliminarComprobantePago(ComprobantePago comprobantePago);
}
