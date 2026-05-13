package pe.edu.pucp.tiendaalien.bl.impl;

import pe.edu.pucp.tiendaalien.bl.IFranquiciaBL;
import pe.edu.pucp.tiendaalien.dao.FranquiciaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.FranquiciaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;

public class FranquiciaBLImpl implements IFranquiciaBL {
    FranquiciaDAO franquiciaDAO =  new FranquiciaDAOImpl();
    @Override
    public Franquicia registrarFranquicia(Franquicia franquicia) {
        return franquiciaDAO.save(franquicia);
    }

    @Override
    public Franquicia cargarFranquiciaPorId(Integer id) {
        return franquiciaDAO.loadById(id);
    }

    @Override
    public Franquicia modificarFranquicia(Franquicia franquicia) {
        return franquiciaDAO.update(franquicia);
    }

    @Override
    public void eliminarFranquicia(Franquicia franquicia) {
        franquiciaDAO.remove(franquicia);
    }
}
