package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Airplane objAirplane = (Airplane) obj;

        try {
            String sql = "INSERT INTO avion (modelo, capcidad) VALUES (?,?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1,objAirplane.getModel());
            objPrepared.setInt(2,objAirplane.getCapacity());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while (objResult.next()){
                objAirplane.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"El avión fue agregado correctamente 👍");

        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAirplane;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listAirplane = new ArrayList<>();

        try {

            String sql = "SELECT * FROM avion;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResul = objPrepared.executeQuery();

            while (objResul.next()){

               Airplane objAirplane = new Airplane();

                //2. Guardar id, nombre y descripción en objAirplane
                objAirplane.setId(objResul.getInt("id_avion"));
                objAirplane.setModel(objResul.getString("modelo"));
                objAirplane.setCapacity(objResul.getInt("capcidad"));

                listAirplane.add(objAirplane);
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listAirplane;

    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Airplane objAirplane = (Airplane) obj;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE avion SET modelo = ?, capcidad = ?;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Valor a interrogativos
            objPrepared.setString(1, objAirplane.getModel());
            objPrepared.setInt(2, objAirplane.getCapacity());

            int totalAffectedRows = objPrepared.executeUpdate();

            if(totalAffectedRows > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
            }

        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        return isUpdated;

    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Airplane objAirplane = (Airplane) obj;
        
        boolean isDelete = false;

        try{
            String sql = "DELETE FROM avion WHERE id_avion = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, objAirplane.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if(totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.openConnection();

        return isDelete;
    }
}
