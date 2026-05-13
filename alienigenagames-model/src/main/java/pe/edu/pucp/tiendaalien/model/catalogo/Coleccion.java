package pe.edu.pucp.tiendaalien.model.catalogo;

public class Coleccion {

    private int coleccionId;
    private String nombre;
    private Boolean esActivo;

    public Coleccion(){
    }

    public Coleccion(String nombre){
        this.nombre=nombre;
        this.esActivo=true;
    }

    //setters y getters

    public int getColeccionId() {
        return coleccionId;
    }

    public void setColeccionId(int coleccionId) {
        this.coleccionId = coleccionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}