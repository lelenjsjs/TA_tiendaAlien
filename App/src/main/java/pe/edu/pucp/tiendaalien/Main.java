package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import pe.edu.pucp.tiendaalien.dao.UbigeoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UbigeoDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import java.util.List;


public class Main {
    private static UbigeoDAO ubiDAO;

    public static void main(String[] args) {

        // PRUEBA 1: UBIGEO
        System.out.println("\n>> [TEST UBIGEO]");
        UbigeoDAO ubiDAO = new UbigeoDAOImpl();

        // A. INSERTAR (save)
        Ubigeo nuevoUbi = new Ubigeo();
        nuevoUbi.setCodigo("150101");
        nuevoUbi.setDepartamento("Lima");
        nuevoUbi.setProvincia("Lima");
        nuevoUbi.setDistrito("Lima Cercado");
        ubiDAO.save(nuevoUbi);
        System.out.println("1. INSERTAR: Éxito. ID generado: " + nuevoUbi.getUbigeoId());

        // B. OBTENER POR ID (load)
        Ubigeo ubiCargado = ubiDAO.load(nuevoUbi.getUbigeoId());
        System.out.println("2. OBTENER POR ID: Cargado: " + ubiCargado.getDistrito());

        // C. MODIFICAR (update)
        ubiCargado.setDistrito("Lima Cercado - MODIFICADO");
        ubiDAO.update(ubiCargado);
        System.out.println("3. MODIFICAR: Distrito cambiado a: " + ubiDAO.load(nuevoUbi.getUbigeoId()).getDistrito());

        // D. LISTAR (listAll)
        List<Ubigeo> listaUbi = ubiDAO.listAll();
        System.out.println("4. LISTAR: Total de registros encontrados: " + listaUbi.size());

        // E. ELIMINAR (remove)
//        UbigeoDAO dao = new UbigeoDAOImpl();
//        Ubigeo ubiAEliminar = new Ubigeo();
//
//        ubiAEliminar.setUbigeoId(2); // Pon aquí el número que quieres borrar
//        dao.remove(ubiAEliminar);
//
//        System.out.println("Registro borrado con éxito desde Java.");

        //  ========================================================================

    }
}