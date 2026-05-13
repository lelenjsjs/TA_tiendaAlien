package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import pe.edu.pucp.tiendaalien.bl.*;
import pe.edu.pucp.tiendaalien.bl.impl.*;
import pe.edu.pucp.tiendaalien.model.catalogo.*;


import java.util.List;


// Cuando corra las pruebas se solicita colocar diferentes IDs porque sino da error de duplicado

public class Main {

    public static void main(String[] args) throws BusinessLogicException {
        /* PRODUCTO */
        IProductoBL productoBL = new ProductoBLImpl();
        IMarcaBL marcaBL = new MarcaBLImpl();
        IFranquiciaBL franquiciaBL = new FranquiciaBLImpl();
        IColeccionBL coleccionBL = new ColeccionBLImpl();
        ICategoriaBL categoriaBL = new CategoriaBLImpl();

        /*
         private int productoId;
    private String nombre;
    private String descripcion;
    private String sku;
    private int stock;
    private double precio;
    private double precioComparacion;
    private String idioma;
    private String tamano;
    private boolean esPreventa;
    private Date fecLanzamiento;
    private Date fecCreacion;
    private Date fecUltimaModificacion;
    private boolean siActivo;

    private Marca marca;
    private Franquicia franquicia;
    private Coleccion coleccion;
    private Categoria categoria;
    private List<ImagenProducto> imagenes;
        * */
        Producto producto = new Producto();
        producto.setNombre("Producto");
        producto.setDescripcion("Producto");
        producto.setSku("92JR82EJ");
        producto.setStock(100);
        producto.setPrecio(140.50);
        producto.setIdioma("Japones");
        producto.setTamano("Grande");
        producto.setEsPreventa(false);

        Marca marca = marcaBL.cargarMarcaPorId(1);
        Franquicia franquicia = franquiciaBL.cargarFranquiciaPorId(1);
        Coleccion coleccion = coleccionBL.cargarColeccionPorId(1);
        Categoria categoria = categoriaBL.cargarCategoriaPorId(1);
        producto.setMarca(marca);
        producto.setFranquicia(franquicia);
        producto.setCategoria(categoria);
        producto.setColeccion(coleccion);
        producto.setFecCreacion();

        // Ya podria agregarlo
        productoBL.agregarProducto(producto);



    }
}