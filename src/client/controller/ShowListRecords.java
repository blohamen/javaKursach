package client.controller;

import client.main.WindowManager;
import client.model.DoctorRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowListRecords {

    private ObservableList<DoctorRecord> listOfRecords = FXCollections.observableArrayList();

    @FXML
    private TableView<DoctorRecord> table;

    @FXML
    private TableColumn<DoctorRecord, String> fioColumn;

    @FXML
    private TableColumn<DoctorRecord, String> dateColumn;

    @FXML
    private TableColumn<DoctorRecord, String> timeColumn;

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void initialize() {
        fioColumn.setCellValueFactory(new PropertyValueFactory<DoctorRecord, String>("fio"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<DoctorRecord, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<DoctorRecord, String>("time"));
        table.setItems(listOfRecords);
    }

    public void initData(DoctorRecord[] records) {
        listOfRecords.addAll(records);
    }
}
