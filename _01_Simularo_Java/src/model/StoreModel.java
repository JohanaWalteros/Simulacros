package model;

import database.ConfigDB;
import entity.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreModel {

    //1. Listar: solo requiero la información de la tienda
    public List<Object> findAll(){
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear una lista de objetos: para guardar los registros que devuelva la BD
        List<Object> listStore = new ArrayList<>();

        //3. Control de errores
        try {
            //1. Crear la sentencia sql para listar
            String sql = "SELECT * FROM tienda;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Ejecutar la sentencia preparada que es una consulta a SQL. Trae toda la información de la tabla especialidad
            ResultSet objResul = objPrepared.executeQuery();

            //5. Recorrer ResulSet
            while (objResul.next()){
                //1. Crear la instancia: Por cada iteración se crea una nueva instancia de store
                Store objStore = new Store();

                //2. Guardar nombre y ubicación en objStore
                objStore.setId(objResul.getInt("id_tienda"));
                objStore.setName(objResul.getString("nombre"));
                objStore.setLocation(objResul.getString("ubicacion"));

                //3. Guardar en la lista los objetos creados
                listStore.add(objStore);
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

           ConfigDB.closeConnection();
        return listStore;
    }


}
