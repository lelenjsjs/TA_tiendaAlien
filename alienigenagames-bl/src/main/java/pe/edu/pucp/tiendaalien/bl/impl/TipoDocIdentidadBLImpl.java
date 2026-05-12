package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.ITipoDocIdentidadBL;
import pe.edu.pucp.tiendaalien.dao.TipoDocIdentidadDAO;
import pe.edu.pucp.tiendaalien.dao.impl.TipoDocIdentidadDAOImpl;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;

import java.util.ArrayList;
import java.util.List;

public class TipoDocIdentidadBLImpl implements ITipoDocIdentidadBL {

    private TipoDocIdentidadDAO tipoDocDAO = new TipoDocIdentidadDAOImpl();

    @Override
    public List<TipoDocIdentidad> listarTodo() throws BusinessLogicException {
        List<TipoDocIdentidad> lista = tipoDocDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public TipoDocIdentidad cargarPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) throw new BusinessLogicException("ID no válido.");
        TipoDocIdentidad doc = tipoDocDAO.loadById(id);
        return (doc != null) ? doc : new TipoDocIdentidad();
    }

    @Override
    public TipoDocIdentidad registrar(TipoDocIdentidad t) throws BusinessLogicException {
        validar(t, false);
        return tipoDocDAO.save(t);
    }

    @Override
    public TipoDocIdentidad modificar(TipoDocIdentidad t) throws BusinessLogicException {
        validar(t, true);
        return tipoDocDAO.update(t);
    }

    @Override
    public void eliminar(TipoDocIdentidad t) throws BusinessLogicException {
        if (t == null || t.getTipoDocId() <= 0) throw new BusinessLogicException("ID no válido.");
        tipoDocDAO.remove(t);
    }

    private void validar(TipoDocIdentidad t, boolean esModif) throws BusinessLogicException {
        if (t == null) throw new BusinessLogicException("El objeto no puede ser nulo.");
        if (esModif && t.getTipoDocId() <= 0) throw new BusinessLogicException("ID requerido.");

        if (t.getCodigoSunat() == null || t.getCodigoSunat().trim().isEmpty())
            throw new BusinessLogicException("El código SUNAT es obligatorio.");
        if (t.getCodigoSunat().length() > 10)
            throw new BusinessLogicException("El código SUNAT es demasiado largo.");

        if (t.getDescripcion() == null || t.getDescripcion().trim().isEmpty())
            throw new BusinessLogicException("La descripción es obligatoria.");
    }
}