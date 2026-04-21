package pe.edu.pucp.tiendaalien.model.catalogo;

public class Marca {

    private int marcaId;
    private String nombre;

    public Marca(){
    }

    public Marca(int marcaId, String nombre){
        this.marcaId=marcaId;
        this.nombre=nombre;
    }

    //SETTERS Y GETTERS

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}