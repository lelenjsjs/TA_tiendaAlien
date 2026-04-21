package pe.edu.pucp.tiendaalien.model.catalogo;

public class Coleccion {

    private int coleccionId;
    private String nombre;
    private Franquicia franquicia;

    public Coleccion(){
    }

    public Coleccion(int coleccionId,String nombre, Franquicia franquicia){
        this.coleccionId=coleccionId;
        this.nombre=nombre;
        this.franquicia=franquicia;
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