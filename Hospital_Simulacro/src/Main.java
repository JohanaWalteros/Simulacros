import controllers.AppointmentController;
import controllers.MedicController;
import controllers.PatientsController;
import controllers.SpecialtyController;
import database.ConfigDB;
import entity.Medic;

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
                    Bienvenidos a el menú de gestión del hospital
                                      
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
                                5. Salir del Menú
                                                               
                                
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
                            case 3:
                                SpecialtyController.delete();
                                break;
                            case 4:
                                SpecialtyController.update();
                                break;
                        }

                        //3. indicar en que momento se rompe el ciclo
                    }while (option2 != 5);
                    break;

                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Bienvenidos a el menú de medicos del hospital
                                
                                1. Listar Medicos
                                2. Crear Medico
                                3. Eliminar Medico
                                4. Actualizar Medico
                                5. Salir del menú
                                
                                Ingrese una opción                      
                                """));
                        switch (option2){
                            case 1:
                                MedicController.getAll();
                                break;
                            case 2:
                                MedicController.insert();
                                break;
                            case 3:
                                MedicController.delete();
                                break;
                            case 4:
                                MedicController.update();
                                break;
                        }
                    }while (option2 !=5);
                    break;

                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Bienvenidos a el menú de pacientes del hospital
                                
                                1. Listar Pacientes
                                2. Crear Pacientes
                                3. Eliminar Pacientes
                                4. Actualizar Pacientes
                                5. Salir del menú
                                
                                Ingrese una opción                      
                                """));

                        switch (option2){
                            case 1:
                                PatientsController.getAll();
                                break;
                            case 2:
                                PatientsController.insert();
                                break;
                            case 3:
                                PatientsController.delete();
                                break;
                            case 4:
                                PatientsController.update();
                                break;
                        }
                    }while (option2 !=5);
                    break;

                case 4:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Bienvenidos a el menú de citas del hospital
                                
                                1. Listar Citas
                                2. Crear Citas
                                3. Eliminar Citas
                                4. Actualizar Citas
                                5. Salir del menú
                                
                                Ingrese una opción                      
                                """));
                        switch (option2){
                            case 1:
                                AppointmentController.getAll();
                                break;
                            case 2:
                                AppointmentController.insert();
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                        }
                    }while (option2 !=5);
                    break;
            }
            //4. Indicar en que momento se rompe el ciclo
        }while (option !=5);



    }
}