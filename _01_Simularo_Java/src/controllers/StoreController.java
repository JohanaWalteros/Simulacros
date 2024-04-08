package controllers;

import entity.Store;
import model.StoreModel;

import java.util.List;

public class StoreController {
    //1. Método listar
    public static String getAll (List<Object> list){
        //1. Crear variable donde se guardara la lista
        String listString = "LISTA DE TIENDAS: \n";

        //2. Ciclo for each  para recorrer la lista
        //tem = iterador
        for (Object temp: list){
            //1. Casting: Convertir el objeto tem a tipo Store
            Store objStore = (Store) temp;

            //2. Guardar el resultado en la lista: "Escribe" los datos de especialidad en listString
            listString += objStore.toString()+"\n";
        }
        //3. Retornar la lista
        return listString;
    }

    public static StoreModel instanceModel(){
        return new StoreModel();
    }
}
