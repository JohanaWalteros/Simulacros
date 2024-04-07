package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;
import entity.Medic;
import entity.Patients;
import entity.Specialty;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Appointment objAppointment = (Appointment) obj;

        try {
            String sql = "INSERT INTO cita (fecha_cita , hora_cita, motivo, id_paciente, id_medico) VALUES (?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1, Date.valueOf(objAppointment.getDate()));
            objPrepare.setTime(2,Time.valueOf(objAppointment.getTime()));
            objPrepare.setString(3, objAppointment.getReason());
            objPrepare.setInt(4,objAppointment.getIdPatients());
            objPrepare.setInt(5,objAppointment.getIdMedic());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAppointment.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "El registro fue añadido correctamente");

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAppointment;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection =  ConfigDB.openConnection();

        List<Object> listAppoint = new ArrayList<>();

        try {
            //Sentencia SQL tomada de workbench
            String sql = "SELECT * FROM cita\n" +
                    "INNER JOIN paciente ON paciente.id_paciente = cita.id_paciente\n" +
                    "INNER JOIN medico ON medico.id_medico = cita.id_medico;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()){
                Appointment objAppointment = new Appointment();
                Medic objMedic = new Medic();
                Patients objPatient = new Patients();

                //Citas
                objAppointment.setId(objResult.getInt("cita.id_cita"));
                objAppointment.setDate(objResult.getString("cita.fecha_cita"));
                objAppointment.setTime(objResult.getString("cita.fecha_hora"));
                objAppointment.setReason(objResult.getString("cita.motivo"));
                objAppointment.setIdPatients(objResult.getInt("cita.id_paciente"));
                objAppointment.setIdMedic(objResult.getInt("cita.id_medico"));

                //Medico
                objMedic.setName(objResult.getString("medico.nombre"));
                objMedic.setLastName(objResult.getString("medico.apellidos"));

                //Paciente
                objPatient.setName(objResult.getString("especialidad.nombre"));
                objPatient.setLastName(objResult.getString("especialidad.apellido"));

                objAppointment.setObjMedic(objMedic);
                objAppointment.setObjPatient(objPatient);

                listAppoint.add(objAppointment);
            }

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listAppoint;
    }

    @Override
    public boolean update(Object obj) {
        return false;
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
