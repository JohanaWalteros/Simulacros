package entity;

public class Airplane {
    private int id;
    private String model;
    private int capacity;

    public Airplane() {
    }

    public Airplane(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Avión" +'\n' +
                "id: " + id + '\n' +
                "Modelo: " + model + '\n' +
                "Capacidad: " + capacity +'\n'
                ;
    }
}
