package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.ImagenProducto;

public interface IImagenProductoBL {
    // Create
    ImagenProducto agregarImagen(ImagenProducto imagenProducto);

    // Read
    ImagenProducto cargarImagenPorId(Integer id);

    // Update
    ImagenProducto modificarImagen(ImagenProducto imagenProducto);

    // Delete
    void eliminarImagen(ImagenProducto imagenProducto);
}
