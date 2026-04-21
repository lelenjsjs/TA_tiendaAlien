package pe.edu.pucp.tiendaalien.model.facturacion;

public class TipoDocIdentidad {

    private int tipoDocId;
    private String codigoSunat;
    private String descripcion;

    public TipoDocIdentidad(){

    }

    public TipoDocIdentidad(int tipoDocId, String codigoSunat, String descripcion){
        this.tipoDocId=tipoDocId;
        this.codigoSunat=codigoSunat;
        this.descripcion=descripcion;
    }

    public int getTipoDocId() {
        return tipoDocId;
    }

    public void setTipoDocId(int tipoDocId) {
        this.tipoDocId = tipoDocId;
    }

    public String getCodigoSunat() {
        return codigoSunat;
    }

    public void setCodigoSunat(String codigoSunat) {
        this.codigoSunat = codigoSunat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
