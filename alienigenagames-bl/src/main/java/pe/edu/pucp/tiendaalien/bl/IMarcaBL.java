package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Marca;

public interface IMarcaBL {
    // CRUD
    // Create
    Marca registrarMarca(Marca marca);

    // Read
    Marca cargarMarcaPorId(Integer id);

    // Update
    Marca modificarMarca(Marca marca);

    // Delete
    void eliminarMarca(Marca marca);
}
