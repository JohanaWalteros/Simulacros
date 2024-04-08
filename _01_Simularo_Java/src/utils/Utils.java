package utils;

import java.util.List;

public class Utils {
    public static<T> T[] listToArray(List<T> list){
        /*GENÉRICOS:
        <T>: Es un genérico de tipo T(cualquier cosa) y la lista también puede ser de tipo T,
        * T[]: Va a retornar un array de T*/

        //1. Crear un arreglo (object) a partir del tamaño de la lista
        T[] array = (T[])new Object[list.size()];

        //2. Llenar la lista array
        int i= 0;
        for(T iterador: list){
            array[i++]=iterador;
        }

        //2. Retorno
        return array;
    }
}
