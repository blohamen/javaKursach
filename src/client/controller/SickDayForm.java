package client.controller;

import client.main.Service;
import client.main.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SickDayForm {


    @FXML
    private TextField dateOpenInput;

    @FXML
    private TextField dateCloseInput;

    @FXML
    private TextField diseaseInput;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onOkBtnPress(ActionEvent event) {
        if (Service.checkValidation(dateCloseInput, dateOpenInput, diseaseInput)) {

        }
    }

    @FXML
    void initialize() {

    }
}
