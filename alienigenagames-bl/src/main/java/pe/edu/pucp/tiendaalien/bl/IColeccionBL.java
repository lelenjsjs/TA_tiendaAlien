package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;


public interface IColeccionBL {
    // CRUD
    // Create
    Coleccion registrarColeccion(Coleccion coleccion);

    // Read
    Coleccion cargarColeccionPorId(Integer id);

    // Update
    Coleccion modificarColeccion(Coleccion coleccion);

    // Delete
    void eliminarColeccion(Coleccion coleccion);
}
