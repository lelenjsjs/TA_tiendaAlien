package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;
import java.util.List;

public interface ComprobantePagoDAO extends BaseDAO<ComprobantePago, Integer> {
    List<ComprobantePago> listAll();
    ComprobantePago loadById(Integer id);
    ComprobantePago save(ComprobantePago t);
    ComprobantePago update(ComprobantePago t);
    void remove(ComprobantePago t);
}