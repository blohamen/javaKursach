package client.controller;

import client.main.WindowManager;
import client.model.SickDay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowSickDaysPacient {

    @FXML
    private TableView<SickDay> tableView;

    @FXML
    private TableColumn<SickDay, String> fioDoctorColumn;

    @FXML
    private TableColumn<SickDay, String> dateStartColumn;

    @FXML
    private TableColumn<SickDay, String>  dateEndColumn;

    @FXML
    private TableColumn<SickDay, String>  diagnosiesColumn;

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    private ObservableList<SickDay> list = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        fioDoctorColumn.setCellValueFactory(new PropertyValueFactory<SickDay, String>("doctorTitle"));
        dateStartColumn.setCellValueFactory(new PropertyValueFactory<SickDay, String>("startDate"));
        dateEndColumn.setCellValueFactory(new PropertyValueFactory<SickDay, String>("endDate"));
        diagnosiesColumn.setCellValueFactory(new PropertyValueFactory<SickDay, String>("diagnosies"));
        tableView.setItems(list);
    }

    void initData(SickDay[] data) {
        list.addAll(data);
    }
}
