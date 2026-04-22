package pe.edu.pucp.tiendaalien.model.catalogo;

public class ImagenProducto {
    private String urlImagen;
    private boolean siPrincipal;
    private Producto producto_id;
    //setters y getters
    public String UrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public boolean isSiPrincipal() {
        return siPrincipal;
    }

    public void setSiActivo(boolean siPrincipal) {
        this.siPrincipal = siPrincipal;
    }

}
