package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IDireccionUsuarioBL;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.dao.DireccionUsuarioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.DireccionUsuarioDAOImpl;

import java.util.List;

public class DireccionUsuarioBLImpl implements IDireccionUsuarioBL {
    private DireccionUsuarioDAO direccionUsuarioDAO= new DireccionUsuarioDAOImpl();
    // Create
    @Override
    public DireccionUsuario registrarDireccion(DireccionUsuario direccion) throws BusinessLogicException {
        // false = Nuevo registro
        validar(direccion);
        return direccionUsuarioDAO.save(direccion);
    }

    // Read
    @Override
    public DireccionUsuario cargarDireccionPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID de la dirección no es válido.");
        }
        DireccionUsuario direccion = direccionUsuarioDAO.loadById(id);
        return (direccion != null) ? direccion : new DireccionUsuario();
    }

    // Update
    @Override
    public DireccionUsuario modificarDireccion(DireccionUsuario direccion) throws BusinessLogicException {
        // true = Edición
        validar(direccion);
        return direccionUsuarioDAO.update(direccion);
    }

    // Delete
    @Override
    public void eliminarDireccion(DireccionUsuario direccion) throws BusinessLogicException {
        if (direccion == null || direccion.getId() == null || direccion.getId() <= 0) {
            throw new BusinessLogicException("Debe especificar una dirección válida para eliminarla.");
        }
        direccionUsuarioDAO.remove(direccion);
    }

    // Listar todos
    @Override
    public List<DireccionUsuario> listarDireccionesPorUsuario(Integer idUsuario) throws BusinessLogicException {
        return List.of();
    }


    // Validar
    private void validar(DireccionUsuario d) throws BusinessLogicException {
        // 1. Validar que no sea nulo
        if (d == null) {
            throw new BusinessLogicException("La dirección no puede ser nula.");
        }

        // 2. Validar que el USUARIO exista
        // throw new BusinessLogicException("La dirección debe estar asociada obligatoriamente a un Usuario registrado.")

        // 3. Validar que el UBIGEO exista
        // throw new BusinessLogicException("La dirección debe estar asociada obligatoriamente a un Ubigeo registrado.")

        // 3. Validar Dirección Física (VARCHAR 250 NOT NULL)
        if (d.getDireccion() == null || d.getDireccion().trim().isEmpty()) {
            throw new BusinessLogicException("El texto de la dirección no puede estar vacío.");
        }

        if (d.getDireccion().length() > 250) {
            throw new BusinessLogicException("La dirección no puede exceder los 250 caracteres.");
        }

        // 4. Validar Referencia (Puede ser nula, pero si existe, máximo 250 caracteres)
        if (d.getReferencia() != null && d.getReferencia().length() > 250) {
            throw new BusinessLogicException("La referencia no puede exceder los 250 caracteres.");
        }
    }





}
