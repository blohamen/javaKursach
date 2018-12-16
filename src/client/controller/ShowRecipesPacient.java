package client.controller;

import client.main.WindowManager;
import client.model.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowRecipesPacient {

    @FXML
    private TableView<Recipe> tableView;

    @FXML
    private TableColumn<Recipe, String> fioDoctorColumn;

    @FXML
    private TableColumn<Recipe, String> medicatesColumn;

    @FXML
    private TableColumn<Recipe, String> doseColumn;

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void initialize() {
        fioDoctorColumn.setCellValueFactory(new PropertyValueFactory<Recipe, String>("doctorTitle"));
        medicatesColumn.setCellValueFactory(new PropertyValueFactory<Recipe, String>("medicates"));
        doseColumn.setCellValueFactory(new PropertyValueFactory<Recipe, String>("dose"));
        tableView.setItems(list);
    }
    private ObservableList<Recipe> list = FXCollections.observableArrayList();
    void initData(Recipe[] data) {
        list.addAll(data);
    }
}
