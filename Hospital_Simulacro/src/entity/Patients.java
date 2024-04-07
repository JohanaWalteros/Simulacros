package entity;

import java.util.Date;

public class Patients {
    private int id;
    private String name;
    private String lastName;
    private String dateBirth;
    private String document;

    public Patients() {
    }

    public Patients(String name, String lastName, String dateBirth, String document) {
        this.name = name;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.document = document;
    }

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

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "PACIENTES: " + '\n' +
                "- id: " + id +
                "- Nombre: " + name + '\n' +
                "- Apellidos: " + lastName + '\n' +
                "- Fecha Nacimiento: " + dateBirth + '\n' +
                "- Documento: " + document;
    }

}
