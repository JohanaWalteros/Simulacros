package controllers;

import com.mysql.cj.util.Util;
import entity.Airplane;
import model.AirplaneModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AirplaneController {
    public static void insert(){

        String model = JOptionPane.showInputDialog("Ingrese el modelo del avión: ");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del avión: "));

        instanceModel().insert(new Airplane(model,capacity));
    }
    public static void getAll(){
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }
    public static String getAll(List<Object> list){

        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: list){
            Airplane objAirplane = (Airplane) temp;

            listString+= objAirplane.toString() + "\n";
        }
        return listString;
    }
    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Airplane airplaneSelected = (Airplane) JOptionPane.showInputDialog(
                null,
                "Selecciona una avión",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(airplaneSelected);
    }
    public static  void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Airplane AirplaneSelected = (Airplane) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

       AirplaneSelected.setModel(JOptionPane.showInputDialog(null, "Ingrese el modelo: ",AirplaneSelected.getModel()));
        AirplaneSelected.setCapacity(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad", AirplaneSelected.getCapacity())));

        instanceModel().update(AirplaneSelected);
    }
    public static AirplaneModel instanceModel(){
        return new AirplaneModel();
    }
}
