package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.CategoriaDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.catalogo.Categoria;
import pe.edu.pucp.tiendaalien.model.catalogo.Familia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public List<Categoria> listAll() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT categoria_id, nombre, familia FROM categorias";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Categorías", e);
        }
        return lista;
    }

    @Override
    public Categoria load(Integer id) {
        String sql = "SELECT categoria_id, nombre, familia FROM categorias WHERE categoria_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar Categoría con ID: " + id, e);
        }
        return null;
    }

    @Override
    public Categoria save(Categoria c) {
        String sql = "INSERT INTO categorias (nombre, familia) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getFamilia().name()); // Guardamos el nombre del Enum

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        c.setCategoriaId(rs.getInt(1));
                    }
                }
            }
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar Categoría", e);
        }
    }

    @Override
    public Categoria update(Categoria c) {
        String sql = "UPDATE categorias SET nombre = ?, familia = ? WHERE categoria_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getFamilia().name());
            ps.setInt(3, c.getCategoriaId());

            ps.executeUpdate();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Categoría", e);
        }
    }

    @Override
    public void remove(Categoria c) {
        String sql = "DELETE FROM categorias WHERE categoria_id = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getCategoriaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Categoría", e);
        }
    }

    private Categoria mapResultSet(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setCategoriaId(rs.getInt("categoria_id"));
        c.setNombre(rs.getString("nombre"));
        // Convertimos el String de la BD de vuelta al Enum Familia
        String familiaStr = rs.getString("familia");
        if (familiaStr != null) {
            c.setFamilia(Familia.valueOf(familiaStr));
        }
        return c;
    }
}