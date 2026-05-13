package pe.edu.pucp.tiendaalien.model.catalogo;

public class Franquicia {

    private int franquiciaId;
    private String nombre;
    private Boolean esActivo;

    public Franquicia(){
    }

    public Franquicia(String nombre){
        this.nombre=nombre;
        this.esActivo=true;
    }

    //setters y getters

    public int getFranquiciaId() {
        return franquiciaId;
    }

    public void setFranquiciaId(int franquiciaId) {
        this.franquiciaId = franquiciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }
}
