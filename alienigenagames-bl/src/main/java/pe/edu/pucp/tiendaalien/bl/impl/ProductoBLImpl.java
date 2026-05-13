package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IProductoBL;
import pe.edu.pucp.tiendaalien.dao.ProductoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.ProductoDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

public class ProductoBLImpl implements IProductoBL {
    ProductoDAO productoDAO = new ProductoDAOImpl();
    @Override
    public Producto agregarProducto(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public Producto cargarProductoPorId(int id) {
        return productoDAO.loadById(id);
    }

    @Override
    public Producto modificarProducto(Producto producto) {
        return productoDAO.update(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoDAO.remove(producto);
    }
}
