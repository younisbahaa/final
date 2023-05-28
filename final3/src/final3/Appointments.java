package final3;

public class Appointments {

    private int id;
    private String appointment_day;
    private String doctor;

    public Appointments(int id, String appointment_day, String doctor) {
        this.id = id;
        this.appointment_day = appointment_day;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointment_day() {
        return appointment_day;
    }

    public void setAppointment_day(String appointment_day) {
        this.appointment_day = appointment_day;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

  

}
