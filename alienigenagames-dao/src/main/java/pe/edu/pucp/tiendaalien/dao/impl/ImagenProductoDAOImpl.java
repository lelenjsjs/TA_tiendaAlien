package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.ImagenProductoDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.catalogo.ImagenProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagenProductoDAOImpl implements ImagenProductoDAO {

    @Override
    public List<ImagenProducto> listAll() {
        List<ImagenProducto> lista = new ArrayList<>();
        String sql = "SELECT url_imagen, si_principal FROM imagenes_producto";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Imágenes", e);
        }
        return lista;
    }

    @Override
    public ImagenProducto load(Integer id) {
        String sql = "SELECT url_imagen, si_principal FROM imagenes_producto WHERE imagen_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Imagen con ID: " + id, e);
        }
        return null;
    }

    @Override
    public ImagenProducto save(ImagenProducto img) {
        // Asumiendo que necesitas pasarle el ID del producto al que pertenece
        String sql = "INSERT INTO imagenes_producto (url_imagen, si_principal) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, img.UrlImagen());
            ps.setBoolean(2, img.isSiPrincipal());

            ps.executeUpdate();
            return img;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Imagen", e);
        }
    }

    @Override
    public ImagenProducto update(ImagenProducto img) {
        // Se requiere un identificador (en este ejemplo usamos la URL como criterio o un ID ficticio)
        String sql = "UPDATE imagenes_producto SET si_principal = ? WHERE url_imagen = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, img.isSiPrincipal());
            ps.setString(2, img.UrlImagen());

            ps.executeUpdate();
            return img;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Imagen", e);
        }
    }

    @Override
    public void remove(ImagenProducto img) {
        String sql = "DELETE FROM imagenes_producto WHERE url_imagen = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, img.UrlImagen());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Imagen", e);
        }
    }

    private ImagenProducto mapResultSet(ResultSet rs) throws SQLException {
        ImagenProducto img = new ImagenProducto();
        img.setUrlImagen(rs.getString("url_imagen"));
        img.setSiActivo(rs.getBoolean("si_principal"));
        return img;
    }
}