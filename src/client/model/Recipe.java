package client.model;

public class Recipe {
    private String doctorTitle;
    private String medicates;
    private String dose;

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getMedicates() {
        return medicates;
    }

    public void setMedicates(String medicates) {
        this.medicates = medicates;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Recipe(String doctorTitle, String medicates, String dose) {

        this.doctorTitle = doctorTitle;
        this.medicates = medicates;
        this.dose = dose;
    }
}
