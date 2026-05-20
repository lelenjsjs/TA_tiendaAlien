package pe.edu.pucp.tiendaalien.model.catalogo;

public class ImagenProducto {
    private String urlImagen;
    private boolean siPrincipal;
    private Producto producto;
    private Boolean esActivo;
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

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
