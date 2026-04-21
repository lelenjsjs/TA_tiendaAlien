package pe.edu.pucp.tiendaalien.model.catalogo;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    private int productoId;
    private String nombre;
    private String descripcion;
    private String sku;
    private int stock;
    private boolean siActivo;
    private double precio;
    private double precioComparacion;
    private String idioma;
    private String tamano;
    private boolean esPreventa;
    private Date fecLanzamiento;
    private Date fecCreacion;
    private Date fecUltimaModificacion;
    private Marca marca;
    private Franquicia franquicia;
    private Coleccion coleccion;
    private Categoria categoria;
    private List<ImagenProducto> imagenes;

    public Producto(){
    }

    public Producto(int productoId, String nombre, String descripcion, String sku, int stock,
                    boolean siActivo, double precio, double precioComparacion, String idioma, String tamano,
                    Date fecCreacion, Date fecUltimaModificacion, Marca marca, Franquicia franquicia,
                    Coleccion coleccion, Categoria categoria){
        this.productoId=productoId;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.sku=sku;
        this.stock=stock;
        this.siActivo=siActivo;
        this.precio=precio;
        this.precioComparacion=precioComparacion;
        this.idioma=idioma;
        this.tamano=tamano;
        this.fecCreacion=fecCreacion;
        this.fecUltimaModificacion=fecUltimaModificacion;
        this.marca=marca;
        this.franquicia=franquicia;
        this.coleccion=coleccion;
        this.categoria=categoria;
        this.imagenes=new ArrayList<>();
    }

    //SETTERS Y GETTERS

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isSiActivo() {
        return siActivo;
    }

    public void setSiActivo(boolean siActivo) {
        this.siActivo = siActivo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioComparacion() {
        return precioComparacion;
    }

    public void setPrecioComparacion(double precioComparacion) {
        this.precioComparacion = precioComparacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public boolean isEsPreventa() {
        return esPreventa;
    }

    public void setEsPreventa(boolean esPreventa) {
        this.esPreventa = esPreventa;
    }

    public Date getFecLanzamiento() {
        return fecLanzamiento;
    }

    public void setFecLanzamiento(Date fecLanzamiento) {
        this.fecLanzamiento = fecLanzamiento;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Date getFecUltimaModificacion() {
        return fecUltimaModificacion;
    }

    public void setFecUltimaModificacion(Date fecUltimaModificacion) {
        this.fecUltimaModificacion = fecUltimaModificacion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Franquicia getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(Franquicia franquicia) {
        this.franquicia = franquicia;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ImagenProducto> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenProducto> imagenes) {
        this.imagenes = imagenes;
    }

}
