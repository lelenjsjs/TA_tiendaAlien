package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IUbigeoBL;
import pe.edu.pucp.tiendaalien.dao.UbigeoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UbigeoDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import java.util.ArrayList;
import java.util.List;

public class UbigeoBLImpl implements IUbigeoBL {

    private UbigeoDAO ubigeoDAO = new UbigeoDAOImpl();

    @Override
    public List<Ubigeo> listarTodo() throws BusinessLogicException {
        List<Ubigeo> lista = ubigeoDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public Ubigeo cargarPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) throw new BusinessLogicException("ID no válido.");
        Ubigeo ubi = ubigeoDAO.loadById(id);
        return (ubi != null) ? ubi : new Ubigeo();
    }

    @Override
    public Ubigeo registrarUbigeo(Ubigeo u) throws BusinessLogicException {
        validar(u, false);
        return ubigeoDAO.save(u);
    }

    @Override
    public Ubigeo modificarUbigeo(Ubigeo u) throws BusinessLogicException {
        validar(u, true);
        return ubigeoDAO.update(u);
    }

    @Override
    public void eliminarUbigeo(Ubigeo u) throws BusinessLogicException {
        if (u == null || u.getUbigeoId() <= 0) throw new BusinessLogicException("ID no válido.");
        ubigeoDAO.remove(u);
    }

    private void validar(Ubigeo u, boolean esModif) throws BusinessLogicException {
        if (u == null) throw new BusinessLogicException("El Ubigeo no puede ser nulo.");
        if (esModif && u.getUbigeoId() <= 0) throw new BusinessLogicException("ID requerido para modificar.");

        if (u.getCodigo() == null || u.getCodigo().trim().isEmpty())
            throw new BusinessLogicException("El código de ubigeo es obligatorio.");
        if (u.getCodigo().length() > 10)
            throw new BusinessLogicException("El código no puede exceder los 10 caracteres.");

        if (u.getDepartamento() == null || u.getDepartamento().trim().isEmpty())
            throw new BusinessLogicException("El departamento es obligatorio.");

        if (u.getProvincia() == null || u.getProvincia().trim().isEmpty())
            throw new BusinessLogicException("La provincia es obligatoria.");

        if (u.getDistrito() == null || u.getDistrito().trim().isEmpty())
            throw new BusinessLogicException("El distrito es obligatorio.");
    }
}