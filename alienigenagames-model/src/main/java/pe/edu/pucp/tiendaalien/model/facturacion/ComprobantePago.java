package pe.edu.pucp.tiendaalien.model.facturacion;

import java.util.Date;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

public class ComprobantePago {

    private int comprobante_id;
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

    // Getters y Setters
    public int getComprobante_id() { return comprobante_id; }
    public void setComprobante_id(int comprobante_id) { this.comprobante_id = comprobante_id; }

    public EstadoSunat getEstado_sunat() { return estado_sunat; }
    public void setEstado_sunat(EstadoSunat estado_sunat) { this.estado_sunat = estado_sunat; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public TipoComprobante getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(TipoComprobante tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public String getNro_serie() { return nro_serie; }
    public void setNro_serie(String nro_serie) { this.nro_serie = nro_serie; }

    public String getCorrelativo() { return correlativo; }
    public void setCorrelativo(String correlativo) { this.correlativo = correlativo; }

    public String getCliente_nro_doc() { return cliente_nro_doc; }
    public void setCliente_nro_doc(String cliente_nro_doc) { this.cliente_nro_doc = cliente_nro_doc; }

    public String getCliente_denominacion() { return cliente_denominacion; }
    public void setCliente_denominacion(String cliente_denominacion) { this.cliente_denominacion = cliente_denominacion; }

    public String getDireccion_fiscal() { return direccion_fiscal; }
    public void setDireccion_fiscal(String direccion_fiscal) { this.direccion_fiscal = direccion_fiscal; }

    public double getMonto_total() { return monto_total; }
    public void setMonto_total(double monto_total) { this.monto_total = monto_total; }

    public double getMonto_igv() { return monto_igv; }
    public void setMonto_igv(double monto_igv) { this.monto_igv = monto_igv; }

    public double getMonto_gravado() { return monto_gravado; }
    public void setMonto_gravado(double monto_gravado) { this.monto_gravado = monto_gravado; }

    public String getUrl_xml() { return url_xml; }
    public void setUrl_xml(String url_xml) { this.url_xml = url_xml; }

    public String getUrl_pdf() { return url_pdf; }
    public void setUrl_pdf(String url_pdf) { this.url_pdf = url_pdf; }

    public Date getFec_emision() { return fec_emision; }
    public void setFec_emision(Date fec_emision) { this.fec_emision = fec_emision; }

    public TipoDocIdentidad getTipoDocIdentidad() {
        return tipoDocIdentidad;
    }

    public void setTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad) {
        this.tipoDocIdentidad = tipoDocIdentidad;
    }
}