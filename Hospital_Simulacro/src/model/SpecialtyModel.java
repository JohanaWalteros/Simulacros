package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//1. Implementar los métodos del CRUD
public class SpecialtyModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir(castear) Object obj a la clase especialidades
        Specialty objSpecialty = (Specialty) obj;

        //3. Control de errores try-catch
        try {
            //1. Crear la sentencia sql para insertar
            String sql = "INSERT INTO especialidad (nombre, descripcion) VALUES (?,?);";

            //2. Preparar el Statement, retornar las llaves generadas
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //4. Valor a interrogativos
            objPrepared.setString(1,objSpecialty.getName());
            objPrepared.setString(2,objSpecialty.getDescription());

            //5. Ejecutar la sentencia preparada que es una consulta a SQL
            objPrepared.execute();

            //6. Obtener las llaver generadas
            ResultSet objResult = objPrepared.getGeneratedKeys();

            //7. Recorrer ResulSet
            while (objResult.next()){
                objSpecialty.setId(objResult.getInt(1));
            }

            //8.Mensaje de aprobación
            JOptionPane.showMessageDialog(null,"La especialidad fue agregada correctamente");

            //3. Control de SQL exeption
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();

        //9. Retornar el objeto creado
        return objSpecialty;

        //10. CREAR EL CONTROLADOR DE SPECIALTY
    }

    @Override
    public List<Object> findAll() {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear una lista de objetos: para guardar los registros que devuelva la BD
        List<Object> listSpecialties = new ArrayList<>();

        //3. Control de errores try-catch
        try {

            //1. Crear la sentencia sql para listar
            String sql = "SELECT * FROM especialidad;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Ejecutar la sentencia preparada que es una consulta a SQL. Trae toda la información de la tabla especialidad
            ResultSet objResul = objPrepared.executeQuery();

            //5. Recorrer ResulSet
            while (objResul.next()){
                //1. Crear la instancia: Por cada iteración se crea una nueva instancia de specialty
                Specialty objSpecialty = new Specialty();

                //2. Guardar id, nombre y descripción en objSpecialty
                objSpecialty.setId(objResul.getInt("id_especialidad"));
                objSpecialty.setName(objResul.getString("nombre"));
                objSpecialty.setDescription(objResul.getString("descripcion"));

                //3. Guardar en la lista los objetos creados
                listSpecialties.add(objSpecialty);
            }

            //3. Control de SQL exeption
        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listSpecialties;

        //CREAR EL MÉTODO SPECIALTYCONTROLLER
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
