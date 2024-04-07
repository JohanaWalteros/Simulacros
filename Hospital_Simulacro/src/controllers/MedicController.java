package controllers;

import entity.Medic;
import entity.Specialty;
import model.MedicModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class MedicController {
    public static void insert(){

        //1. Solicitar los datos
        String name = JOptionPane.showInputDialog("Ingrese el nombre del medico");
        String lastName = JOptionPane.showInputDialog("Ingrese los apellidos del medico");

        //2. listar la especialidad
        /*Se usa la instancia del modelo specialty */
        Object[] optionsSpecialties= Utils.listToArray(SpecialtyController.instanceModel().findAll());

        //3. Crear el mensaje y castearlo
        Specialty objSpecialty = (Specialty) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsSpecialties,
                optionsSpecialties[0]
        );

        //5. Ingresar al constructor los nuevos datos enviados por el usuario
        instanceModel().insert(new Medic(name, lastName, objSpecialty.getId(), objSpecialty));

        //6. AGREGARLO AL MAIN EN LA OPCIÓN DE GESTIONAR MEDICOS
    }

    public static void getAll(){
        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }
    public static String getAll(List<Object> list){

        String listString = "LISTA DE REGISTROS: \n";


        for (Object temp: list){
           Medic objMedic = (Medic) temp;

           listString+= objMedic.toString() + "\n";
        }

        return listString;
    }

    public static void delete (){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Medic objMedicSelected = (Medic) JOptionPane.showInputDialog(
                null,
                "Selecciona el medico a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanceModel().delete(objMedicSelected);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Medic objMedicSelected = (Medic) JOptionPane.showInputDialog(
                null,
                "Selecciona a un medico para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String name = JOptionPane.showInputDialog("Ingrese el nombre del medico",objMedicSelected.getName());
        String lastName = JOptionPane.showInputDialog("Ingrese los apellidos del medico", objMedicSelected.getLastName());

        //Listar las especialidades
        Object[] optionsSpecialties= Utils.listToArray(SpecialtyController.instanceModel().findAll());

        //Mostrar en un select
        Specialty objSpecialty = (Specialty) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsSpecialties,
                optionsSpecialties[0]
        );

        instanceModel().update(new Medic(name, lastName, objSpecialty.getId(),objSpecialty));
    }













    //4. Instanciar MedicModel
    public static MedicModel instanceModel(){
        return new MedicModel();
    }

}

