package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//1. Importar el drive de coenxión
public class ConfigDB {
    //2. Declarar la variable objConnection
    public static Connection objConnection = null;

    //3. Método para abrir la conexión
    public static Connection openConnection() {

        //4. Control de errores try-catch
        try {
            //4. Llamar el drive de conexión
            Class.forName("com.mysql.cj.jdbc.Driver");

            //5. Variables de conexión
            String url = "jdbc:mysql://localhost:3306/tienda";
            String user = "root";
            String password = "";

            //6. Establecer conexión
            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado perfectamente");

            //4.Control de exception
        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver no Instalado" + e.getMessage());
        }//5. Control de exception
        catch (SQLException e) {
            System.out.println("Error >> No se pudo establecer una conexión" + e.getMessage());
        }
        //7. Retornar
        return objConnection;
    }

    //8. Crear el método para cerrar la conexión
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
    //9. CREAR LA ENTIDAD TIENDA
}
