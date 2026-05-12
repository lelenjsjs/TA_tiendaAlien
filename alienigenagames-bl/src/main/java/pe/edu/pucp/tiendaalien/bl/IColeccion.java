package pe.edu.pucp.tiendaalien.dao;

import pe.edu.pucp.tiendaalien.model.catalogo.Coleccion;
import java.util.List;

public interface ColeccionDAO {
    Coleccion load(Integer id);
    Coleccion save(Coleccion coleccion);
    Coleccion update(Coleccion coleccion);
    void remove(Coleccion coleccion);
    List<Coleccion> listAll();
}