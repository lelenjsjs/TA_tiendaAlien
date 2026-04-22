package pe.edu.pucp.tiendaalien.dao.impl;
import pe.edu.pucp.tiendaalien.dao.PedidoDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PedidoDAOImpl implements PedidoDAO{
    @Override
    public List<Pedido> listAll() {
        List<Pedido> list = new ArrayList<>();
        String sql = "select id_area, nombre, activa from area where activa = 1";
        try(Connection connection = DBManager.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt(1));
                pedido.setName(rs.getString(2));
                pedido.setActive(rs.getBoolean(3));
                list.add(pedido);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
