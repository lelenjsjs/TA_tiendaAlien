package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.ProductoDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public Producto loadById(Integer integer) {
        Producto producto = null;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT producto_id, nombre, descripcion, sku, stock, " +
                    "precio, precio_comparacion, idioma, tamano, es_preventa, fec_lanzamiento, " +
                    "marca_id, franquicia_id, coleccion_id, categoria_id " +
                    "FROM producto WHERE producto_id = ? AND si_activo=1";

            pst = con.prepareStatement(sql);
            pst.setInt(1, integer);
            rs = pst.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setProductoId(rs.getInt("producto_id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setSku(rs.getString("sku"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPrecioComparacion(rs.getDouble("precio_comparacion"));
                producto.setIdioma(rs.getString("idioma"));
                producto.setTamano(rs.getString("tamano"));
                producto.setEsPreventa(rs.getBoolean("es_preventa"));
                producto.setFecLanzamiento(rs.getDate("fec_lanzamiento"));
                producto.setSiActivo(rs.getBoolean("si_activo"));


                Marca marca = new Marca();
                marca.setMarcaId(rs.getInt("marca_id")); // Cambia "setIdMarca" por el nombre real de tu setter
                producto.setMarca(marca);

                Franquicia franquicia = new Franquicia();
                franquicia.setFranquiciaId(rs.getInt("franquicia_id"));
                producto.setFranquicia(franquicia);


                Coleccion coleccion = new Coleccion();
                coleccion.setColeccionId(rs.getInt("coleccion_id"));
                producto.setColeccion(coleccion);

                Categoria categoria = new Categoria();
                categoria.setCategoriaId(rs.getInt("categoria_id"));
                producto.setCategoria(categoria);
            }
        } catch (Exception ex) {
            System.out.println("ERROR LOAD: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
            try { if (pst != null) pst.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
        }
        return producto;
    }

    @Override
    public List<Producto> listAll() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT producto_id, nombre, descripcion, sku, stock, " +
                "precio, precio_comparacion, idioma, tamano, es_preventa, fec_lanzamiento, " +
                "marca_id, franquicia_id, coleccion_id, categoria_id, si_activo " +
                "FROM producto WHERE si_activo = 1";

        try {
            con = DBManager.getInstance().getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();

                // Atributos básicos
                producto.setProductoId(rs.getInt("producto_id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setSku(rs.getString("sku"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPrecioComparacion(rs.getDouble("precio_comparacion"));
                producto.setIdioma(rs.getString("idioma"));
                producto.setTamano(rs.getString("tamano"));
                producto.setEsPreventa(rs.getBoolean("es_preventa"));
                producto.setFecLanzamiento(rs.getDate("fec_lanzamiento"));
                producto.setSiActivo(rs.getBoolean("si_activo"));

                // Objetos relacionados (solo seteamos los IDs)
                Marca marca = new Marca();
                marca.setMarcaId(rs.getInt("marca_id"));
                producto.setMarca(marca);

                Franquicia franquicia = new Franquicia();
                franquicia.setFranquiciaId(rs.getInt("franquicia_id"));
                producto.setFranquicia(franquicia);

                Coleccion coleccion = new Coleccion();
                coleccion.setColeccionId(rs.getInt("coleccion_id"));
                producto.setColeccion(coleccion);

                Categoria categoria = new Categoria();
                categoria.setCategoriaId(rs.getInt("categoria_id"));
                producto.setCategoria(categoria);

                // Agregamos el producto a la lista
                productos.add(producto);
            }
        } catch (Exception ex) {
            System.out.println("ERROR LIST ALL: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) { }
            try { if (pst != null) pst.close(); } catch (Exception ex) { }
            try { if (con != null) con.close(); } catch (Exception ex) { }
        }
        return productos;
    }

    @Override
    public Producto save(Producto producto) {
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO producto(nombre, descripcion, sku, stock, precio, " +
                    "precio_comparacion, idioma, tamano, es_preventa, fec_lanzamiento, si_activo, " +
                    "marca_id, franquicia_id, coleccion_id, categoria_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // Le indicamos al PreparedStatement que nos devuelva el ID generado
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getSku());
            pst.setInt(4, producto.getStock());
            pst.setDouble(5, producto.getPrecio());
            pst.setDouble(6, producto.getPrecioComparacion());
            pst.setString(7, producto.getIdioma());
            pst.setString(8, producto.getTamano());
            pst.setBoolean(9, producto.isEsPreventa());
            // Convertimos de java.util.Date a java.sql.Date usando el tiempo en milisegundos
            pst.setDate(10, new java.sql.Date(producto.getFecLanzamiento().getTime()));
            pst.setBoolean(11, true);
            pst.setInt(12, producto.getMarca().getMarcaId());
            pst.setInt(13, producto.getFranquicia().getFranquiciaId());
            pst.setInt(14, producto.getColeccion().getColeccionId());
            pst.setInt(15, producto.getCategoria().getCategoriaId());

            pst.executeUpdate();

            // Recuperamos el ID autogenerado
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                producto.setProductoId(rs.getInt(1)); // Asignamos el nuevo ID al objeto
            }

        } catch (Exception ex) {
            System.out.println("ERROR SAVE: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
            try { if (pst != null) pst.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
        }
        return producto;
    }

    @Override
    public Producto update(Producto producto) {
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE producto SET nombre=?, descripcion=?, sku=?, stock=?," +
                    "precio=?, precio_comparacion=?, idioma=?, tamano=?, es_preventa=?, fec_lanzamiento=?, " +
                    "marca_id=?, franquicia_id=?, coleccion_id=?, categoria_id=? " +
                    "WHERE producto_id=? AND si_activo=1";

            pst = con.prepareStatement(sql);

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getSku());
            pst.setInt(4, producto.getStock());
            pst.setDouble(5, producto.getPrecio());
            pst.setDouble(6, producto.getPrecioComparacion());
            pst.setString(7, producto.getIdioma());
            pst.setString(8, producto.getTamano());
            pst.setBoolean(9, producto.isEsPreventa());
            pst.setDate(10, producto.getFecLanzamiento() != null ? new java.sql.Date(producto.getFecLanzamiento().getTime()) : null);
            pst.setInt(11, producto.getMarca().getMarcaId());
            pst.setInt(12, producto.getFranquicia().getFranquiciaId());
            pst.setInt(13, producto.getColeccion().getColeccionId());
            pst.setInt(14, producto.getCategoria().getCategoriaId());
            // Parámetro para el WHERE
            pst.setInt(16, producto.getProductoId());

            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR UPDATE: " + ex.getMessage());
        } finally {
            try { if (pst != null) pst.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
        }
        return producto;
    }

    @Override
    public void remove(Producto producto) {
        try {
            con = DBManager.getInstance().getConnection();

            // Borrado Físico: Usamos la sentencia DELETE de SQL
            String sql = "UPDATE producto SET si_activo =0 WHERE producto_id=?";

            pst = con.prepareStatement(sql);
            pst.setInt(1, producto.getProductoId());

            pst.executeUpdate();

            // Aquí ya no necesitamos el setSiActivo(false) porque el producto ya no existe.

        } catch (Exception ex) {
            System.out.println("ERROR REMOVE: " + ex.getMessage());
        } finally {
            try { if (pst != null) pst.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println("ERROR: " + ex.getMessage()); }
        }
    }

}
