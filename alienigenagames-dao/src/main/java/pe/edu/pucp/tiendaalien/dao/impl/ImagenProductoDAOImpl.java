package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.dao.ImagenProductoDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.ImagenProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImagenProductoDAOImpl implements ImagenProductoDAO {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public ImagenProductoDAO load(Integer integer) {
        return null;
    }

    @Override
    public ImagenProductoDAO save(ImagenProductoDAO imagenProductoDAO) {
        return null;
    }

    @Override
    public ImagenProductoDAO update(ImagenProductoDAO imagenProductoDAO) {
        return null;
    }

    @Override
    public void remove(ImagenProductoDAO imagenProductoDAO) {

    }

}