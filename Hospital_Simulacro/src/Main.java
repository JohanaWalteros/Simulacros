import controllers.SpecialtyController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

//Lógica y navegación de usuario
public class Main {
    public static void main(String[] args) {
        //Menú de opciones

        //1. Variables de opciones: almacena las opciones seleccionadas por el usuario
        int option = 0, option2 = 0;

        //2. Ciclo DO While
        do{

            //3. Menú principal
            //ParseInt: convierte la entrada en número entero
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    ===========Bienvenidos a el menú de gestión del hospital===========
                    1. Gestionar Especialidades
                    2. Gestionar Medicos
                    3. Gestionar Pacientes
                    4. Gestionar Citas
                    5. Salir del menú
                    
                    Ingrese una opción   
                    """));

            //5. Crear la estructura de control switch
            switch (option){
                case 1:
                    do {
                        //1. Crear menú
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                ===========Bienvenidos a el menú de especialidad del hospital===========
                                1. Listar Especialidad
                                2. Crear Especialidad
                                3. Eliminar Especialidad
                                4. Actualizar Especialidad
                                
                                Ingrese una opción                      
                                """));

                        //2. Crear la estructura de control switch
                        switch (option2){

                            case 1:
                                SpecialtyController.getAll();
                                break;
                            case 2:
                                SpecialtyController.insert();
                                break;
                        }

                        //3. indicar en que momento se rompe el ciclo
                    }while (option2 != 4);
                    break;
            }

            //4. Indicar en que momento se rompe el ciclo
        }while (option !=5);



    }
}