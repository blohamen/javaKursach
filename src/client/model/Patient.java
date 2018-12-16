package client.model;

public class Patient {
    private String numCard;
    private String firstName;
    private String surName;
    private String thirdName;
    private String date;
    private String fullAge;
    private String address;
    private PatientDiagnosies[] patientDiagnosies;

    public Patient(String numCard, String firstName, String surName, String thirdName, String date, String fullAge, String address, PatientDiagnosies[] patientDiagnosies) {
        this.numCard = numCard;
        this.firstName = firstName;
        this.surName = surName;
        this.thirdName = thirdName;
        this.date = date;
        this.fullAge = fullAge;
        this.address = address;
        this.patientDiagnosies = patientDiagnosies;
    }

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public PatientDiagnosies[] getPatientDiagnosies() {
        return patientDiagnosies;
    }

    public void setPatientDiagnosies(PatientDiagnosies[] patientDiagnosies) {
        this.patientDiagnosies = patientDiagnosies;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFullAge() {
        return fullAge;
    }

    public void setFullAge(String fullAge) {
        this.fullAge = fullAge;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
