package pe.edu.pucp.tiendaalien.model.catalogo;

public class Categoria {

    private int categoriaId;
    private String nombre;
    private Familia familia;

    public Categoria(){
    }

    public Categoria(int categoriaId, String nombre, Familia familia){
        this.categoriaId=categoriaId;
        this.nombre=nombre;
        this.familia=familia;
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



}