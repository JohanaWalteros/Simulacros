import controllers.CustomerController;
import controllers.ProductController;
import database.ConfigDB;
import entity.Store;
import model.CustomerModel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //1. Variables de opciones: almacena las opciones seleccionadas por el usuario
        int option = 0, option2 = 0;

        //2. Do-While
        do {
            //3. Menú principal
            //ParseInt: convierte la entrada en número entero
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Gestionar Producto
                    2. Gestionar Cliente
                    3. Gestionar Compra
                    4. Salir del menú
                                       
                    Ingrese una opción   
                    """));
            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Producto
                                2. Crear Producto
                                3. Eliminar Producto
                                4. Actualizar Producto
                                5. Salir del Menú                                                           
                                
                                Ingrese una opción                      
                                """));
                        switch (option2){
                            case 1:
                                ProductController.getAll();
                                break;
                            case 2:
                                ProductController.insert();
                                break;
                            case 3:
                                ProductController.delete();
                                break;
                            case 4:
                                ProductController.update();
                                break;
                        }
                    }while(option2 != 5);
                    break;
                case 2:
                    do{
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Producto
                                2. Crear Producto
                                3. Eliminar Producto
                                4. Actualizar Producto
                                5. Salir del Menú                                                           
                                
                                Ingrese una opción                      
                                """));
                        switch (option2){
                            case 1:
                                CustomerController.getAll();
                                break;
                            case 2:
                                CustomerController.insert();
                                break;
                            case 3:
                                CustomerController.delete();
                                break;
                            case 4:
                                CustomerController.update();
                                break;
                        }
                    }while(option2 !=5);
                    break;
                case 3:
                    do{
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Compra
                                2. Crear Compra
                                3. Eliminar Compra
                                4. Actualizar Compra
                                5. Salir del Menú                                                         
                                
                                Ingrese una opción                      
                                """));
                        switch (option2){
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;

                        }

                    }while (option2 != 5);
                    break;

            }
        }while (option != 4);




    }
}