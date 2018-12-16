package client.controller;

import client.main.WindowManager;
import client.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.database.DatabaseHandler;

import java.util.Optional;

public class AdminPage {
    private ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> fioDoctorColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    void onCancelBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onDeleteBtnPress(ActionEvent event) {
        User user = tableView.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Вы не выбрали врача!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Вы уверены что хотите удалить?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.removeDoctor(user.getTitle());
                users.remove(user);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Пользователь удален");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void onEditBtnPress(ActionEvent event) {

    }

    @FXML
    void initialize() {
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String >("login"));
        fioDoctorColumn.setCellValueFactory(new PropertyValueFactory<User, String >("title"));
        tableView.setItems(this.users);
    }

    void initData(User[] users) {
        this.users.addAll(users);
    }
}
