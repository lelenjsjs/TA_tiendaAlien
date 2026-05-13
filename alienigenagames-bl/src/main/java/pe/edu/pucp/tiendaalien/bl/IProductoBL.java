package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

public interface IProductoBL {
    // Create
    Producto agregarProducto(Producto producto);

    // Read
    Producto cargarProductoPorId(int id);

    // Update
    Producto modificarProducto(Producto producto);

    // Delete
    void eliminarProducto(Producto producto);
}
