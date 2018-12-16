package client.controller;

import client.main.Service;
import client.main.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AnalysisForm {

    @FXML
    private TextField typeAnalysisInput;

    @FXML
    private TextField dateInput;

    @FXML
    private TextField timeInput;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onOkBtnPress(ActionEvent event) {
        if(Service.checkValidation(typeAnalysisInput,dateInput,timeInput)) {
            // request to back
        }
    }

    @FXML
    void initialize() {

    }
}
