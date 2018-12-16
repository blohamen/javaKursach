package client.controller;

import client.main.Connection;
import client.main.Service;
import client.main.WindowManager;
import client.model.CommandObject;
import client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.database.DatabaseHandler;
import server.main.ServerConfig;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationPage extends Connection {


    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passInput;

    @FXML
    private TextField fioInput;

    @FXML
    void onCancelButtonPressed(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onRegistrationBtnPressed(ActionEvent event) {
        if (Service.checkValidation(loginInput, passInput, fioInput)) {
            // make request to register new doctor
            CommandObject<User> commandObject = new CommandObject<>(
                    ServerConfig.REGISTRATION,
                    new User(
                            loginInput.getText(),
                            passInput.getText(),
                            0,
                            fioInput.getText()
                    )
            );

            try {
                connect();
                objectOutputStream.writeObject(commandObject);
                User user = ((User) objectInputStream.readObject());
                closeConnection();
                if (user != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Пользователь зарегистрирован");
                    alert.showAndWait();
                    WindowManager.closeWindow(event);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Пользователь уже существует в базе");
                    alert.showAndWait();
                    WindowManager.closeWindow(event);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void initialize() {

    }
}
