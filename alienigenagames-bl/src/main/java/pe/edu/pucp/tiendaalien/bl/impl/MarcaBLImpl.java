package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IMarcaBL;
import pe.edu.pucp.tiendaalien.dao.MarcaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.MarcaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Marca;

import java.util.ArrayList;
import java.util.List;

public class MarcaBLImpl implements IMarcaBL {

    private MarcaDAO marcaDAO = new MarcaDAOImpl();

    @Override
    public List<Marca> listarMarcas() throws BusinessLogicException {
        List<Marca> lista = marcaDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public Marca cargarMarcaPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID de la marca debe ser mayor a cero.");
        }
        Marca marca = marcaDAO.load(id);
        return (marca != null) ? marca : new Marca();
    }

    @Override
    public Marca registrarMarca(Marca marca) throws BusinessLogicException {
        // false = Es un registro nuevo, no exigimos ID previo
        validar(marca, false);
        return marcaDAO.save(marca);
    }

    @Override
    public Marca modificarMarca(Marca marca) throws BusinessLogicException {
        // true = Es modificación, exigimos que tenga un ID válido
        validar(marca, true);
        return marcaDAO.update(marca);
    }

    @Override
    public void eliminarMarca(Marca marca) throws BusinessLogicException {
        if (marca == null || marca.getMarcaId() == null || marca.getMarcaId() <= 0) {
            throw new BusinessLogicException("Se requiere una marca válida y con ID para eliminarla.");
        }
        marcaDAO.remove(marca);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES (REGLAS DE NEGOCIO)
    // =========================================================================

    private void validar(Marca m, boolean esModificacion) throws BusinessLogicException {

        // 1. Validar que el objeto no sea nulo
        if (m == null) {
            throw new BusinessLogicException("La marca no puede ser nula.");
        }

        // 2. Validar ID en caso de modificación
        if (esModificacion && (m.getMarcaId() == null || m.getMarcaId() <= 0)) {
            throw new BusinessLogicException("El ID de la marca es obligatorio para realizar una modificación.");
        }

        // 3. Validar el Nombre (Según tu SQL: VARCHAR(100) NOT NULL)
        if (m.getNombre() == null || m.getNombre().trim().isEmpty()) {
            throw new BusinessLogicException("El nombre de la marca es obligatorio y no puede estar en blanco.");
        }

        if (m.getNombre().length() > 100) {
            throw new BusinessLogicException("El nombre de la marca es demasiado largo (máximo 100 caracteres).");
        }
    }
}