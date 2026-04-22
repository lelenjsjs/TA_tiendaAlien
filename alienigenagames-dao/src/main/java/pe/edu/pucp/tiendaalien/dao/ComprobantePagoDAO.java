package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;
//import pe.edu.pucp.tiendaalien.model.facturacion.EstadoSunat;

//import java.util.Date;
import java.util.List;

public interface ComprobantePagoDAO extends BaseDAO<ComprobantePago, Integer> {

    // CRUD básico
    List<ComprobantePago> listAll();

    // Métodos de Negocio (Stored Procedures)
//    ComprobantePago buscarPorPedido(int pedidoId);
//    List<ComprobantePago> listarPorFechas(Date fechaInicio, Date fechaFin);
//    List<ComprobantePago> listarPorEstadoSunat(EstadoSunat estado);
}