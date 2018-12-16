package client.model;

public class DoctorRecord {
    private String fio;
    private String date;
    private String time;
    private String doctorTitle;

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public DoctorRecord(String fio, String date, String time, String doctorTitle) {

        this.fio = fio;
        this.date = date;
        this.time = time;
        this.doctorTitle = doctorTitle;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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
}
