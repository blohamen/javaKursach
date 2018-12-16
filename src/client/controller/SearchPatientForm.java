package client.controller;

import client.main.Service;
import client.main.WindowManager;
import client.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import server.database.DatabaseHandler;

import java.io.IOException;
import java.sql.SQLException;

public class SearchPatientForm {

    private void showPersonCard(ActionEvent event, Patient patient) throws IOException {
        WindowManager newWindow = new WindowManager(
                880,
                400,
                "../view/personCard.fxml",
                "Электронная карта",
                true,
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

    @FXML
    private TextField fioInput;

    @FXML
    private TextField addressInput;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onSearchBtnPress(ActionEvent event) {
        if(Service.checkValidation(fioInput, addressInput)) {
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DatabaseHandler handler = new DatabaseHandler();
                String[] fio = fioInput.getText().split(" ");
                if (fio.length != 3) {
                    alert.setHeaderText("Вы неправильно ввели ФИО пациента");
                    alert.showAndWait();
                    return;
                }
                Patient patient = handler.getPatient(fio[1], fio[0], fio[2], addressInput.getText());
                if(patient == null) {
                   alert.setHeaderText("Пациент не найден");
                   alert.showAndWait();
                   return;
               }
                showPersonCard(event, patient);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
