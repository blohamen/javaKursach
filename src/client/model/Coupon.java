package client.model;

public class Coupon {
    private String doctorTitle;
    private String date;
    private String time;

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
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

    public Coupon(String doctorTitle, String date, String time) {

        this.doctorTitle = doctorTitle;
        this.date = date;
        this.time = time;
    }
}
