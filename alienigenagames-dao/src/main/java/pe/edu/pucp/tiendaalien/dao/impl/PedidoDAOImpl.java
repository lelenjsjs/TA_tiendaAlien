package pe.edu.pucp.tiendaalien.dao.impl;
import pe.edu.pucp.tiendaalien.dao.PedidoDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.ventas.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO{
    @Override
    public List<Pedido> listAll() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT pedido_id, cod_pedido,cliente_email,cliente_cel,canal_venta, metodo_pago, " +
                "estado_pago,subtotal,cargo_servicio,monto_adelanto,monto_total,pasarela_transaccion_id,fec_creacion,\n" +
                "estado_pedido FROM pedido WHERE activo = 1";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearPedido(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public Pedido load(Integer id) {
        String sql = "SELECT pedido_id, cod_pedido,cliente_email,cliente_cel,canal_venta, metodo_pago, " +
                "estado_pago,subtotal,cargo_servicio,monto_adelanto,monto_total,pasarela_transaccion_id,fec_creacion,\n" +
                "estado_pedido FROM pedido WHERE  id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPedido(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Pedido save(Pedido pedido) {

        String sql = """
            INSERT INTO pedido(
                cod_pedido,
                cliente_email,
                cliente_cel,
                canal_venta,
                metodo_pago,
                estado_pago,
                subtotal,
                cargo_servicio,
                monto_adelanto,
                monto_total,
                pasarela_transaccion_id,
                fec_creacion,
                estado_pedido,
                activo
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            llenarPreparedStatement(ps, pedido);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setPedidoId(rs.getInt(1));
                }
            }

            return pedido;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pedido update(Pedido pedido) {

        String sql = """
            UPDATE pedido SET
                cod_pedido = ?,
                cliente_email = ?,
                cliente_cel = ?,
                canal_venta = ?,
                metodo_pago = ?,
                estado_pago = ?,
                subtotal = ?,
                cargo_servicio = ?,
                monto_adelanto = ?,
                monto_total = ?,
                pasarela_transaccion_id = ?,
                fec_creacion = ?,
                estado_pedido = ?
            WHERE pedido_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            llenarPreparedStatement(ps, pedido);
            ps.setInt(14, pedido.getPedidoId());

            ps.executeUpdate();

            return pedido;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Pedido pedido) {

        String sql = """
            UPDATE pedido
            SET activo = 0
            WHERE pedido_id = ?
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getPedidoId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Pedido mapearPedido(ResultSet rs) throws SQLException {

        Pedido p = new Pedido();

        p.setPedidoId(rs.getInt("pedido_id"));
        p.setCodPedido(rs.getString("cod_pedido"));
        p.setClienteEmail(rs.getString("cliente_email"));
        p.setClienteCel(rs.getString("cliente_cel"));

        p.setCanalVenta(CanalVenta.valueOf(rs.getString("canal_venta")));
        p.setMetodoPago(MetodoPago.valueOf(rs.getString("metodo_pago")));
        p.setEstadoPago(EstadoPago.valueOf(rs.getString("estado_pago")));
        p.setEstadoPedido(EstadoPedido.valueOf(rs.getString("estado_pedido")));

        p.setSubtotal(rs.getDouble("subtotal"));
        p.setCargoServicio(rs.getDouble("cargo_servicio"));
        p.setMontoAdelanto(rs.getDouble("monto_adelanto"));
        p.setMontoTotal(rs.getDouble("monto_total"));

        p.setPasarelaTransaccionId(rs.getString("pasarela_transaccion_id"));
        p.setFecCreacion(rs.getTimestamp("fec_creacion"));

        return p;
    }

    private void llenarPreparedStatement(PreparedStatement ps, Pedido p)
            throws SQLException {

        ps.setString(1, p.getCodPedido());
        ps.setString(2, p.getClienteEmail());
        ps.setString(3, p.getClienteCel());
        ps.setString(4, p.getCanalVenta().name());
        ps.setString(5, p.getMetodoPago().name());
        ps.setString(6, p.getEstadoPago().name());
        ps.setDouble(7, p.getSubtotal());
        ps.setDouble(8, p.getCargoServicio());
        ps.setDouble(9, p.getMontoAdelanto());
        ps.setDouble(10, p.getMontoTotal());
        ps.setString(11, p.getPasarelaTransaccionId());
        ps.setTimestamp(12, new Timestamp(p.getFecCreacion().getTime()));
        ps.setString(13, p.getEstadoPedido().name());
    }
}