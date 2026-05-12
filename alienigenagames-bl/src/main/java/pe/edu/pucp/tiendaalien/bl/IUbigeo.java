package pe.edu.pucp.tiendaalien.bl;

import pe.edu.pucp.tiendaalien.model.usuario.Ubigeo; // Ajusta el import según tu paquete
import java.util.List;

public interface IUbigeoBL {
    List<Ubigeo> listarUbigeos() throws BusinessLogicException;
    Ubigeo cargarUbigeoPorId(Integer id) throws BusinessLogicException;
    Ubigeo registrarUbigeo(Ubigeo ubigeo) throws BusinessLogicException;
    Ubigeo modificarUbigeo(Ubigeo ubigeo) throws BusinessLogicException;
    void eliminarUbigeo(Ubigeo ubigeo) throws BusinessLogicException;
}