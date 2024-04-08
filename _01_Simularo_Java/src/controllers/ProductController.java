package controllers;

import entity.Product;
import entity.Store;
import model.ProductModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ProductController {

    //1. Método crear
    public static void insert(){
        //1. Solicitar datos
        String name = JOptionPane.showInputDialog(null,"Ingrese el nombre del producto: ");
        double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el precio del producto: "));

        //2. Listar la tienda como un select, crear la lista
        /*Se usa la instancia del modelo store */
        Object[]  optionsStore= Utils.listToArray(StoreController.instanceModel().findAll());

        //3. Crear el mensaje y castearlo
        Store objStore = (Store) JOptionPane.showInputDialog(
                null,
                "Selecciona una tienda",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsStore,
                optionsStore[0]
        );

        //4. Ingresar al constructor los nuevos datos enviados por el usuario
        instanceModel().insert(new Product(name, price, objStore.getId(),objStore));

    }

    //2. Método listar
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
            //1. Casting: Convertir el objeto tem a tipoProduct
            Product objProduct = (Product) temp;

            //2. Guardar el resultado en la lista: "Escribe" los datos de especialidad en listString
            listString+= objProduct.toString() + "\n";
        }

        return listString;
    }
    //3. Método Eliminar
    public static void delete (){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Product objProductSelected = (Product) JOptionPane.showInputDialog(
                null,
                "Selecciona el Producto a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanceModel().delete(objProductSelected);
    }

    //4. Método Actualizar
    public static  void update(){
        //1. Llamar la lista creada utils y el método listar todos
        Object[] options = Utils.listToArray(instanceModel().findAll());

        //2. Crear el mensaje y castearlo
        Product objSelected = (Product) JOptionPane.showInputDialog(
                null,
                "Selecciona un producto para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        //3. Solicitar los nuevos datos
        objSelected.setName(JOptionPane.showInputDialog(null, "Ingrese nombre del producto: ", objSelected.getName()));
        objSelected.setPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese precio del producto ", objSelected.getPrice())));

        //4. Llamar al método actualizar
        instanceModel().update(objSelected);
    }

    public static ProductModel instanceModel(){
        return new ProductModel();
    }

}
