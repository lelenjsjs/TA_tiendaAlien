package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.ColeccionDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColeccionDAOImpl implements ColeccionDAO {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public Coleccion load(Integer id) {
        Coleccion coleccion = null;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT coleccion_id, nombre FROM coleccion WHERE coleccion_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                coleccion = mapResultSet(rs);
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar Coleccion: " + ex.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception ex) {}
            try { if(pst != null) pst.close(); } catch(Exception ex) {}
            try { if(con != null) con.close(); } catch(Exception ex) {}
        }
        return coleccion;
    }

    @Override
    public Coleccion save(Coleccion coleccion) {
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO coleccion (nombre) VALUES (?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, coleccion.getNombre());
            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al guardar Coleccion: " + ex.getMessage());
        } finally {
            try { if(pst != null) pst.close(); } catch(Exception ex) {}
            try { if(con != null) con.close(); } catch(Exception ex) {}
        }
        return coleccion;
    }

    @Override
    public Coleccion update(Coleccion coleccion) {
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE coleccion SET nombre = ? WHERE coleccion_id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, coleccion.getNombre());
            pst.setInt(2, coleccion.getColeccionId());
            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al modificar Coleccion: " + ex.getMessage());
        } finally {
            try { if(pst != null) pst.close(); } catch(Exception ex) {}
            try { if(con != null) con.close(); } catch(Exception ex) {}
        }
        return coleccion;
    }

    @Override
    public void remove(Coleccion coleccion) {
        try {
            con = DBManager.getInstance().getConnection();
            // Borrado físico directo porque la tabla no tiene estado activo/inactivo
            String sql = "DELETE FROM coleccion WHERE coleccion_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, coleccion.getColeccionId());
            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al eliminar Coleccion: " + ex.getMessage());
        } finally {
            try { if(pst != null) pst.close(); } catch(Exception ex) {}
            try { if(con != null) con.close(); } catch(Exception ex) {}
        }
    }

    @Override
    public List<Coleccion> listAll() {
        List<Coleccion> lista = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT coleccion_id, nombre FROM coleccion";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (Exception ex) {
            System.out.println("Error al listar Colecciones: " + ex.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception ex) {}
            try { if(pst != null) pst.close(); } catch(Exception ex) {}
            try { if(con != null) con.close(); } catch(Exception ex) {}
        }
        return lista;
    }

    // Método auxiliar para traducir de SQL a Java
    private Coleccion mapResultSet(ResultSet rs) throws SQLException {
        Coleccion coleccion = new Coleccion();
        coleccion.setColeccionId(rs.getInt("coleccion_id"));
        coleccion.setNombre(rs.getString("nombre"));
        return coleccion;
    }
}