package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Franquicia;
import java.util.List;

public interface IFranquiciaBL {
    List<Franquicia> listarFranquicias() throws BusinessLogicException;
    Franquicia cargarFranquiciaPorId(Integer id) throws BusinessLogicException;
    Franquicia registrarFranquicia(Franquicia franquicia) throws BusinessLogicException;
    Franquicia modificarFranquicia(Franquicia franquicia) throws BusinessLogicException;
    void eliminarFranquicia(Franquicia franquicia) throws BusinessLogicException;
}