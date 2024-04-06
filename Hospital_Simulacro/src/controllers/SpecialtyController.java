package controllers;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

//El controlador usa el modelo
public class SpecialtyController {

    //1. Solicitud de datos
    public static void insert(){
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad: ");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la especialidad: ");

        //3. Usar la instancia de SpecialtyModel: recibe como parámetros los datos solicitados
        instanceModel().insert(new Specialty(name,descripcion));

        //4. LLAMAR EL MÉTODO EN EL MAIN
    }

    //3. Mostrar toda la lista
    public static void getAll(){
        //1. crear la variable que trae la lista del model
        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }

    //4. Sobre escritura de getAll: El método se llama igual, pero realiza acciones diferentes
    /*Se realiza para guardar la lista en una variable tipo String, para luego imprimirla en un JOptionPane*/
    public static String getAll(List<Object> list){
        //1. Crear variable donde se guardara la lista
        String listString = "LISTA DE REGISTROS: \n";

        //2. Ciclo for each  para recorrer la lista
        //tem = iterador
        for (Object temp: list){
            //1. Casting: Convertir el objeto tem a tipo Specialty
            Specialty objSpecialty = (Specialty) temp;

            //2. Guardar el resultado en la lista: "Escribe" los datos de especialidad en listString
            listString+= objSpecialty.toString() + "\n";
        }
        //3. Retornar la lista
        return listString;

        //4. LLAMAR EL MÉTODO EN MAIN
    }

    //2. Método para instanciar (crear un objeto de la clase SpecialtyModel): ya que se va a usar repetidas veces
    public static SpecialtyModel instanceModel(){
        return new SpecialtyModel();
    }


}
