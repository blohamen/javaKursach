package client.controller;

import client.main.Connection;
import client.main.Constants;
import client.main.Service;
import client.main.WindowManager;
import client.model.CommandObject;
import client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.main.ServerConfig;

import java.io.IOException;

public class AuthPage extends Connection {

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passInput;


    private void showRegistrationPage(ActionEvent event) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                "../view/RegistrationPage.fxml",
                "Страница регистрации",
                true,
                false,
                false
                );
        newWindow.openNewWindow((Node) event.getSource(), newWindow.getFXMLLoader().load());
    }

    private void showDoctorMainPage(ActionEvent event, String doctorFio) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                "../view/DoctorMainPage.fxml",
                "Главное меню доктора",
                false,
                true,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        DoctorMainPage doctorMainPageController = loader.getController();
        doctorMainPageController.initData(doctorFio);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }

    private void showAdminPage(ActionEvent event, User[] users) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                "../view/adminPage.fxml",
                "Главное меню администратора",
                false,
                true,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        AdminPage controller = loader.getController();
        controller.initData(users);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }



    @FXML
    void onExitBtnPressed(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onLoginBtnPressed(ActionEvent event) {
        if (Service.checkValidation(loginInput, passInput)) {
            try {
                User user = new User(loginInput.getText(), passInput.getText());
                CommandObject<User> userCommandObject = new CommandObject<>(
                        ServerConfig.AUTHORIZATION,
                        user
                );
                try {
                    connect();
                    objectOutputStream.writeObject(userCommandObject);
                    user = (User) objectInputStream.readObject();
                    closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (user == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Пользователь не найден");
                    alert.showAndWait();
                    return;
                }

                Constants.doctorFio = user.getTitle();

                switch (user.getRole()) {
                    case Constants.userRole: {
                        showDoctorMainPage(event, user.getTitle());
                        break;
                    }
                    case Constants.adminRole: {
                       connect();
                       objectOutputStream.writeObject(new CommandObject<User[]>(ServerConfig.GET_ALL_USERS));
                       User[] users = (User[]) objectInputStream.readObject();
                       closeConnection();
                       showAdminPage(event, users);
                       break;
                    }
                }
            }   catch(IOException | ClassNotFoundException e){
                    e.printStackTrace();
                }
        }
    }

    @FXML
    void onRegistrationBtnPressed(ActionEvent event) {
        try {
            showRegistrationPage(event);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    }
}
