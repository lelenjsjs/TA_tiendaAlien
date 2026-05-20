package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IMarcaBL;
import pe.edu.pucp.tiendaalien.dao.MarcaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.MarcaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Marca;

public class MarcaBLImpl implements IMarcaBL {
    MarcaDAO marcaDAO = new MarcaDAOImpl();
    @Override
    public Marca registrarMarca(Marca marca) {
        return marcaDAO.save(marca);
    }

    @Override
    public Marca cargarMarcaPorId(Integer id) {
        return marcaDAO.loadById(id);
    }

    @Override
    public Marca modificarMarca(Marca marca) {
        return marcaDAO.update(marca);
    }

    @Override
    public void eliminarMarca(Marca marca) {
        marcaDAO.remove(marca);
    }
}
