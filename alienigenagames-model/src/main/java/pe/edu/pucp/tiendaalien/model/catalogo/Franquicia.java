package pe.edu.pucp.tiendaalien.model.catalogo;

public class Franquicia {

    private int franquiciaId;
    private String nombre;

    public Franquicia(){
    }

    public Franquicia(int franquiciaId, String nombre){
        this.franquiciaId=franquiciaId;
        this.nombre=nombre;
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

}
