package client.controller;

import client.main.WindowManager;
import client.model.PatientAnalysies;
import client.model.PatientDiagnosies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ShowAnalysis {
    private ObservableList<PatientAnalysies> patientAnalysiesObservableList = FXCollections.observableArrayList();
    @FXML
    private Label diseaseLabel;

    @FXML
    private TextFlow epicrisisTextFlow;

    @FXML
    private TableView<PatientAnalysies> table;

    @FXML
    private TableColumn<PatientAnalysies, String> analysisColumn;

    @FXML
    private TableColumn<PatientAnalysies, String> resultsColumn;

    @FXML
    private Label drugsLabel;

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void initialize() {
        analysisColumn.setCellValueFactory(new PropertyValueFactory<PatientAnalysies, String>("name"));
        resultsColumn.setCellValueFactory(new PropertyValueFactory<PatientAnalysies, String>("result"));
        table.setItems(patientAnalysiesObservableList);
    }

    void initData(PatientDiagnosies patientDiagnosies) {
        diseaseLabel.setText(patientDiagnosies.getDiagnosisName());
        epicrisisTextFlow.getChildren().add(new Text(patientDiagnosies.getDescription()));
        drugsLabel.setText(patientDiagnosies.getMedicates());
        patientAnalysiesObservableList.addAll(patientDiagnosies.getPatientAnalysies());
    }
}
