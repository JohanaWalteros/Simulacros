package controllers;

import entity.Appointment;
import entity.Medic;
import entity.Patients;
import entity.Specialty;
import model.AppointmentModel;
import model.MedicModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AppointmentController {
    public static void insert(){

        String date = JOptionPane.showInputDialog("Ingrese la fecha de la cita YYY-MM-DD");
        String time = JOptionPane.showInputDialog("Ingrese la hora de la cita HH:MM:SS");
        String reason = JOptionPane.showInputDialog("Ingrese el motivo de la cita");

        //Select Pacientes
        Object[] optionsPatients = Utils.listToArray(PatientsController.instaceModel().findAll());


        Patients PatientSelected = (Patients) JOptionPane.showInputDialog(
                null,
                "Seleccione el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPatients,
                optionsPatients[0]
        );

        //Select Medicos
        Object[] optionsMedic = Utils.listToArray(MedicController.instanceModel().findAll());

        Medic MedicSelected = (Medic) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsMedic,
                optionsMedic [0]
        );

        instanceModel().insert(new Appointment(date, time, reason, PatientSelected.getId(), MedicSelected.getId(),MedicSelected,PatientSelected));
    }

    public static void getAll(){
    String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE REGISTROS \n";

        for (Object temp: list){
            Appointment objAppointment = (Appointment) temp;
            listString += objAppointment.toString() + "\n";
        }
        return listString;
    }

    public static AppointmentModel instanceModel(){
        return new AppointmentModel();
    }
}
