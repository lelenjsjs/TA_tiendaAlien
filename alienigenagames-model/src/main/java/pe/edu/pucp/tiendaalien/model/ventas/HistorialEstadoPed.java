package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IHistorialEstadoPedidoBL;
import pe.edu.pucp.tiendaalien.dao.HistorialEstadoPedidoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.HistorialEstadoPedidoDAOImpl;


import pe.edu.pucp.tiendaalien.model.ventas.HistorialEstadoPed;

import java.util.ArrayList;
import java.util.List;

public class HistorialEstadoPedidoBLImpl implements IHistorialEstadoPedidoBL {

    private HistorialEstadoPedidoDAO historialDAO = new HistorialEstadoPedidoDAOImpl();

    @Override
    public List<HistorialEstadoPed> listarHistorialPorPedido(Integer idPedido) throws BusinessLogicException {
        if (idPedido == null || idPedido <= 0) {
            throw new BusinessLogicException("Se requiere el ID del pedido para listar su historial.");
        }
        List<HistorialEstadoPed> lista = historialDAO.listByPedido(idPedido);
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public HistorialEstadoPed cargarHistorialPorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID del registro de historial no es válido.");
        }
        HistorialEstadoPed historial = historialDAO.load(id);
        return (historial != null) ? historial : new HistorialEstadoPed();
    }

    @Override
    public HistorialEstadoPed registrarHistorial(HistorialEstadoPed historial) throws BusinessLogicException {
        // false = Nuevo registro
        validar(historial, false);
        return historialDAO.save(historial);
    }

    @Override
    public HistorialEstadoPed modificarHistorial(HistorialEstadoPed historial) throws BusinessLogicException {
        // true = Edición
        validar(historial, true);
        return historialDAO.update(historial);
    }

    @Override
    public void eliminarHistorial(HistorialEstadoPed historial) throws BusinessLogicException {
        // Corregido: Ahora usa getHistorialEstadoId()
        if (historial == null || historial.getHistorialEstadoId() == null || historial.getHistorialEstadoId() <= 0) {
            throw new BusinessLogicException("Debe especificar un registro de historial válido para eliminar.");
        }
        historialDAO.remove(historial);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES (REGLAS DE NEGOCIO)
    // =========================================================================
    private void validar(HistorialEstadoPed h, boolean esModificacion) throws BusinessLogicException {
        if (h == null) {
            throw new BusinessLogicException("El registro del historial no puede ser nulo.");
        }

        // 1. Validar ID en caso de modificación (Corregido: getHistorialEstadoId)
        if (esModificacion && (h.getHistorialEstadoId() == null || h.getHistorialEstadoId() <= 0)) {
            throw new BusinessLogicException("El ID del historial es obligatorio para modificarlo.");
        }

        // 2. Validar Relaciones (Llave Foránea al Pedido)
        if (h.getPedido() == null || h.getPedido().getPedidoId() == null || h.getPedido().getPedidoId() <= 0) {
            throw new BusinessLogicException("El historial debe estar asociado obligatoriamente a un Pedido válido.");
        }

        // 3. Validar el Estado (VARCHAR 50 NOT NULL)
        if (h.getEstado() == null || h.getEstado().trim().isEmpty()) {
            throw new BusinessLogicException("El estado del pedido no puede estar vacío.");
        }

        if (h.getEstado().length() > 50) {
            throw new BusinessLogicException("El texto del estado no puede exceder los 50 caracteres.");
        }
    }
}