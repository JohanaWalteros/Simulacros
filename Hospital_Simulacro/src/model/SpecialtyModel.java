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

    //1. Método Crear
    @Override
    public Object insert(Object obj) {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir(castear) Object obj a la clase especialidades
        Specialty objSpecialty = (Specialty) obj;

        //4. Control de errores try-catch
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

        //3. Cerrar la conexión
        ConfigDB.closeConnection();

        //5. Retornar el objeto creado
        return objSpecialty;

        //6. CREAR EL CONTROLADOR DE SPECIALTY Y CONFIGURAR LA LÓGICA DEL MÉTODO
    }

    //2. Método Leer todos los existentes
    @Override
    public List<Object> findAll() {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Crear una lista de objetos: para guardar los registros que devuelva la BD
        List<Object> listSpecialties = new ArrayList<>();

        //4. Control de errores try-catch
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

        //2. Cerrar la conexión
        ConfigDB.closeConnection();

        //5. Retorno
        return listSpecialties;

        //6.CREAR LA LÓGICA DEL MODELO EN EL CONTROLLER
    }

    //4. Actualizar = Update
    @Override
    public boolean update(Object obj) {
        //1. Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Casting
        Specialty objSpecialty = (Specialty) obj;

        //3. Crear una variable de tipo boolean, para saber si fue actualizado correctamente
        boolean isUpdated = false;

        try {
            //1. Sentencia SQL
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ?;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Valor a interrogativos
            objPrepared.setString(1, objSpecialty.getName());
            objPrepared.setString(2, objSpecialty.getDescription());

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

        //2. Cerrar conexión
        return isUpdated;

        //4. CREAR LA LÓGICA DEL MODELO EN EL CONTROLLER
    }

    //3. Eliminar = Delete
    @Override
    public boolean delete(Object obj) {
        //1. Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Casting
        Specialty objSpecialty = (Specialty) obj;

        //4. Crear una variable de tipo boolean, para saber si fue eliminado correctamente
        boolean isDelete = false;

        //5. Try catch
        try{

            //1. Sentencia SQL
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Valor a interrogativos
            objPrepared.setInt(1, objSpecialty.getId());

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

        //7. CREAR LA LÓGICA DEL MODELO EN EL CONTROLLER
    }
}
