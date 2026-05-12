package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.DetallePedDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedDAOImpl implements DetallePedDAO {

    @Override
    public List<DetallePed> listAll() {
        List<DetallePed> lista = new ArrayList<>();
        // REGLA: Filtramos por es_activo = 1 y usamos el nombre real 'detalle_ped'
        String sql = "SELECT detalle_ped_id, pedido_id, producto_id, precio_unitario_congelado, " +
                     "es_preventa_congelado, cantidad FROM detalle_ped WHERE es_activo = 1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearDetalle(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar detalles", e);
        }
        return lista;
    }

    @Override
    public DetallePed loadById(Integer id) {
        String sql = "SELECT detalle_ped_id, pedido_id, producto_id, precio_unitario_congelado, " +
                     "es_preventa_congelado, cantidad FROM detalle_ped " +
                     "WHERE detalle_ped_id = ? AND es_activo = 1";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearDetalle(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar detalle", e);
        }
        return null;
    }

    @Override
    public DetallePed save(DetallePed d) {
        String sql = "INSERT INTO detalle_ped (pedido_id, producto_id, precio_unitario_congelado, " +
                     "es_preventa_congelado, cantidad, es_activo) VALUES (?, ?, ?, ?, ?, 1)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, d.getPedido().getPedidoId());
            ps.setInt(2, d.getProducto().getProductoId());
            ps.setDouble(3, d.getPrecioUnitarioCongelado());
            ps.setBoolean(4, d.isEsPreventaCongelado());
            ps.setInt(5, d.getCantidad());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) d.setDetallePedId(rs.getInt(1));
            }
            return d;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar detalle", e);
        }
    }

    @Override
    public DetallePed update(DetallePed d) {
        String sql = "UPDATE detalle_ped SET pedido_id = ?, producto_id = ?, precio_unitario_congelado = ?, " +
                     "es_preventa_congelado = ?, cantidad = ? WHERE detalle_ped_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getPedido().getPedidoId());
            ps.setInt(2, d.getProducto().getProductoId());
            ps.setDouble(3, d.getPrecioUnitarioCongelado());
            ps.setBoolean(4, d.isEsPreventaCongelado());
            ps.setInt(5, d.getCantidad());
            ps.setInt(6, d.getDetallePedId());

            ps.executeUpdate();
            return d;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar detalle", e);
        }
    }

    @Override
    public void remove(DetallePed d) {
        // REGLA: UPDATE es_activo = 0 (Borrado Lógico)
        String sql = "UPDATE detalle_ped SET es_activo = 0 WHERE detalle_ped_id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getDetallePedId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar detalle", e);
        }
    }

    private DetallePed mapearDetalle(ResultSet rs) throws SQLException {
        DetallePed d = new DetallePed();
        d.setDetallePedId(rs.getInt("detalle_ped_id"));
        d.setPrecioUnitarioCongelado(rs.getDouble("precio_unitario_congelado"));
        d.setEsPreventaCongelado(rs.getBoolean("es_preventa_congelado"));
        d.setCantidad(rs.getInt("cantidad"));

        Pedido p = new Pedido(); p.setPedidoId(rs.getInt("pedido_id")); d.setPedido(p);
        Producto prod = new Producto(); prod.setProductoId(rs.getInt("producto_id")); d.setProducto(prod);
        return d;
    }
}