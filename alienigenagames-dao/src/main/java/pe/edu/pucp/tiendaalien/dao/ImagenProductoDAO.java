package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.dao.base.BaseDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.ImagenProducto;
import java.util.List;

public interface ImagenProductoDAO extends BaseDAO<ImagenProducto, Integer> {
    ImagenProducto load(Integer id);
    ImagenProducto save(ImagenProducto t);
    ImagenProducto update(ImagenProducto t);
    void remove(ImagenProducto t);
    List<ImagenProducto> listAll();
}