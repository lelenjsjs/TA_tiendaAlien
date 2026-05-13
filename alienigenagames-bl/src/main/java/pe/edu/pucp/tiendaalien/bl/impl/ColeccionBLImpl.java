package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IColeccionBL;
import pe.edu.pucp.tiendaalien.dao.ColeccionDAO;

import pe.edu.pucp.tiendaalien.dao.impl.ColeccionDAOImpl;

import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;

public class ColeccionBLImpl implements IColeccionBL {
    ColeccionDAO coleccionDAO = new ColeccionDAOImpl();
    @Override
    public Coleccion registrarColeccion(Coleccion coleccion) {
        return coleccionDAO.save(coleccion);
    }

    @Override
    public Coleccion cargarColeccionPorId(Integer id) {
        return coleccionDAO.loadById(id);
    }

    @Override
    public Coleccion modificarColeccion(Coleccion coleccion) {
        return coleccionDAO.update(coleccion);
    }

    @Override
    public void eliminarColeccion(Coleccion coleccion) {
        coleccionDAO.remove(coleccion);
    }
}
