package pe.edu.pucp.tiendaalien.model.facturacion;

import java.util.List;
import java.util.ArrayList;

public class TipoComprobante {
    private int tipo_comprobante_id;
    private String codigo_sunat;
    private String descripcion;

    // Relación: Un Tipo de Comprobante puede estar en muchos Comprobantes
    private List<ComprobantePago> comprobantes = new ArrayList<>();

    public TipoComprobante() {}

    // Getters y Setters
    public int getTipo_comprobante_id() { return tipo_comprobante_id; }
    public void setTipo_comprobante_id(int tipo_comprobante_id) { this.tipo_comprobante_id = tipo_comprobante_id; }

    public String getCodigo_sunat() { return codigo_sunat; }
    public void setCodigo_sunat(String codigo_sunat) { this.codigo_sunat = codigo_sunat; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<ComprobantePago> getComprobantes() { return comprobantes; }
    public void setComprobantes(List<ComprobantePago> comprobantes) { this.comprobantes = comprobantes; }
}