package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// ESTE MAIN ESTA PROBANDO LA CONEXION A BASE DE DATOS
// --> Se de

public class Main {
    public static void main(String[] args) {
        // 1. Cargamos el archivo db.properties desde resources [cite: 1]
        ResourceBundle db = ResourceBundle.getBundle("db");

        // 2. Extraemos los valores usando las llaves del archivo [cite: 1]
        String host = db.getString("host");
        int port = Integer.parseInt(db.getString("port"));
        String esquema = db.getString("database");
        String usuario = db.getString("user");
        String password = db.getString("password");

        // 3. Construimos la URL de conexión JDBC [cite: 1, 2]
        String url = "jdbc:mysql://" + host + ":" + port + "/" + esquema;

        // 4. Intentamos la conexión usando try-with-resources [cite: 2]
        try (Connection connection = DriverManager.getConnection(url, usuario, password)) {

            if (connection != null && !connection.isClosed()) {
                System.out.println("¡Conexión con la base de datos exitosa!");
            }

        } catch (SQLException e) {
            System.err.println("Error de conectividad JDBC:");
            e.printStackTrace();
        }
    }
}