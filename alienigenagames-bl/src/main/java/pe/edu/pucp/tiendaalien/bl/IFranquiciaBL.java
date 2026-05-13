package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;

public interface IFranquiciaBL {
    // CRUD
    // Create
    Franquicia registrarFranquicia(Franquicia franquicia);

    // Read
    Franquicia cargarFranquiciaPorId(Integer id);

    // Update
    Franquicia modificarFranquicia(Franquicia franquicia);

    // Delete
    void eliminarFranquicia(Franquicia franquicia);
}
