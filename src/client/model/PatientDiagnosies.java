package client.model;

public class PatientDiagnosies {
    private String numCard;
    private String diagnosisName;
    private String description;
    private PatientAnalysies[] patientAnalysies;
    private String medicates;

    public PatientDiagnosies(String diagnosisName, String description, String medicates, PatientAnalysies[] patientAnalysies) {
        this.diagnosisName = diagnosisName;
        this.description = description;
        this.patientAnalysies = patientAnalysies;
        this.medicates = medicates;
    }

    public PatientDiagnosies(String numCard, String diagnosisName, String description, PatientAnalysies[] patientAnalysies, String medicates) {
        this.numCard = numCard;
        this.diagnosisName = diagnosisName;

        this.description = description;
        this.patientAnalysies = patientAnalysies;
        this.medicates = medicates;
    }

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PatientAnalysies[] getPatientAnalysies() {
        return patientAnalysies;
    }

    public void setPatientAnalysies(PatientAnalysies[] patientAnalysies) {
        this.patientAnalysies = patientAnalysies;
    }

    public String getMedicates() {
        return medicates;
    }

    public void setMedicates(String medicates) {
        this.medicates = medicates;
    }
}
