package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IComprobanteBL;
import pe.edu.pucp.tiendaalien.dao.ComprobanteDAO;
import pe.edu.pucp.tiendaalien.dao.impl.ComprobanteDAOImpl;
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;

public class ComprobanteBLImpl implements IComprobanteBL {
    private ComprobanteDAO comprobanteDAO = new ComprobanteDAOImpl();
    @Override
    public ComprobantePago registrarComprobantePago(ComprobantePago comprobantePago) {
        return comprobanteDAO.save(comprobantePago);
    }

    @Override
    public ComprobantePago cargarComprobantePagoPorId(Integer id) {
        return comprobanteDAO.loadById(id);
    }

    @Override
    public ComprobantePago modificarComprobantePago(ComprobantePago comprobantePago) {
        return comprobanteDAO.update(comprobantePago);
    }

    @Override
    public void eliminarComprobantePago(ComprobantePago comprobantePago) {
        comprobanteDAO.remove(comprobantePago);
    }
}
