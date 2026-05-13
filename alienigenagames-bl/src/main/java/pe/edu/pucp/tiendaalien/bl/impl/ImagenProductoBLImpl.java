package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IImagenProductoBL;
import pe.edu.pucp.tiendaalien.dao.ImagenProductoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.ImagenProductoDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.ImagenProducto;

public class ImagenProductoBLImpl implements IImagenProductoBL {
    private ImagenProductoDAO imagenProductoDAO = new ImagenProductoDAOImpl();

    // Create
    @Override
    public ImagenProducto agregarImagen(ImagenProducto imagenProducto) {
        return imagenProductoDAO.save(imagenProducto);
    }

    @Override
    public ImagenProducto cargarImagenPorId(Integer id) {
        return imagenProductoDAO.loadById(id);
    }

    @Override
    public ImagenProducto modificarImagen(ImagenProducto imagenProducto) {
        return imagenProductoDAO.update(imagenProducto);
    }

    @Override
    public void eliminarImagen(ImagenProducto imagenProducto) {
        imagenProductoDAO.remove(imagenProducto);
    }
}
