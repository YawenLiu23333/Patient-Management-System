package project1;
import java.time.*;

public class Patient {

    private String patientName;
    private String patientID;
    private String address;
    private int height;
    private double weight;
    private LocalDate dateOfBirth;
    private LocalDate dateOfInitialVisit;
    private LocalDate dateOfLastVisit;

    public Patient() {

    }

    public void setName(String name) {
        this.patientName = name;
    }

    public void setID(String ID) {
        this.patientID = ID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDOB(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setIniVisit(LocalDate dateOfInitialVisit) {
        this.dateOfInitialVisit = dateOfInitialVisit;
    }

    public void setLastVisit(LocalDate dateOfLastVisit) {
        this.dateOfLastVisit = dateOfLastVisit;
    }

    public String getName() {
        return patientName;
    }

    public String getID() {
        return patientID;
    }

    public String getAddress() {
        return address;
    }

    public int getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public LocalDate getDOB() {
        return dateOfBirth;
    }

    public LocalDate getIni() {
        return dateOfInitialVisit;
    }

    public LocalDate getLast() {
        return dateOfLastVisit;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);
        return age.getYears();

    }

    public int get_time_as_patient() {
        LocalDate today = LocalDate.now();
        Period ini = Period.between(dateOfInitialVisit, today);
        return ini.getYears();

    }

    public int get_time_since_last_visit() {
        LocalDate today = LocalDate.now();
        Period last = Period.between(dateOfLastVisit, today);
        return last.getYears();
    }
}
