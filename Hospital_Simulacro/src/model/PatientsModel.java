package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patients;
import entity.Specialty;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientsModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Patients objPatients = (Patients) obj;

        try {
            String sql = "INSERT INTO paciente (nombre, apellido, fecha_nacimiento, documento_identidad) VALUES (?,?,?,?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1,objPatients.getName());
            objPrepared.setString(2,objPatients.getLastName());
            objPrepared.setDate(3, Date.valueOf(objPatients.getDateBirth()));
            objPrepared.setString(4,objPatients.getDocument());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while (objResult.next()){
                objPatients.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null,"El paciente fue agregado correctamente");
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPatients;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listPatientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM paciente;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            
            ResultSet objResul = objPrepared.executeQuery();
            
            while (objResul.next()){                
                Patients objPatients = new Patients();

                objPatients.setId(objResul.getInt("id_paciente"));
                objPatients.setName(objResul.getString("nombre"));
                objPatients.setLastName(objResul.getString("apellido"));
                objPatients.setDateBirth(objResul.getString("fecha_nacimiento"));
                objPatients.setDocument(objResul.getString("documento_identidad"));

                listPatientes.add(objPatients);
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listPatientes;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Patients objPatients = (Patients) obj;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE paciente SET nombre = ?, apellido = ?, fecha_nacimiento = ?, documento_identidad = ? " +
                    "WHERE id_paciente = ?";
            
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objPatients.getName());
            objPrepared.setString(2,objPatients.getLastName());
            objPrepared.setDate(3, Date.valueOf(objPatients.getDateBirth()));
            objPrepared.setString(4,objPatients.getDocument());
            objPrepared.setInt(5,objPatients.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if(totalAffectedRows > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
            }
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Patients objPatients = (Patients) obj;

         boolean isDelete = false;

         try {
             String sql = "DELETE FROM medico WHERE id_medico = ?";

             PreparedStatement objPrepared = objConnection.prepareStatement(sql);

             objPrepared.setInt(1, objPatients.getId());

             int totalAffectedRows = objPrepared.executeUpdate();

             if(totalAffectedRows > 0){
                 isDelete = true;
                 JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
             }

         }catch (SQLException e){
             System.out.println("Error"+ e.getMessage());
         }

        ConfigDB.closeConnection();
        return isDelete;
    }
}
