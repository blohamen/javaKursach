package client.controller;

import client.main.Service;
import client.main.WindowManager;
import client.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import server.database.DatabaseHandler;

import java.io.IOException;
import java.sql.SQLException;

public class AddEditForm {
    private Patient patient;
    @FXML
    private TextField secondNameInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField patronymicInput;

    @FXML
    private TextField addressInput;

    @FXML
    private TextField birthdayDateInput;

    @FXML
    private TextField ageInput;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        try {
            backToPersonCard(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onOkBtnPress(ActionEvent event) {
        if(Service.checkValidation(
                secondNameInput,
                firstNameInput,
                patronymicInput,
                addressInput,
                birthdayDateInput,
                ageInput
        )) {
           patient.setFirstName(firstNameInput.getText());
           patient.setAddress(addressInput.getText());
           patient.setDate(birthdayDateInput.getText());
           patient.setFullAge(ageInput.getText());
           patient.setThirdName(patronymicInput.getText());
           patient.setSurName(secondNameInput.getText());
            try {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.updateUserInfo(patient);
                backToPersonCard(event);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {

    }
    void initData(Patient patient) {
        this.patient = patient;
        secondNameInput.setText(patient.getSurName());
        firstNameInput.setText(patient.getFirstName());
        patronymicInput.setText(patient.getThirdName());
        addressInput.setText(patient.getAddress());
        birthdayDateInput.setText(patient.getDate());
        ageInput.setText(patient.getFullAge());
    }

    void backToPersonCard(ActionEvent event) throws IOException {
        WindowManager newWindow = new WindowManager(
                880,
                400,
                "../view/personCard.fxml",
                "Электронная карта",
                false,
                true,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        PersonCard controller = loader.getController();
        controller.initData(patient);
        WindowManager.closeWindow(event);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }
}
