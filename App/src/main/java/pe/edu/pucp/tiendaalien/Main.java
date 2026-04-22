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
    private static UbigeoDAO ubiDAO;

    public static void main(String[] args) {
//        // PRUEBA 1: UBIGEO
//        System.out.println("\n>> [TEST UBIGEO]");
//        UbigeoDAO ubiDAO = new UbigeoDAOImpl();
//
//        // A. INSERTAR (save)
//        Ubigeo nuevoUbi = new Ubigeo();
//        nuevoUbi.setCodigo("157103");
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
//
//        // E. ELIMINAR (remove)
//        UbigeoDAO dao = new UbigeoDAOImpl();
//        Ubigeo ubiAEliminar = new Ubigeo();
//
//        ubiAEliminar.setUbigeoId(2); // Pon aquí el número que quieres borrar
//        dao.remove(ubiAEliminar);
//
//        System.out.println("Registro borrado con éxito desde Java.");
//
//        // ===============================================================================
//        // PRUEBA 2: AGENCIA DE ENVÍO
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
//
//        // E. ELIMINAR (remove)
//        AgenciaEnvioDAO ageDAO2 = new AgenciaEnvioDAOImpl();
//        AgenciaEnvio ageABorrar = new AgenciaEnvio();
//
//        ageABorrar.setAgenciaId(1);
//        ageDAO2.remove(ageABorrar);
//
//        System.out.println("5. ELIMINAR: Agencia eliminada correctamente de la base de datos.");
//
//        // ===============================================================================
//
//        // PRUEBA 3: USUARIO
//        System.out.println("\n>> [TEST USUARIO]");
//        UsuarioDAO userDAO = new UsuarioDAOImpl();
//
//        // A. INSERTAR (save)
//        Usuario nuevoUser = new Usuario();
//        nuevoUser.setNombres("Jennie");
//        nuevoUser.setApellidos("Kim");
//        nuevoUser.setEmail("jennie_ki88m@pucp.edu.pe");
//        nuevoUser.setContraHash("hash_seguro_123");
//        nuevoUser.setCelular("987654321");
//        nuevoUser.setRol(Rol.CLIENTE);
//        nuevoUser.setFechaCreacion(new java.util.Date());
//
//        userDAO.save(nuevoUser);
//        System.out.println("1. INSERTAR: Usuario creado con ID: " + nuevoUser.getUsuarioId());
//
//        // B. OBTENER POR ID (load)
//        Usuario userCargado = userDAO.load(nuevoUser.getUsuarioId());
//        System.out.println("2. OBTENER POR ID: Cargado: " + userCargado.getNombres() + " " + userCargado.getApellidos());
//
//
//        // C. MODIFICAR (update)
//        userCargado.setApellidos("Perez (Editado)");
//        userDAO.update(userCargado);
//        System.out.println("3. MODIFICAR: Apellido actualizado a: " + userDAO.load(nuevoUser.getUsuarioId()).getApellidos());
//
//
//        // D. LISTAR (listAll)
//        List<Usuario> listaUsers = userDAO.listAll();
//        System.out.println("4. LISTAR: Total de usuarios en el sistema: " + listaUsers.size());
//
//        // E. ELIMINAR
//        UsuarioDAO daoU = new UsuarioDAOImpl();
//        Usuario userAEliminar = new Usuario();
//        userAEliminar.setUsuarioId(1);
//        daoU.remove(userAEliminar);
//
//        System.out.println("5. ELIMINAR: Usuario eliminado correctamente.");

        // ===============================================================================
        // PRUEBA 4: MARCA
                System.out.println("\n>> [TEST MARCA]");
                MarcaDAO marcaDAO = new MarcaDAOImpl();

        // A. INSERTAR (save)
                Marca nuevaMarca = new Marca();
                nuevaMarca.setNombre("Bandai");
                marcaDAO.save(nuevaMarca);
                System.out.println("1. INSERTAR: Éxito. ID generado: " + nuevaMarca.getMarcaId());

        // B. OBTENER POR ID (load)
                Marca marcaCargada = marcaDAO.load(nuevaMarca.getMarcaId());
                System.out.println("2. OBTENER POR ID: Cargada: " + marcaCargada.getNombre());

        // C. MODIFICAR (update)
                marcaCargada.setNombre("Bandai Namco");
                marcaDAO.update(marcaCargada);
                System.out.println("3. MODIFICAR: Nombre actualizado a: " + marcaDAO.load(nuevaMarca.getMarcaId()).getNombre());

        // D. LISTAR (listAll)
                List<Marca> listaMarcas = marcaDAO.listAll();
                System.out.println("4. LISTAR: Total de marcas registradas: " + listaMarcas.size());

        // E. ELIMINAR (remove)
        // Creamos una instancia para borrar el registro que acabamos de usar en el test
                MarcaDAO marcaDAO2 = new MarcaDAOImpl();
                Marca marcaABorrar = new Marca();

                marcaABorrar.setMarcaId(nuevaMarca.getMarcaId());
                marcaDAO2.remove(marcaABorrar);

                System.out.println("5. ELIMINAR: Marca eliminada físicamente de la base de datos.");
        // ===============================================================================
    }
}