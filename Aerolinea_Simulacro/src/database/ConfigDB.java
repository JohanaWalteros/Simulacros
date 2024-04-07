package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;
    public static Connection openConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://b4cnezttqhjdqqxytilv-mysql.services.clever-cloud.com/b4cnezttqhjdqqxytilv";
            String user = "ujgpd9lgnreqwghj";
            String password = "ITrRyYcvdaAzN3XTXbKO";

            objConnection = DriverManager.getConnection(url, user, password);

            System.out.println("Conectado perfectamente");

        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver no Instalado" + e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("Error >> No se pudo establecer una conexión con la BD" + e.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection() {
        try {
            //1. Condicional: Si hay una conexión activa, la cerramos
            if (objConnection != null) objConnection.close();
            System.out.println("La conexión ha finalizado");

            //2. Control de error
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
