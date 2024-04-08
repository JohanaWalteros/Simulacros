package controllers;

import entity.Specialty;
import model.SpecialtyModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

//El controlador usa el modelo
public class SpecialtyController {

    //1. Método Crear
    public static void insert(){
        //1. Solicitud de datos
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad: ");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la especialidad: ");

        //3. Usar la instancia de SpecialtyModel: recibe como parámetros los datos solicitados
        instanceModel().insert(new Specialty(name,descripcion));

        //4. LLAMAR EL MÉTODO EN EL MAIN
    }

    //3. Método Leer todos los existentes
    public static void getAll(){
        /*Sobre escritura de getAll: El método se llama igual, pero realiza acciones diferentes.
        Se realiza para guardar la lista en una variable tipo String, para luego imprimirla en un JOptionPane*/

        //1. crear la variable que trae la lista del model
        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }

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

    //4. Método Eliminar
    public static void delete(){
        //HABILITAR JOPTION COMO UN SELECT

        //1. Crear package de utils (array que usara el Joption)

        //2. Llamar la lista creada
        Object[] options = Utils.listToArray(instanceModel().findAll());

        //3. Crear el mensaje y castearlo
       Specialty objSelected = (Specialty) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
       );
       //4. Eliminar la especialidad
        instanceModel().delete(objSelected);

        //5. LLAMAR EL MÉTODO EN MAIN
    }

    //5. Método actualizar
    public static  void update(){
        //1. Llamar la lista creada utils y el método listar todos
        Object[] options = Utils.listToArray(instanceModel().findAll());

        //2. Crear el mensaje y castearlo
        Specialty objSelected = (Specialty) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        //3. Solicitar los nuevos datos
        objSelected.setName(JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre: ", objSelected.getName()));
        objSelected.setName(JOptionPane.showInputDialog(null, "Ingrese la nueva descripción ", objSelected.getDescription()));

        //4. Llamar al método actualizar
        instanceModel().update(objSelected);
    }


    //2. Método para instanciar (crear un objeto de la clase SpecialtyModel): ya que se va a usar repetidas veces
    public static SpecialtyModel instanceModel(){
        return new SpecialtyModel();
    }



//Insetr into nombre tabla (columna) valores ()
}
