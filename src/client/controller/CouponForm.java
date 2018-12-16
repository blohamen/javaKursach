package client.controller;

import client.main.Service;
import client.main.WindowManager;
import client.model.DoctorRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import server.database.DatabaseHandler;

public class CouponForm {
    private String fio;

    @FXML
    private TextField timeInput;

    @FXML
    private TextField dateInput;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onOkBtnPress(ActionEvent event) {
        if(Service.checkValidation(timeInput, dateInput)) {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.addCoupon(new DoctorRecord(
                    fio,
                    dateInput.getText(),
                    timeInput.getText(),
                    client.main.Constants.doctorFio
            ));

            WindowManager.closeWindow(event);
        }
    }

    @FXML
    void initialize() {

    }

    void initData(String name) {
        fio = name;
    }
}
