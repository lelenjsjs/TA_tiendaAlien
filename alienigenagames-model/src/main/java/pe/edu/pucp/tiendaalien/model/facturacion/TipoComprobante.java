package pe.edu.pucp.tiendaalien.model.facturacion;

public class TipoComprobante {
    private int tipoComprobanteId;
    private String codigoSunat;
    private String descripcion;

    public TipoComprobante() {}

    public TipoComprobante(int tipoComprobanteId, String codigoSunat, String descripcion) {
        this.tipoComprobanteId = tipoComprobanteId;
        this.codigoSunat = codigoSunat;
        this.descripcion = descripcion;
    }

    // Getters y Setters con CamelCase
    public int getTipoComprobanteId() { return tipoComprobanteId; }
    public void setTipoComprobanteId(int tipoComprobanteId) { this.tipoComprobanteId = tipoComprobanteId; }

    public String getCodigoSunat() { return codigoSunat; }
    public void setCodigoSunat(String codigoSunat) { this.codigoSunat = codigoSunat; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}