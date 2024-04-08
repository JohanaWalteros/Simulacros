package model;

import database.CRUD;
import database.ConfigDB;
import entity.Customer;
import utils.Utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir(castear)
        Customer objCustomer = (Customer) obj;

        //4. Control de errores try-catch
        try {
            //1. Crear la sentencia sql para insertar
            String sql = "INSERT INTO cliente (nombre, apellido, email) VALUES (?,?,?);";

            //2. Preparar el Statement, retornar las llaves generadas
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Valor a interrogativos
            objPrepared.setString(1,objCustomer.getName());
            objPrepared.setString(2,objCustomer.getLastName());
            objPrepared.setString(3,objCustomer.getEmail());

            //5. Ejecutar la sentencia preparada que es una consulta a SQL
            objPrepared.execute();

            //6. Obtener las llaver generadas
            ResultSet objResult = objPrepared.getGeneratedKeys();

            //7. Recorrer ResulSet
            while (objResult.next()){
                objCustomer.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"El cliente fue agregado correctamente");

        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        //3. Cerrar la conexión
        ConfigDB.closeConnection();

        //5. Retornar el objeto creado
        return objCustomer;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Crear una lista de objetos: para guardar los registros que devuelva la BD
        List<Object> listCustomer = new ArrayList<>();

        //4. Control de errores try-catch
        try {

            //1. Crear la sentencia sql para listar
            String sql = "SELECT * FROM cliente;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Ejecutar la sentencia preparada que es una consulta a SQL. Trae toda la información de la tabla cliente
            ResultSet objResul = objPrepared.executeQuery();

            //5. Recorrer ResulSet
            while (objResul.next()){
                //1. Crear la instancia: Por cada iteración se crea una nueva instancia de Customer
                Customer objCustomer = new Customer();

                //2. Guardar datos en objCustomer
                objCustomer.setId(objResul.getInt("id_cliente"));
                objCustomer.setName(objResul.getString("nombre"));
                objCustomer.setEmail(objResul.getString("email"));

                //3. Guardar en la lista los objetos creados
                listCustomer.add(objCustomer);
            }

            //3. Control de SQL exeption
        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        //2. Cerrar la conexión
        ConfigDB.closeConnection();

        //5. Retorno
        return listCustomer;

    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Casting
        Customer objCustomer = (Customer) obj;

        //3. Crear una variable de tipo boolean, para saber si fue actualizado correctamente
        boolean isUpdated = false;

        try {
            //1. Sentencia SQL
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?; email =?";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Valor a interrogativos
            objPrepared.setString(1, objCustomer.getName());
            objPrepared.setString(2, objCustomer.getLastName());
            objPrepared.setString(3, objCustomer.getEmail());


            //5. Ejecutar la sentencia preparada
            int totalAffectedRows = objPrepared.executeUpdate();

            //6. Condicional de total filas afectadas
            if(totalAffectedRows > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
            }

            //3. Control SQL
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Casting
        Customer objCustomer = (Customer) obj;

        //4. Crear una variable de tipo boolean, para saber si fue eliminado correctamente
        boolean isDelete = false;

        //5. Try catch
        try{

            //1. Sentencia SQL
            String sql = "DELETE FROM cliente WHERE id_cliente = ?;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Valor a interrogativos
            objPrepared.setInt(1, objCustomer.getId());

            //5. Ejecutar la sentencia preparada: executeUpdate(), devuelve el número de filas afectadas
            int totalAffectedRows = objPrepared.executeUpdate();

            //6. Condicional de total filas afectadas
            if(totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

            //3.Control de SQL
        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        //2. Cerrar conexión
        ConfigDB.openConnection();

        //6. Retornar
        return isDelete;

    }

    public static CustomerModel instanceModel(){
        return new CustomerModel();
    }

}
