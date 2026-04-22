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
    public static void main(String[] args) {
        try {
            UbigeoDAO ubiDAO = new UbigeoDAOImpl();
            List<Ubigeo> lista = ubiDAO.listAll(); // Solo lee, no escribe

            System.out.println("Conexión exitosa.");
            System.out.println("Registros encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}