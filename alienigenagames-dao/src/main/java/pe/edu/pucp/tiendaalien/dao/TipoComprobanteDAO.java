package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;

import java.util.List;

public interface TipoComprobanteDAO extends BaseDAO<TipoComprobante, Integer> {
    TipoComprobante load(Integer id);
    TipoComprobante save(TipoComprobante t);
    TipoComprobante update(TipoComprobante t);
    void remove(TipoComprobante t);
    List<TipoComprobante> listAll();
}
