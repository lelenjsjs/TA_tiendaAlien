package pe.edu.pucp.tiendaalien.model.catalogo;

public class Categoria {

    private int categoriaId;
    private String nombre;
    private Familia familia;
    private Boolean esActivo;

    public Categoria(){
    }

    public Categoria( String nombre, Familia familia){
        this.nombre=nombre;
        this.familia=familia;
        this.esActivo=false;
    }

    //setters y getters
    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }


    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

    public String toString(){
        return "ID = " + this.categoriaId + ", Nombre = " + this.nombre + ", Familia = " + this.familia +"\n";
    }
}