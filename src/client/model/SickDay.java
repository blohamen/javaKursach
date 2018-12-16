package client.model;

public class SickDay {
    private String doctorTitle;
    private String startDate;
    private String endDate;
    private String diagnosies;

    public SickDay(String doctorTitle, String startDate, String endDate, String diagnosies) {
        this.doctorTitle = doctorTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnosies = diagnosies;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDiagnosies() {
        return diagnosies;
    }

    public void setDiagnosies(String diagnosies) {
        this.diagnosies = diagnosies;
    }
}
