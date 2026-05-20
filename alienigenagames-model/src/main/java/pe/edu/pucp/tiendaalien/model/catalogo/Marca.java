package pe.edu.pucp.tiendaalien.model.catalogo;

public class Marca {

    private int marcaId;
    private String nombre;
    private Boolean esActivo;

    public Marca(){
    }

    public Marca(String nombre){
        this.nombre=nombre;
        this.esActivo=true;
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

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }
}