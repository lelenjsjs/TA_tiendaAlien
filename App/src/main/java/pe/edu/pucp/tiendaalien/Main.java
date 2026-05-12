package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IAgenciaEnvioBL;
import pe.edu.pucp.tiendaalien.bl.impl.AgenciaEnvioBLImpl;
import pe.edu.pucp.tiendaalien.dao.UbigeoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UbigeoDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import pe.edu.pucp.tiendaalien.dao.AgenciaEnvioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.AgenciaEnvioDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import pe.edu.pucp.tiendaalien.dao.UsuarioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UsuarioDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol;

import pe.edu.pucp.tiendaalien.dao.MarcaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.MarcaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Marca;




import java.util.List;


// Cuando corra las pruebas se solicita colocar diferentes IDs porque sino da error de duplicado

public class Main {

    public static void main(String[] args) throws BusinessLogicException {
        /* ////////////////////////// AGENCIA DE ENVIO /////////////////////////////// */
        IAgenciaEnvioBL agenciaEnvioBL = new AgenciaEnvioBLImpl();

        /* 1. Registrar una agencia de envio: CREATE =====================================================*/
        String nombreAgencia = "Shamurr";
        String url = "https:/www.shamurr.com";
        AgenciaEnvio agenciaRecibida = new AgenciaEnvio(nombreAgencia,url);
        agenciaRecibida = agenciaEnvioBL.registrarAgencia(agenciaRecibida);

        System.out.println(agenciaRecibida);

        /* 2. Buscar agencia por ID: READ ================================================================*/
        int idDeLaAgencia = 2;// !!!!!!!!!!!!!!!!!
        AgenciaEnvio agenciaEnvioPorId = agenciaEnvioBL.cargarAgenciaPorId(idDeLaAgencia);
        System.out.println("Buscando Agencia " + "con ID = ...."+idDeLaAgencia);
        if(agenciaEnvioPorId == null){
            System.out.println("Agencia no encontrada");
        }
        else {
            System.out.println(agenciaEnvioPorId);
        }

        /* 3. Actualizar una agencia de envio: UPDATE =====================================================*/
        String nombreOriginalAgencia = "Shalom";// !!!!!!!!!!!!!
        AgenciaEnvio agenciaACambiar = agenciaEnvioBL.cargarAgenciaPorNombre(nombreOriginalAgencia);
        System.out.println(agenciaACambiar);

        String nuevoUrl = "https://shalomurl.pe/rastreo"; // !!!!!!!!!!!!
        agenciaACambiar.setUrlTracking(nuevoUrl);// !!!!!!!!!!!!!

        agenciaACambiar = agenciaEnvioBL.modificarAgencia(agenciaACambiar);
        System.out.println(agenciaACambiar);

        /* 4. Desactivar la agencia de envio: "DELETE" ==================================================== */
        String nombreAgenciaAEliminar = "Shalom";// !!!!!!!!!!!!!!!!
        AgenciaEnvio agenciaAEliminar = agenciaEnvioBL.cargarAgenciaPorNombre(nombreAgenciaAEliminar);

        System.out.println("Eliminare : " + nombreAgenciaAEliminar);
        agenciaEnvioBL.eliminarAgencia(agenciaAEliminar);

        /* 5. Listar todas ================================================================================ */
        List<AgenciaEnvio> listaAgenciasEnvio = agenciaEnvioBL.listarAgenciasDeEnvio();

        if(listaAgenciasEnvio.isEmpty()){
            System.out.println("No hay agencias de envio registradas");
        }
        else {
            System.out.println("AGENCIAS DE ENVIO");
            for (AgenciaEnvio agenciaEnvio : listaAgenciasEnvio) {
                System.out.println(agenciaEnvio);
            }
        }

        /* 6. Buscar agencia por nombre ================================================================== */
        String nombreAgenciaABuscar = "Olva Courier";
        AgenciaEnvio agenciaEnvioPorNom = agenciaEnvioBL.cargarAgenciaPorNombre(nombreAgenciaABuscar);
        System.out.println("Buscando Agencia " + nombreAgenciaABuscar);
        if(agenciaEnvioPorNom == null){
            System.out.println("Agencia no encontrada");
        }
        else {
            System.out.println(agenciaEnvioPorNom);
        }




    }
}