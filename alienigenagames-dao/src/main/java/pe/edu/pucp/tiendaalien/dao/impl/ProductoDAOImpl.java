package pe.edu.pucp.tiendaalien.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.ProductoDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

public class ProductoDAOImpl implements ProductoDAO {

    /* --- MÉTODOS PARA LA TRANSACCIÓN (Reciben Connection) --- */

    /**
     * Actualiza el stock de un producto dentro de una transacción.
     * @param cantidad Valor negativo para restar stock (ej. -5).
     */
    @Override
    public void actualizarStock(int idProducto, int cantidad, Connection con) throws SQLException {
        String sql = "UPDATE producto SET stock = stock + ? WHERE producto_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        }
    }

    /* --- MÉTODOS CRUD NORMALES (Gestionan su propia conexión) --- */

    @Override
    public Producto loadById(Integer id) {
        Producto p = null;
        String sql = "SELECT * FROM producto WHERE producto_id = ? AND si_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Producto();
                    p.setProductoId(rs.getInt("producto_id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setStock(rs.getInt("stock"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setEsPreventa(rs.getBoolean("es_preventa"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public Producto save(Producto p) {
        String sql = "INSERT INTO producto (nombre, stock, precio, es_preventa, si_activo) VALUES (?, ?, ?, ?, 1)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getStock());
            ps.setDouble(3, p.getPrecio());
            ps.setBoolean(4, p.getEsPreventa());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setProductoId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public Producto update(Producto p) {
        String sql = "UPDATE producto SET nombre = ?, stock = ?, precio = ?, es_preventa = ? WHERE producto_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getStock());
            ps.setDouble(3, p.getPrecio());
            ps.setBoolean(4, p.getEsPreventa());
            ps.setInt(5, p.getProductoId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public void remove(Producto p) {
        String sql = "UPDATE producto SET si_activo = 0 WHERE producto_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getProductoId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Producto> listAll() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE si_activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setProductoId(rs.getInt("producto_id"));
                p.setNombre(rs.getString("nombre"));
                p.setStock(rs.getInt("stock"));
                p.setPrecio(rs.getDouble("precio"));
                p.setEsPreventa(rs.getBoolean("es_preventa"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}