package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.ITipoComprobanteBL;
import pe.edu.pucp.tiendaalien.dao.TipoComprobanteDAO;
import pe.edu.pucp.tiendaalien.dao.impl.TipoComprobanteDAOImpl;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;

import java.util.ArrayList;
import java.util.List;

public class TipoComprobanteBLImpl implements ITipoComprobanteBL {

    private TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOImpl();

    @Override
    public List<TipoComprobante> listarTodo() throws BusinessLogicException {
        List<TipoComprobante> lista = tipoComprobanteDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public TipoComprobante cargarPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) throw new BusinessLogicException("ID no válido.");
        TipoComprobante tc = tipoComprobanteDAO.loadById(id);
        return (tc != null) ? tc : new TipoComprobante();
    }

    @Override
    public TipoComprobante registrarTipoComprobante(TipoComprobante tc) throws BusinessLogicException {
        validar(tc, false);
        return tipoComprobanteDAO.save(tc);
    }

    @Override
    public TipoComprobante modificarTipoComprobante(TipoComprobante tc) throws BusinessLogicException {
        validar(tc, true);
        return tipoComprobanteDAO.update(tc);
    }

    @Override
    public void eliminarTipoComprobante(TipoComprobante tc) throws BusinessLogicException {
        if (tc == null || tc.getTipo_comprobante_id() <= 0) {
            throw new BusinessLogicException("Se requiere un ID válido para la desactivación.");
        }
        tipoComprobanteDAO.remove(tc);
    }

    private void validar(TipoComprobante tc, boolean esModif) throws BusinessLogicException {
        if (tc == null) throw new BusinessLogicException("El tipo de comprobante no puede ser nulo.");
        if (esModif && tc.getTipo_comprobante_id() <= 0) throw new BusinessLogicException("ID requerido.");

        if (tc.getCodigo_sunat() == null || tc.getCodigo_sunat().trim().isEmpty()) {
            throw new BusinessLogicException("El código SUNAT es obligatorio.");
        }
        if (tc.getCodigo_sunat().length() > 10) {
            throw new BusinessLogicException("El código SUNAT no puede exceder los 10 caracteres.");
        }

        if (tc.getDescripcion() == null || tc.getDescripcion().trim().isEmpty()) {
            throw new BusinessLogicException("La descripción es obligatoria.");
        }
        if (tc.getDescripcion().length() > 100) {
            throw new BusinessLogicException("La descripción no puede exceder los 100 caracteres.");
        }
    }
}