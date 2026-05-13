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
    public List<TipoComprobante> listarTiposComprobante() throws BusinessLogicException {
        try {
            List<TipoComprobante> lista = tipoComprobanteDAO.listAll();
            return (lista != null) ? lista : new ArrayList<>();
        } catch (Exception e) {
            throw new BusinessLogicException("Error al listar tipos de comprobante: " + e.getMessage());
        }
    }

    @Override
    public TipoComprobante cargarTipoCompPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) throw new BusinessLogicException("ID no válido.");
        try {
            TipoComprobante tc = tipoComprobanteDAO.loadById(id);
            if (tc == null) throw new BusinessLogicException("No se encontró el tipo de comprobante.");
            return tc;
        } catch (Exception e) {
            throw new BusinessLogicException("Error al cargar tipo: " + e.getMessage());
        }
    }

    @Override
    public TipoComprobante registrarTipoComprobante(TipoComprobante tc) throws BusinessLogicException {
        validar(tc, false);
        try {
            return tipoComprobanteDAO.save(tc);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al registrar: " + e.getMessage());
        }
    }

    @Override
    public TipoComprobante modificarTipoComprobante(TipoComprobante tc) throws BusinessLogicException {
        validar(tc, true);
        try {
            return tipoComprobanteDAO.update(tc);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminarTipoComprobante(TipoComprobante tc) throws BusinessLogicException {
        // Corregido: getTipoComprobanteId()
        if (tc == null || tc.getTipoComprobanteId() <= 0) {
            throw new BusinessLogicException("Se requiere un ID válido para la eliminación.");
        }
        try {
            tipoComprobanteDAO.remove(tc);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al eliminar: " + e.getMessage());
        }
    }

    private void validar(TipoComprobante tc, boolean esModif) throws BusinessLogicException {
        if (tc == null) throw new BusinessLogicException("El tipo de comprobante no puede ser nulo.");

        // Corregido: getTipoComprobanteId()
        if (esModif && tc.getTipoComprobanteId() <= 0) {
            throw new BusinessLogicException("ID requerido para modificación.");
        }

        // Corregido: getCodigoSunat()
        if (tc.getCodigoSunat() == null || tc.getCodigoSunat().trim().isEmpty()) {
            throw new BusinessLogicException("El código SUNAT es obligatorio.");
        }
        if (tc.getCodigoSunat().length() > 10) {
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