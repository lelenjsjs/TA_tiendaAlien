package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.DetallePedDAO;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedDAOImpl implements DetallePedDAO {

    @Override
    public DetallePed save(DetallePed t, Connection con) throws SQLException {
        // Ajustado: precio_unitario_congelado y orden de columnas según tu SQL
        String sql = "INSERT INTO detalle_ped (pedido_id, producto_id, precio_unitario_congelado, es_preventa_congelado, cantidad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getPedido().getIdPedido());
            ps.setInt(2, t.getProducto().getProductoId());
            ps.setDouble(3, t.getPrecioUnitCongelado());
            ps.setBoolean(4, t.getEsPreventaCongelado());
            ps.setInt(5, t.getCantidad());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) t.setId(rs.getInt(1)); // Usando detalle_ped_id generado
            }
        }
        return t;
    }

    @Override
    public DetallePed update(DetallePed t, Connection con) throws SQLException {
        // Ajustado: precio_unitario_congelado y detalle_ped_id
        String sql = "UPDATE detalle_ped SET cantidad = ?, precio_unitario_congelado = ? WHERE detalle_ped_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, t.getCantidad());
            ps.setDouble(2, t.getPrecioUnitCongelado());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        }
        return t;
    }

    @Override
    public void remove(DetallePed t, Connection con) throws SQLException {
        String sql = "DELETE FROM detalle_ped WHERE detalle_ped_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, t.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public DetallePed loadById(Integer id) {
        String sql = "SELECT * FROM detalle_ped WHERE detalle_ped_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DetallePed> listAll() {
        List<DetallePed> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ped";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODOS DE APOYO Y SOBRECARGAS ---

    private DetallePed mapearRS(ResultSet rs) throws SQLException {
        DetallePed det = new DetallePed();
        det.setId(rs.getInt("detalle_ped_id"));
        det.setCantidad(rs.getInt("cantidad"));
        det.setPrecioUnitCongelado(rs.getDouble("precio_unitario_congelado"));
        det.setEsPreventaCongelado(rs.getBoolean("es_preventa_congelado"));

        // Seteamos un objeto producto solo con el ID (Proxy)
        Producto p = new Producto();
        p.setProductoId(rs.getInt("producto_id"));
        det.setProducto(p);

        return det;
    }

    @Override
    public DetallePed save(DetallePed t) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            return save(t, con);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override public DetallePed update(DetallePed t) { return null; }
    @Override public void remove(DetallePed t) { }
}