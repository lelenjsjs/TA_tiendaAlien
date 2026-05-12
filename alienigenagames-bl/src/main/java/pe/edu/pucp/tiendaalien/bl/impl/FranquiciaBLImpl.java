package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IFranquiciaBL;
import pe.edu.pucp.tiendaalien.dao.FranquiciaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.FranquiciaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;

import java.util.ArrayList;
import java.util.List;

public class FranquiciaBLImpl implements IFranquiciaBL {

    private FranquiciaDAO franquiciaDAO = new FranquiciaDAOImpl();

    @Override
    public List<Franquicia> listarFranquicias() throws BusinessLogicException {
        List<Franquicia> lista = franquiciaDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public Franquicia cargarFranquiciaPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID de la franquicia debe ser mayor a cero.");
        }
        Franquicia franquicia = franquiciaDAO.load(id);
        return (franquicia != null) ? franquicia : new Franquicia();
    }

    @Override
    public Franquicia registrarFranquicia(Franquicia franquicia) throws BusinessLogicException {
        // false = Es un registro nuevo, no exigimos ID
        validar(franquicia, false);
        return franquiciaDAO.save(franquicia);
    }

    @Override
    public Franquicia modificarFranquicia(Franquicia franquicia) throws BusinessLogicException {
        // true = Es modificación, exigimos que tenga un ID válido
        validar(franquicia, true);
        return franquiciaDAO.update(franquicia);
    }

    @Override
    public void eliminarFranquicia(Franquicia franquicia) throws BusinessLogicException {
        if (franquicia == null || franquicia.getFranquiciaId() == null || franquicia.getFranquiciaId() <= 0) {
            throw new BusinessLogicException("Se requiere una franquicia válida y con ID para eliminarla.");
        }
        franquiciaDAO.remove(franquicia);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES (REGLAS DE NEGOCIO)
    // =========================================================================

    private void validar(Franquicia f, boolean esModificacion) throws BusinessLogicException {

        // 1. Validar que el objeto no sea nulo
        if (f == null) {
            throw new BusinessLogicException("La franquicia no puede ser nula.");
        }

        // 2. Validar ID en caso de modificación
        if (esModificacion && (f.getFranquiciaId() == null || f.getFranquiciaId() <= 0)) {
            throw new BusinessLogicException("El ID de la franquicia es obligatorio para realizar una modificación.");
        }

        // 3. Validar el Nombre (Según tu SQL: VARCHAR(100) NOT NULL)
        if (f.getNombre() == null || f.getNombre().trim().isEmpty()) {
            throw new BusinessLogicException("El nombre de la franquicia es obligatorio y no puede estar en blanco.");
        }

        if (f.getNombre().length() > 100) {
            throw new BusinessLogicException("El nombre de la franquicia es demasiado largo (máximo 100 caracteres).");
        }
    }
}