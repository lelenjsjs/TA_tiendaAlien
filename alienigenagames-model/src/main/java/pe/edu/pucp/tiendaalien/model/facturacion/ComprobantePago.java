package pe.edu.pucp.tiendaalien.model.facturacion;

import pe.edu.pucp.tiendaalien.model.ventas.Pedido;
import java.util.Date;

public class ComprobantePago {
    // Identificador único correlativo al script SQL
    private int comprobanteId;

    // Relaciones con otras entidades (Objetos, no solo IDs)
    private Pedido pedido;
    private TipoComprobante tipoComprobante;
    private TipoDocIdentidad tipoDocIdentidad;

    // Atributos de cabecera
    private String nroSerie;
    private String correlativo;
    private String clienteNroDoc;
    private String clienteDenominacion;
    private String direccionFiscal;

    // Atributos monetarios
    private double montoTotal;
    private double montoIgv;
    private double montoGravado;

    // Atributos de control y SUNAT
    private EstadoSunat estadoSunat;
    private String urlXml;
    private String urlPdf;
    private Date fecEmision;

    // Constructor con valores por defecto según tu script SQL
    public ComprobantePago() {
        this.estadoSunat = EstadoSunat.PENDIENTE;
        this.clienteNroDoc = "00000000";
        this.clienteDenominacion = "Clientes Varios";
    }

    // --- GETTERS Y SETTERS (Indispensables para el DAO) ---

    public int getComprobanteId() { return comprobanteId; }
    public void setComprobanteId(int comprobanteId) { this.comprobanteId = comprobanteId; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public TipoComprobante getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(TipoComprobante tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public TipoDocIdentidad getTipoDocIdentidad() { return tipoDocIdentidad; }
    public void setTipoDocIdentidad(TipoDocIdentidad tipoDocIdentidad) { this.tipoDocIdentidad = tipoDocIdentidad; }

    public String getNroSerie() { return nroSerie; }
    public void setNroSerie(String nroSerie) { this.nroSerie = nroSerie; }

    public String getCorrelativo() { return correlativo; }
    public void setCorrelativo(String correlativo) { this.correlativo = correlativo; }

    public String getClienteNroDoc() { return clienteNroDoc; }
    public void setClienteNroDoc(String clienteNroDoc) { this.clienteNroDoc = clienteNroDoc; }

    public String getClienteDenominacion() { return clienteDenominacion; }
    public void setClienteDenominacion(String clienteDenominacion) { this.clienteDenominacion = clienteDenominacion; }

    public String getDireccionFiscal() { return direccionFiscal; }
    public void setDireccionFiscal(String direccionFiscal) { this.direccionFiscal = direccionFiscal; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    public double getMontoIgv() { return montoIgv; }
    public void setMontoIgv(double montoIgv) { this.montoIgv = montoIgv; }

    public double getMontoGravado() { return montoGravado; }
    public void setMontoGravado(double montoGravado) { this.montoGravado = montoGravado; }

    public EstadoSunat getEstadoSunat() { return estadoSunat; }
    public void setEstadoSunat(EstadoSunat estadoSunat) { this.estadoSunat = estadoSunat; }

    public String getUrlXml() { return urlXml; }
    public void setUrlXml(String urlXml) { this.urlXml = urlXml; }

    public String getUrlPdf() { return urlPdf; }
    public void setUrlPdf(String urlPdf) { this.urlPdf = urlPdf; }

    public Date getFecEmision() { return fecEmision; }
    public void setFecEmision(Date fecEmision) { this.fecEmision = fecEmision; }
}