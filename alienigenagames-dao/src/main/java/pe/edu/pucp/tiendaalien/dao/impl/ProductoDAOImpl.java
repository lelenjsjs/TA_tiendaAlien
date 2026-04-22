package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.ProductoDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoDAOImpl implements ProductoDAO {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public Producto load(Integer integer) {
        Producto producto = null;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT producto_id, nombre, descripcion, sku, stock, sl_activo, " +
                    "precio, precio_comparacion, idioma, tamano, es_preventa, fec_lanzamiento, " +
                    "marca_id, franquicia_id, coleccion_id, categoria_id " +
                    "FROM producto WHERE producto_id = ?";

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
                producto.setSiActivo(rs.getBoolean("sl_activo"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPrecioComparacion(rs.getDouble("precio_comparacion"));
                producto.setIdioma(rs.getString("idioma"));
                producto.setTamano(rs.getString("tamano"));
                producto.setEsPreventa(rs.getBoolean("es_preventa"));
                producto.setFecLanzamiento(rs.getDate("fec_lanzamiento"));


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
    public Producto save(Producto producto) {
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO producto(nombre, descripcion, sku, stock, sl_activo, precio, " +
                    "precio_comparacion, idioma, tamano, es_preventa, fec_lanzamiento, " +
                    "marca_id, franquicia_id, coleccion_id, categoria_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // Le indicamos al PreparedStatement que nos devuelva el ID generado
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getSku());
            pst.setInt(4, producto.getStock());
            pst.setBoolean(5, producto.isSiActivo());
            pst.setDouble(6, producto.getPrecio());
            pst.setDouble(7, producto.getPrecioComparacion());
            pst.setString(8, producto.getIdioma());
            pst.setString(9, producto.getTamano());
            pst.setBoolean(10, producto.isEsPreventa());
            pst.setDate(11, producto.getFecLanzamiento() != null ? new java.sql.Date(producto.getFecLanzamiento().getTime()) : null);
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
            String sql = "UPDATE producto SET nombre=?, descripcion=?, sku=?, stock=?, sl_activo=?, " +
                    "precio=?, precio_comparacion=?, idioma=?, tamano=?, es_preventa=?, fec_lanzamiento=?, " +
                    "marca_id=?, franquicia_id=?, coleccion_id=?, categoria_id=? " +
                    "WHERE producto_id=?";

            pst = con.prepareStatement(sql);

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getSku());
            pst.setInt(4, producto.getStock());
            pst.setBoolean(5, producto.isSiActivo());
            pst.setDouble(6, producto.getPrecio());
            pst.setDouble(7, producto.getPrecioComparacion());
            pst.setString(8, producto.getIdioma());
            pst.setString(9, producto.getTamano());
            pst.setBoolean(10, producto.isEsPreventa());
            pst.setDate(11, producto.getFecLanzamiento() != null ? new java.sql.Date(producto.getFecLanzamiento().getTime()) : null);
            pst.setInt(12, producto.getMarca().getMarcaId());
            pst.setInt(13, producto.getFranquicia().getFranquiciaId());
            pst.setInt(14, producto.getColeccion().getColeccionId());
            pst.setInt(15, producto.getCategoria().getCategoriaId());
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
            String sql = "DELETE FROM producto WHERE producto_id = ?";

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
