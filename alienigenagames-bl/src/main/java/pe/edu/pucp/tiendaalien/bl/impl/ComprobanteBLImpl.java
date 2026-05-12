package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IComprobantePagoBL;
import pe.edu.pucp.tiendaalien.dao.ComprobantePagoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.ComprobantePagoDAOImpl;

// ¡Ruta corregida!
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;

import java.util.ArrayList;
import java.util.List;

public class ComprobantePagoBLImpl implements IComprobantePagoBL {

    private ComprobantePagoDAO comprobantePagoDAO = new ComprobantePagoDAOImpl();

    @Override
    public List<ComprobantePago> listarComprobantes() throws BusinessLogicException {
        List<ComprobantePago> lista = comprobantePagoDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public ComprobantePago cargarComprobantePorId(Integer id) throws BusinessLogicException {
        if (id == null || id <= 0) {
            throw new BusinessLogicException("El ID del comprobante no es válido.");
        }
        ComprobantePago comprobante = comprobantePagoDAO.loadById(id);
        return (comprobante != null) ? comprobante : new ComprobantePago();
    }

    @Override
    public ComprobantePago registrarComprobante(ComprobantePago comprobante) throws BusinessLogicException {
        // false = Nuevo registro
        validar(comprobante, false);
        return comprobantePagoDAO.save(comprobante);
    }

    @Override
    public ComprobantePago modificarComprobante(ComprobantePago comprobante) throws BusinessLogicException {
        // true = Edición
        validar(comprobante, true);
        return comprobantePagoDAO.update(comprobante);
    }

    @Override
    public void eliminarComprobante(ComprobantePago comprobante) throws BusinessLogicException {
        if (comprobante == null || comprobante.getComprobante_id() == null || comprobante.getComprobante_id() <= 0) {
            throw new BusinessLogicException("Debe especificar un comprobante válido para anular.");
        }
        // Llama al remove del DAO (que ya hace el borrado lógico/anulación)
        comprobantePagoDAO.remove(comprobante);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES
    // =========================================================================
    private void validar(ComprobantePago c, boolean esModificacion) throws BusinessLogicException {
        if (c == null) {
            throw new BusinessLogicException("El comprobante no puede ser nulo.");
        }

        if (esModificacion && (c.getComprobante_id() == null || c.getComprobante_id() <= 0)) {
            throw new BusinessLogicException("Se requiere el ID para modificar el comprobante.");
        }

        // Validación de relaciones obligatorias
        if (c.getPedido() == null || c.getPedido().getPedidoId() == null || c.getPedido().getPedidoId() <= 0) {
            throw new BusinessLogicException("El comprobante debe estar asociado a un pedido válido.");
        }

        if (c.getTipoComprobante() == null || c.getTipoComprobante().getTipo_comprobante_id() == null || c.getTipoComprobante().getTipo_comprobante_id() <= 0) {
            throw new BusinessLogicException("El tipo de comprobante es obligatorio.");
        }

        // Blindaje financiero
        if (c.getMonto_total() == null || c.getMonto_total() < 0) {
            throw new BusinessLogicException("El monto total del comprobante no puede ser negativo.");
        }
    }
}