package client.model;

public class Analysis {
    private String doctorTitle;
    private String type;
    private String date;
    private String time;

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Analysis(String doctorTitle, String type, String date, String time) {

        this.doctorTitle = doctorTitle;
        this.type = type;
        this.date = date;
        this.time = time;
    }
}
