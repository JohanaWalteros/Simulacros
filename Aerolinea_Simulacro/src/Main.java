import controllers.AirplaneController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int option = 0, option2 = 0;

        do{
            option = Integer.parseInt(JOptionPane.showInputDialog("""                    
                    1. Gestionar Aviónes
                    2. Gestionar Vuelos
                    3. Gestionar Pasajeros
                    4. Gestionar Reservas
                    5. Salir del menú
                    
                    Ingrese una opción   
                    """));
            switch (option){
                case 1:
                    do{
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Aviónes
                                2. Crear Avión
                                3. Eliminar Avión
                                4. Actualizar Avión
                                5. Salir del Menú
                               
                                Ingrese una opción 
                                """));
                        switch (option2){
                            case 1:
                                AirplaneController.getAll();
                                break;
                            case 2:
                                AirplaneController.insert();
                                break;
                            case 3:
                                AirplaneController.delete();
                                break;
                            case 4:
                                AirplaneController.update();
                                break;
                        }
                    }while(option2!=5);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while(option!=5);
    }
}