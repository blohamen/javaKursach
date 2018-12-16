package client.controller;

import client.main.WindowManager;
import client.model.Analysis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowTestsPacient {

    @FXML
    private TableView<Analysis> tableView;

    @FXML
    private TableColumn<Analysis, String> fioDoctorColumn;

    @FXML
    private TableColumn<Analysis, String> typeColumn;

    @FXML
    private TableColumn<Analysis, String> dateColumn;

    @FXML
    private TableColumn<Analysis, String> timeColumn;

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void initialize() {
        fioDoctorColumn.setCellValueFactory(new PropertyValueFactory<Analysis,String>("doctorTitle"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Analysis,String>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Analysis,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Analysis,String>("time"));
        tableView.setItems(list);
    }

    private ObservableList<Analysis> list = FXCollections.observableArrayList();
    void initData(Analysis[] data) {
        list.addAll(data);
    }
}
