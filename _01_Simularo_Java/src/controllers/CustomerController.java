package controllers;

import entity.Customer;
import model.CustomerModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CustomerController {
    //1. Método Crear
    public static void insert(){
        //1. Solicitud de datos
        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
        String lastName = JOptionPane.showInputDialog("Ingrese el apellido del cliente: ");
        String email = JOptionPane.showInputDialog("Ingrese el correo electrónico del cliente");

        //3. Usar la instancia de CustomerModel: recibe como parámetros los datos solicitados
        instanceModel().insert(new Customer(name, lastName, email));
    }

    public static void getAll(){
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
            //1. Casting: Convertir el objeto tem a tipo Customer
            Customer objCustomer = (Customer) temp;

            //2. Guardar el resultado en la lista: "Escribe" los datos de especialidad en listString
            listString+= objCustomer.toString() + "\n";
        }
        //3. Retornar la lista
        return listString;
    }

    public static void delete(){
        //1. Llamar la lista creada
        Object[] options = Utils.listToArray(instanceModel().findAll());

        //2. Crear el mensaje y castearlo
        Customer objSelected = (Customer) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        //3. Eliminar la especialidad
        instanceModel().delete(objSelected);

    }

    public static  void update() {
        //1. Llamar la lista creada utils y el método listar todos
        Object[] options = Utils.listToArray(instanceModel().findAll());

        //2. Crear el mensaje y castearlo
        Customer objCustomer = (Customer) JOptionPane.showInputDialog(
                null,
                "Selecciona el cliente que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        //3. Solicitar los nuevos datos
        objCustomer.setName(JOptionPane.showInputDialog(null, "Ingrese el nombre: ", objCustomer.getName()));
        objCustomer.setLastName(JOptionPane.showInputDialog(null, "Ingrese el apellido: ", objCustomer.getLastName()));
        objCustomer.setEmail(JOptionPane.showInputDialog(null, "Ingrese el correo: ", objCustomer.getEmail()));

        //4. Llamar al método actualizar
        instanceModel().update(objCustomer);
    }

    public static CustomerModel instanceModel(){
        return new CustomerModel();
    }
}
