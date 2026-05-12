package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.catalogo.Marca;
import java.util.List;

public interface IMarcaBL {
    List<Marca> listarMarcas() throws BusinessLogicException;
    Marca cargarMarcaPorId(Integer id) throws BusinessLogicException;
    Marca registrarMarca(Marca marca) throws BusinessLogicException;
    Marca modificarMarca(Marca marca) throws BusinessLogicException;
    void eliminarMarca(Marca marca) throws BusinessLogicException;
}