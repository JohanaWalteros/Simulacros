package controllers;

import entity.Patients;
import entity.Specialty;
import model.PatientsModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PatientsController {
    public static void insert() {
        String name = JOptionPane.showInputDialog("Ingrese el nombre del paciente: ");
        String lastName = JOptionPane.showInputDialog("Ingrese los apellidos del paciente: ");
        String dateBirth = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente (YYY-MM-DD): ");
        String document = JOptionPane.showInputDialog("Ingrese el documento del paciente: ");

        instaceModel().insert(new Patients(name, lastName, dateBirth, document));
    }

    public static void getAll(){
        String list = getAll(instaceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE REGISTROS \n";

        for (Object temp: list){
            Patients objPatients = (Patients) temp;
            listString += objPatients.toString() + "\n";
        }
    return listString;
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instaceModel().findAll());

        Patients objPatientSelected = (Patients)JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instaceModel().delete(objPatientSelected);
    }

    public static  void update(){
        Object[] options = Utils.listToArray(instaceModel().findAll());

        Patients objPatientSelected = (Patients) JOptionPane.showInputDialog(
                null,
                "Selecciona un paciente para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objPatientSelected.setName(JOptionPane.showInputDialog(null, "Ingrese el nombre del paciente: ", objPatientSelected.getName()));
        objPatientSelected.setLastName(JOptionPane.showInputDialog(null, "Ingrese el apellido del paciente ", objPatientSelected.getLastName()));
        objPatientSelected.setDateBirth(JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del paciente (YYY-MM-DD)", objPatientSelected.getDateBirth()));
        objPatientSelected.setDocument(JOptionPane.showInputDialog(null, "Ingrese el número de documento del paciente", objPatientSelected.getDocument()));

        instaceModel().update(objPatientSelected);
    }













    public static PatientsModel instaceModel(){
        return new PatientsModel();
    }
}
