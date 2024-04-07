package entity;

public class Medic {

    //1. Atributos
    private int id;
    private String name;
    private String lastName;

    //Llave foranea
    private int idSpecialty;

    //2. Inyección de independencias
    /*Trae toda la información de la llave foranea, guarda una clase de tipo objeto, dentro de otra clase*/
    private Specialty objSpecialty;

    //3. Constructores
    public Medic() {
    }

    //4. Agregar al constructor objSpecialty
    public Medic(String name, String lastName, int idSpecialty, Specialty objSpecialty) {
        this.name = name;
        this.lastName = lastName;
        this.idSpecialty = idSpecialty;
        this.objSpecialty = objSpecialty;
    }

    //5. Set and get
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

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    //6. Set y get de objSpecualty
    public Specialty getObjSpecialty() {
        return objSpecialty;
    }

    public void setObjSpecialty(Specialty objSpecialty) {
        this.objSpecialty = objSpecialty;
    }

    //4. To string objSpecialty
    @Override
    public String toString() {
        return "Medico" +
                "- Nombre: " + name + "\n"+
                "- Apellido: " + lastName + "\n"+
                "- Especialidad: " + this.objSpecialty.getName();
        }

        //5. CREAR EL MODELO
}
