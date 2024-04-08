package model;

import database.CRUD;
import database.ConfigDB;
import entity.Product;
import entity.Store;
import utils.Utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements CRUD {
    //1. Método crear 
    @Override
    public Object insert(Object obj) {
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir(casting) Object obj a la clase Product
        Product objProduct = (Product) obj;

        //3. Control de errores try-catch
        try {
            //1. Crear la sentencia sql para insertar
            String sql = "INSERT INTO producto (nombre, precio, id_tienda) VALUES (?,?,?);";

            //2. Preparar el Statement, retornar las llaves generadas
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //3. Dar valores a los interrogativos
            objPrepared.setString(1,objProduct.getName());
            objPrepared.setDouble(2,objProduct.getPrice());
            objPrepared.setInt(3,objProduct.getIdStore());

            //4. Ejecutar la sentencia preparada que es una consulta a SQL
            objPrepared.execute();

            //5. Obtener las llaves generadas
            ResultSet objResult = objPrepared.getGeneratedKeys();

            //6. Recorrer los resultados
            while (objResult.next()){
                objProduct.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"El registro fue añadido correctamente 👍");

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        //4. Cerrar conexión
        ConfigDB.closeConnection();
            return objProduct;
    }

    //2. Método Leer todos
    @Override
    public List<Object> findAll(){
        //1. Abrir y cerrar la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear una lista
        List<Object> listProducto = new ArrayList<>();

       try {
           //1. Unir el producto con la información de la tienda
           String sql = "select * FROM producto\n" +
                   "inner join tienda on tienda.id_tienda = producto.id_tienda;";

           //2. Preparar el Statement
           PreparedStatement objPrepared = objConnection.prepareStatement(sql);

           //3. Ejecutar la sentencia preparada que es una consulta a SQL. Trae toda la información de la tabla especialidad
           ResultSet objResult = objPrepared.executeQuery();

           //4. Recorrer los resultados
           while (objResult.next()) {
               Product objProduct = new Product();
               Store objStore = new Store();

               //1. Guardar la informacion que trae el bucle en objProduct
               objProduct.setId(objResult.getInt("producto.id_producto"));
               objProduct.setName(objResult.getString("producto.nombre"));
               objProduct.setPrice(objResult.getDouble("producto.precio"));

               objProduct.setObjStore(objStore);
               listProducto.add(objProduct);
           }

       }catch (SQLException e){
           System.out.println("Error" + e.getMessage());
       }
        ConfigDB.closeConnection();

        return listProducto;
    }

    //Método actualizar
    @Override
    public boolean update(Object obj) {
        //1. Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Casting
        Product objProduct= (Product) obj;

        //3. Crear una variable de tipo boolean, para saber si fue actualizado correctamente
        boolean isUpdated = false;

        try {
            //1. Sentencia SQL
            String sql = "UPDATE producto SET nombre = ?, precio = ?;";

            //2. Preparar el Statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Valor a interrogativos
            objPrepared.setString(1, objProduct.getName());
            objPrepared.setDouble(2, objProduct.getPrice());

            //5. Ejecutar la sentencia preparada
            int totalAffectedRows = objPrepared.executeUpdate();

            //6. Condicional de total filas afectadas
            if(totalAffectedRows > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
            }

            //3. Control SQL
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Product objProducto = (Product) obj;

        //1. rear una variable de tipo boolean, para saber si fue eliminado correctamente
        boolean isDelete = false;

        try {
            //1. Sentencia sql
            String sql ="DELETE FROM Producto WHERE id_producto = ?;";

            //2. PreparedStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //3. Valor a interrogativo
            objPrepare.setInt(1, objProducto.getId());

            //4. Ejecutar la sentencia preparada: executeUpdate(), devuelve el número de filas afectadas
            int totalAffectedRows = objPrepare.executeUpdate();

            //6. Condicional de total filas afectadas
            if(totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }
}
