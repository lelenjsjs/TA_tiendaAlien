package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import pe.edu.pucp.tiendaalien.dao.UbigeoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UbigeoDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import pe.edu.pucp.tiendaalien.dao.AgenciaEnvioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.AgenciaEnvioDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import java.util.List;


public class Main {
    private static UbigeoDAO ubiDAO;

    public static void main(String[] args) {

        // PRUEBA 1: UBIGEO
//        System.out.println("\n>> [TEST UBIGEO]");
//        UbigeoDAO ubiDAO = new UbigeoDAOImpl();
//
//        // A. INSERTAR (save)
//        Ubigeo nuevoUbi = new Ubigeo();
//        nuevoUbi.setCodigo("150101");
//        nuevoUbi.setDepartamento("Lima");
//        nuevoUbi.setProvincia("Lima");
//        nuevoUbi.setDistrito("Lima Cercado");
//        ubiDAO.save(nuevoUbi);
//        System.out.println("1. INSERTAR: Éxito. ID generado: " + nuevoUbi.getUbigeoId());
//
//        // B. OBTENER POR ID (load)
//        Ubigeo ubiCargado = ubiDAO.load(nuevoUbi.getUbigeoId());
//        System.out.println("2. OBTENER POR ID: Cargado: " + ubiCargado.getDistrito());
//
//        // C. MODIFICAR (update)
//        ubiCargado.setDistrito("Lima Cercado - MODIFICADO");
//        ubiDAO.update(ubiCargado);
//        System.out.println("3. MODIFICAR: Distrito cambiado a: " + ubiDAO.load(nuevoUbi.getUbigeoId()).getDistrito());
//
//        // D. LISTAR (listAll)
//        List<Ubigeo> listaUbi = ubiDAO.listAll();
//        System.out.println("4. LISTAR: Total de registros encontrados: " + listaUbi.size());

        // E. ELIMINAR (remove)
//        UbigeoDAO dao = new UbigeoDAOImpl();
//        Ubigeo ubiAEliminar = new Ubigeo();
//
//        ubiAEliminar.setUbigeoId(2); // Pon aquí el número que quieres borrar
//        dao.remove(ubiAEliminar);
//
//        System.out.println("Registro borrado con éxito desde Java.");

        // ===============================================================================

        // PRUEBA 2: AGENCIA DE ENVÍO
//        System.out.println("\n>> [TEST AGENCIA DE ENVÍO]");
//        AgenciaEnvioDAO ageDAO = new AgenciaEnvioDAOImpl();
//
//        // A. INSERTAR (save)
//        AgenciaEnvio nuevaAge = new AgenciaEnvio();
//        nuevaAge.setNombre("Olva");
//        nuevaAge.setUrlTracking("https://track.olva.com/");
//        ageDAO.save(nuevaAge);
//        System.out.println("1. INSERTAR: Éxito. ID generado: " + nuevaAge.getAgenciaId());
//
//        // B. OBTENER POR ID (load)
//        AgenciaEnvio ageCargada = ageDAO.load(nuevaAge.getAgenciaId());
//        System.out.println("2. OBTENER POR ID: Agencia cargada: " + ageCargada.getNombre());
//
//        // C. MODIFICAR (update)
//        ageCargada.setNombre("Olva express");
//        ageDAO.update(ageCargada);
//        System.out.println("3. MODIFICAR: Nombre actualizado a: " + ageDAO.load(nuevaAge.getAgenciaId()).getNombre());
//
//        // D. LISTAR (listAll)
//        List<AgenciaEnvio> listaAge = ageDAO.listAll();
//        System.out.println("4. LISTAR: Total de agencias en BD: " + listaAge.size());

        // E. ELIMINAR (remove)
//        AgenciaEnvioDAO ageDAO2 = new AgenciaEnvioDAOImpl();
//        AgenciaEnvio ageABorrar = new AgenciaEnvio();
//
//        ageABorrar.setAgenciaId(1);
//        ageDAO2.remove(ageABorrar);
//
//        System.out.println("5. ELIMINAR: Agencia eliminada correctamente de la base de datos.");

    }
}