package client.controller;

import client.main.Service;
import client.main.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RecipeForm {


    @FXML
    private TextField medicineInput;

    @FXML
    private TextField dosageInput;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onOkBtnPress(ActionEvent event) {
        if(Service.checkValidation(medicineInput, dosageInput)) {

        }
    }

    @FXML
    void initialize() {

    }
}
