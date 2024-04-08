package database;

import java.util.List;

public interface CRUD {
    //1. Crear = Create
    public Object insert(Object obj);

    //2. Leer todos los existentes = Read or FindAll
    public List<Object> findAll();

    //3. Actualizar = Update
    public boolean update(Object obj);

    //4. Eliminar = Delete
    public boolean delete(Object obj);

}

//5. CREAR EL MODELO
