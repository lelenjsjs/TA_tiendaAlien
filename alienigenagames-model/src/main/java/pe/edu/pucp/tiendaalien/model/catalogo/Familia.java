package pe.edu.pucp.tiendaalien.model.catalogo;

public enum Familia {
    PRODUCTO_SELLADO("PRODUCTO SELLADO"),
    ACCESORIO("ACCESORIO");

    public String getTextoParaBD() {
        return textoParaBD;
    }

    private final String textoParaBD;

    Familia(String textoParaBD) {
        this.textoParaBD = textoParaBD;
    }

    // Método para que no de error la diferencia entre SIN _ en la BD y con _ en el .java
    public static Familia desdeTexto(String texto) {
        for (Familia f : values()) {
            if (f.textoParaBD.equalsIgnoreCase(texto)) return f;
        }
        return null;
    }
}