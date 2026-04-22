package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.DetallePedDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedDAOImpl implements DetallePedDAO {

    @Override
    public List<DetallePed> listAll() {

        List<DetallePed> lista = new ArrayList<>();

        String sql = """
            SELECT detalle_ped_id,nombre_congelado,precio_un_congelado,es_preventa_congelado,cantidad
            FROM detalle_pedido
            WHERE activo = 1
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearDetalle(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public DetallePed load(Integer id) {

        String sql = """
            SELECT detalle_ped_id,nombre_congelado,precio_un_congelado,es_preventa_congelado,cantidad
            FROM detalle_pedido
            WHERE detalle_ped_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearDetalle(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public DetallePed save(DetallePed detalle) {

        String sql = """
            INSERT INTO detalle_pedido(
                nombre_congelado,
                precio_un_congelado,
                es_preventa_congelado,
                cantidad,
                producto_id,
                activo
            )
            VALUES (?, ?, ?, ?, ?, 1)
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarPreparedStatement(ps, detalle);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    detalle.setDetallePedId(rs.getInt(1));
                }
            }

            return detalle;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DetallePed update(DetallePed detalle) {

        String sql = """
            UPDATE detalle_pedido SET
                nombre_congelado = ?,
                precio_un_congelado = ?,
                es_preventa_congelado = ?,
                cantidad = ?,
                producto_id = ?
            WHERE detalle_ped_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarPreparedStatement(ps, detalle);
            ps.setInt(6, detalle.getDetallePedId());

            ps.executeUpdate();

            return detalle;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(DetallePed detalle) {

        String sql = """
            UPDATE detalle_pedido
            SET activo = 0
            WHERE detalle_ped_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getDetallePedId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DetallePed mapearDetalle(ResultSet rs) throws SQLException {

        DetallePed d = new DetallePed();

        d.setDetallePedId(rs.getInt("detalle_ped_id"));
        d.setNombreCongelado(rs.getString("nombre_congelado"));
        d.setPrecioUnCongelado(rs.getDouble("precio_un_congelado"));
        d.setEsPreventaCongelado(rs.getBoolean("es_preventa_congelado"));
        d.setCantidad(rs.getInt("cantidad"));

        Producto producto = new Producto();
        producto.setProductoId(rs.getInt("producto_id"));
        d.setProducto(producto);

        return d;
    }

    private void llenarPreparedStatement(
            PreparedStatement ps,
            DetallePed d) throws SQLException {

        ps.setString(1, d.getNombreCongelado());
        ps.setDouble(2, d.getPrecioUnCongelado());
        ps.setBoolean(3, d.isEsPreventaCongelado());
        ps.setInt(4, d.getCantidad());
        ps.setInt(5, d.getProducto().getProductoId());
    }
}