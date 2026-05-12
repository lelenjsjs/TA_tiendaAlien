package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IComprobantePagoBL;
import pe.edu.pucp.tiendaalien.dao.ComprobantePagoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.ComprobantePagoDAOImpl;
import pe.edu.pucp.tiendaalien.model.comprobante.ComprobantePago;

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
            throw new BusinessLogicException("El ID del comprobante debe ser mayor a cero.");
        }
        ComprobantePago comprobante = comprobantePagoDAO.load(id);
        return (comprobante != null) ? comprobante : new ComprobantePago();
    }

    @Override
    public ComprobantePago registrarComprobante(ComprobantePago comprobante) throws BusinessLogicException {
        // false = Es un registro nuevo. No exigimos que tenga un ID de comprobante previo.
        validar(comprobante, false);
        return comprobantePagoDAO.save(comprobante);
    }

    @Override
    public ComprobantePago modificarComprobante(ComprobantePago comprobante) throws BusinessLogicException {
        // true = Es modificación. Exigimos que el ID de comprobante ya exista.
        validar(comprobante, true);
        return comprobantePagoDAO.update(comprobante);
    }

    @Override
    public void eliminarComprobante(ComprobantePago comprobante) throws BusinessLogicException {
        if (comprobante == null || comprobante.getComprobanteId() == null || comprobante.getComprobanteId() <= 0) {
            throw new BusinessLogicException("Se requiere un comprobante válido y con ID para eliminarlo.");
        }
        comprobantePagoDAO.remove(comprobante);
    }

    // =========================================================================
    // MÓDULO DE VALIDACIONES (REGLAS DE NEGOCIO)
    // =========================================================================

    private void validar(ComprobantePago c, boolean esModificacion) throws BusinessLogicException {

        // 1. Validar que el objeto no llegue vacío
        if (c == null) {
            throw new BusinessLogicException("El comprobante no puede ser nulo.");
        }

        // 2. Validar ID en caso de modificación (El interruptor)
        if (esModificacion && (c.getComprobanteId() == null || c.getComprobanteId() <= 0)) {
            throw new BusinessLogicException("El ID del comprobante es obligatorio para realizar una modificación.");
        }

        // 3. Validar las Llaves Foráneas (Asociaciones obligatorias)
        if (c.getPedido() == null || c.getPedido().getPedidoId() == null || c.getPedido().getPedidoId() <= 0) {
            throw new BusinessLogicException("El comprobante debe estar obligatoriamente asociado a un Pedido válido.");
        }

        if (c.getTipoComprobante() == null || c.getTipoComprobante().getTipoComprobanteId() == null || c.getTipoComprobante().getTipoComprobanteId() <= 0) {
            throw new BusinessLogicException("Debe seleccionar un tipo de comprobante (Boleta/Factura) válido.");
        }

        // 4. Validar datos del Cliente
        if (c.getClienteNroDoc() == null || c.getClienteNroDoc().trim().isEmpty()) {
            throw new BusinessLogicException("El número de documento del cliente es obligatorio.");
        }
        if (c.getClienteDenominacion() == null || c.getClienteDenominacion().trim().isEmpty()) {
            throw new BusinessLogicException("El nombre/razón social del cliente es obligatorio.");
        }

        // 5. Validar Reglas Financieras (Montos)
        if (c.getMontoTotal() < 0 || c.getMontoIgv() < 0 || c.getMontoGravado() < 0) {
            throw new BusinessLogicException("Los montos del comprobante (Total, IGV, Gravado) no pueden ser negativos.");
        }

        // 6. Validar Estado SUNAT
        if (c.getEstadoSunat() == null || c.getEstadoSunat().trim().isEmpty()) {
            throw new BusinessLogicException("El estado SUNAT no puede estar vacío.");
        }
    }
}