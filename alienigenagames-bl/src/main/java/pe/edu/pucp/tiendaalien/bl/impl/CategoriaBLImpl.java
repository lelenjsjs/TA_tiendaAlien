package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.ICategoriaBL;
import pe.edu.pucp.tiendaalien.dao.CategoriaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.CategoriaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Categoria;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import java.util.ArrayList;
import java.util.List;

public class CategoriaBLImpl implements ICategoriaBL {
    private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    @Override
    public Categoria registrarCategoria(Categoria c) {
        return categoriaDAO.save(c);
    }

    @Override
    public Categoria cargarCategoriaPorId(int id) {
        return categoriaDAO.loadById(id);
    }

    @Override
    public Categoria cargarCategoriaPorNombre(String nombre) {
        return categoriaDAO.loadByName(nombre);
    }

    @Override
    public Categoria modificarCategoria(Categoria c) {
        return categoriaDAO.update(c);
    }

    @Override
    public void eliminarCategoria(Categoria c) {
        categoriaDAO.remove(c);
    }

    @Override
    public List<Categoria> listarCategorias() {
        List <Categoria> lista = categoriaDAO.listAll();
        return (lista != null) ? lista : new ArrayList<>();
    }
}
