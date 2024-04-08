package entity;

public class Product {
    //1. Atributos
    private int id;
    private String name;
    private double price;

    //2. LLaves foraneas
    private int idStore;

    //3. Objeto tiene la info de la llave foranea
    private Store objStore;

    //4. Constructor

    public Product() {
    }

    public Product(String name, double price, int idStore, Store objStore) {
        this.name = name;
        this.price = price;
        this.idStore = idStore;
        this.objStore = objStore;
    }

    //5. Set y get
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public Store getObjStore() {
        return objStore;
    }

    public void setObjStore(Store objStore) {
        this.objStore = objStore;
    }

    //6. To string


    @Override
    public String toString() {
        return "PRODUCTO" +"\n" +
                " id: " + id +"\n" +
                " Nombre: " + name + "\n" +
                " Precio: " + price;

    }
}
