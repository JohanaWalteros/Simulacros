package entity;

public class Appointment {
    private  int id;
    private String date;
    private String time;

    private String reason;

    //Llaves foraneas
    private int idPatients;
    private int idMedic;

    //Crear la información de médico y paciente
    private Medic objMedic;

    private Patients objPatient;

    public Appointment() {
    }

    public Appointment(String date, String time, String reason, int idPatients, int idMedic, Medic objMedic, Patients objPatient) {
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.idPatients = idPatients;
        this.idMedic = idMedic;
        this.objMedic = objMedic;
        this.objPatient = objPatient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIdPatients() {
        return idPatients;
    }

    public void setIdPatients(int idPatients) {
        this.idPatients = idPatients;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public Medic getObjMedic() {
        return objMedic;
    }

    public void setObjMedic(Medic objMedic) {
        this.objMedic = objMedic;
    }

    public Patients getObjPatient() {
        return objPatient;
    }

    public void setObjPatient(Patients objPatient) {
        this.objPatient = objPatient;
    }

    @Override
    public String toString() {
        return "CITA" +"\n"+
                "- id:" + id +"\n"+
                "- Fecha:" + date + '\n' +
                "- Hora:" + time + '\n' +
                "- Motivo:" + reason + '\n' +
                "- Medico:" + objMedic.getName()+objPatient.getLastName() +'\n'+
                "- Paciente" + objPatient.getName()+objPatient.getLastName()
                ;
    }
}
