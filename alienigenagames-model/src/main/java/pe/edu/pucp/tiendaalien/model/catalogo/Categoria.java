package pe.edu.pucp.tiendaalien.model.catalogo;

public class Categoria {

    private int categoriaId;
    private String nombre;
    private String familia;

    public Categoria(){
    }

    public Categoria(int categoriaId, String nombre, String familia){
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

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }



}