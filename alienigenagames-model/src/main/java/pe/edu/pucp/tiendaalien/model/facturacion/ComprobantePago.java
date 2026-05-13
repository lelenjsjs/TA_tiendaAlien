package pe.edu.pucp.tiendaalien.model.facturacion;

import java.util.Date;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

public class ComprobantePago {

    private int id;
    private String nro_serie;
    private String correlativo;
    private String cliente_nro_doc = "00000000";
    private String cliente_denominacion = "Clientes Varios";
    private String direccion_fiscal;
    private double monto_total;
    private double monto_igv;
    private double monto_gravado;
    private EstadoSunat estado_sunat;
    private String url_xml;
    private String url_pdf;
    private Date fec_emision;
    private Pedido pedido;
    private TipoComprobante tipoComprobante;
    private TipoDocIdentidad tipoDocIdentidad;

    public ComprobantePago() {}


}