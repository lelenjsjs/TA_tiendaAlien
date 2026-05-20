package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Categoria;

import java.util.List;

public interface ICategoriaBL {
    Categoria registrarCategoria(Categoria c);
    Categoria cargarCategoriaPorId(int id);
    Categoria cargarCategoriaPorNombre(String nombre);
    Categoria modificarCategoria(Categoria c);
    void eliminarCategoria(Categoria c);
    List<Categoria> listarCategorias();
}
