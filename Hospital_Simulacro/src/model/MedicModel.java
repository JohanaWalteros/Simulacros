package model;

import database.CRUD;
import database.ConfigDB;
import entity.Medic;
import entity.Specialty;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Medic objMedic = (Medic) obj;

        try {
            String sql = "INSERT INTO medico (nombre, apellidos, id_especialidad) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objMedic.getName());
            objPrepare.setString(2,objMedic.getLastName());
            objPrepare.setInt(3,objMedic.getIdSpecialty());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objMedic.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "El registro fue añadido correctamente");

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objMedic;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection =  ConfigDB.openConnection();

        List<Object> listMedics = new ArrayList<>();

        try {
            //Unir el médico con la información de la especialidad  con la siguiente sentencia sql
            String sql = "SELECT * FROM medico\n" +
                    "INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()){
                Medic objMedic = new Medic();
                Specialty objSpecialty = new Specialty();

                objMedic.setId(objResult.getInt("medico.id_medico"));
                objMedic.setName(objResult.getString("medico.nombre"));
                objMedic.setLastName(objResult.getString("medico.apellidos"));
                objMedic.setIdSpecialty(objResult.getInt("medico.id_especialidad"));

                objSpecialty.setId(objResult.getInt("especialidad.id_especialidad"));
                objSpecialty.setName(objResult.getString("especialidad.nombre"));
                objSpecialty.setDescription(objResult.getString("especialidad.descripcion"));

                objMedic.setObjSpecialty(objSpecialty);

                listMedics.add(objMedic);
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listMedics;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Medic objMedic = (Medic) obj;

        boolean isUpdate = false;

        try {
            String sql = "UPDATE medico SET nombre = ?, apellidos = ?, id_especialidad = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, objMedic.getName());
            objPrepared.setString(2,objMedic.getLastName());
            objPrepared.setInt(3,objMedic.getIdSpecialty());

            int totalAffectedRows = objPrepared.executeUpdate();
            if(totalAffectedRows > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Actualización exitosa 👍");
            }
        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medic objMedic = (Medic) obj;

        boolean isDelete = false;

        try {
            String sql ="DELETE FROM medico WHERE id_medico = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objMedic.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }

}
