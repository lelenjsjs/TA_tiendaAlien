package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IDireccionUsuarioBL;
import pe.edu.pucp.tiendaalien.dao.DireccionUsuarioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.DireccionUsuarioDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuario.DireccionUsuario;

import java.util.ArrayList;
import java.util.List;

public class DireccionUsuarioBLImpl implements IDireccionUsuarioBL {

    private DireccionUsuarioDAO direccionUsuarioDAO = new DireccionUsuarioDAOImpl();

    @Override
    public List<DireccionUsuario> listarDireccionesPorUsuario(Integer idUsuario) throws BusinessLogicException {
        if (idUsuario == null || idUsuario <= 0) {
            throw new BusinessLogicException("Se requiere el ID del usuario para listar sus direcciones.");
        }
        // Asumiendo que crearás este método específico en tu DAO
        List<DireccionUsuario> lista = direccionUsuarioDAO.listByUsuario(idUsuario);
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public DireccionUsuario cargarDireccionPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID de la dirección no es válido.");
        }
        DireccionUsuario direccion = direccionUsuarioDAO.load(id);
        return (direccion != null) ? direccion : new DireccionUsuario();
    }

    @Override
    public DireccionUsuario registrarDireccion(DireccionUsuario direccion) throws BusinessLogicException {
        // false = Nuevo registro
        validar(direccion, false);
        return direccionUsuarioDAO.save(direccion);
    }

    @Override
    public DireccionUsuario modificarDireccion(DireccionUsuario direccion) throws BusinessLogicException {
        // true = Edición
        validar(direccion, true);
        return direccionUsuarioDAO.update(direccion);
    }

    @Override
    public void eliminarDireccion(DireccionUsuario direccion) throws BusinessLogicException {
        if (direccion == null || direccion.getDireccionId() == null || direccion.getDireccionId() <= 0) {
            throw new BusinessLogicException("Debe especificar una dirección válida para eliminarla.");
        }
        direccionUsuarioDAO.remove(direccion);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES (REGLAS DE NEGOCIO)
    // =========================================================================
    private void validar(DireccionUsuario d, boolean esModificacion) throws BusinessLogicException {
        if (d == null) {
            throw new BusinessLogicException("La dirección no puede ser nula.");
        }

        // 1. Validar ID en caso de modificación
        if (esModificacion && (d.getDireccionId() == null || d.getDireccionId() <= 0)) {
            throw new BusinessLogicException("El ID de la dirección es obligatorio para modificar.");
        }

        // 2. Validar Relaciones (Foreign Keys obligatorias)
        if (d.getUsuario() == null || d.getUsuario().getUsuarioId() == null || d.getUsuario().getUsuarioId() <= 0) {
            throw new BusinessLogicException("La dirección debe estar asociada obligatoriamente a un Usuario válido.");
        }

        if (d.getUbigeo() == null || d.getUbigeo().getUbigeoId() == null || d.getUbigeo().getUbigeoId() <= 0) {
            throw new BusinessLogicException("La dirección debe estar asociada a un distrito (Ubigeo) válido.");
        }

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