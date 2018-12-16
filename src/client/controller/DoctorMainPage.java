package client.controller;

import client.main.WindowManager;
import client.model.DoctorRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import server.database.DatabaseHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorMainPage {
    @FXML
    private Label doctorNameLabel;

    private void showListRecords(ActionEvent event, DoctorRecord[] records) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                "../view/showList.fxml",
                "Список записей",
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        ShowListRecords controller = loader.getController();
        controller.initData(records);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }

    private void showSearchForm(ActionEvent event) throws IOException {
        WindowManager newWindow = new WindowManager(
                400,
                300,
                "../view/searchPersonForm.fxml",
                "Поиск пациента",
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        newWindow.openNewWindow((Node) event.getSource(), root);
    }


    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onShowListBtnPress(ActionEvent event) {
        // request for get list of records
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ArrayList<DoctorRecord> records = databaseHandler.getAllRecords(doctorNameLabel.getText());
            showListRecords(event,  records.toArray(new DoctorRecord[records.size()]) );
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onShowPersonCardBtnPress(ActionEvent event) {
        try {
            showSearchForm(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    }

    void initData(String fio) {
        doctorNameLabel.setText(fio);
    }
}
