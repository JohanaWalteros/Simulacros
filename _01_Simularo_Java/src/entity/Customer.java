package entity;

public class Customer {
    //1. Atributos
    private  int id;
    private String name;
    private String lastName;
    private String email;

    //2. Constructor
    public Customer() {
    }

    public Customer(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //4. To string

    @Override
    public String toString() {
        return "CLIENTE" + "\n"+
                "id: " + id +
                "Nombre: " + name + "\n" +
                "Apellido: " + lastName + "\n" +
                "Correo: " + email;
    }
}
