package entity;

public class Store {
    //1. Atributos
    private int id;
    private String name;
    private  String location;

    //2. Constructores

    public Store() {
    }

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    //3. Set y get

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //To string

    @Override
    public String toString() {
        return "TIENDA" + '\n' +
                "id: " + id +
                "Nombre: " + name + '\n' +
                "Ubicación" + location;
    }

    //Tienda tiene los datos quemados
}
