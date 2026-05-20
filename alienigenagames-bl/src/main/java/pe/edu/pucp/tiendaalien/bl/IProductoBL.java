package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

import java.sql.SQLException;
import java.util.List;

public interface IProductoBL {
    // Create
    Producto agregarProducto(Producto producto);

    // Read
    Producto cargarProductoPorId(int id);

    // Update
    Producto modificarProducto(Producto producto);

    // Delete
    void eliminarProducto(Producto producto);

    List<Producto> listarProductos() throws SQLException;
}
