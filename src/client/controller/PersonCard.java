package  client.controller;

import client.main.WindowManager;
import client.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.database.DatabaseHandler;

import java.io.IOException;
import java.sql.SQLException;

public class PersonCard {
    private ObservableList<PatientDiagnosies> patientDiagnosiesObservableList = FXCollections.observableArrayList();

    private Patient patient;
    @FXML
    private Label numCardLabel;

    @FXML
    private Label surNameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label thirdNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label fullAgeLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private TableView<PatientDiagnosies> diseaseTable;

    @FXML
    private TableColumn<PatientDiagnosies, String> diseaseColumn;

    @FXML
    void onAnalysisBtnPress(ActionEvent event) {
        try {
            showFormWindow(event, "../view/analysisForm.fxml", "Добавить анализ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCouponBtnPress(ActionEvent event) {
        try {
            showCouponForm(event, "../view/couponForm.fxml", "Добавить талон");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSickDayBtnPress(ActionEvent event) {
        try {
            showFormWindow(event, "../view/sickDayForm.fxml", "Добавить больничный");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRecipeBtnPress(ActionEvent event) {
        try {
            showFormWindow(event, "../view/recipeForm.fxml", "Добавить рецепт");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void onEditBtnPress(ActionEvent event) {
        try {
            showEditForm(event, this.patient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void onShowDiseaseBtnPress(ActionEvent event) {
        PatientDiagnosies patientDiagnosies = diseaseTable.getSelectionModel().getSelectedItem();
        try {
            if (patientDiagnosies != null) {
                showDiagnosiesDetails(event, patientDiagnosies);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Вы не выбрали диагноз!");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onShowCouponBtnPress(ActionEvent event) {
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            showCouponView(event,"../view/showCouponsPacient.fxml", "Талоны", databaseHandler.getCoupons(patient.getNumCard()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onShowRecipeBtnPress(ActionEvent event) {
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            showRecipeView(event,"../view/showRecipesPacient.fxml", "Рецепты", databaseHandler.getRecipes(patient.getNumCard()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onShowAnalysisBtnPress(ActionEvent event) {
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            showTestsView(event,"../view/showTestsPacient.fxml", "Больничные", databaseHandler.getAnalysies(patient.getNumCard()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onShowSickDayBtnPress(ActionEvent event) {
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            showSickDaysView(event,"../view/showSickDaysPacient.fxml", "Больничные", databaseHandler.getSickDays(patient.getNumCard()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void initialize() {
        diseaseColumn.setCellValueFactory(new PropertyValueFactory<PatientDiagnosies, String>("diagnosisName"));
        diseaseTable.setItems(patientDiagnosiesObservableList);
    }

    void initData(Patient patient) {
        numCardLabel.setText(patient.getNumCard());
        firstNameLabel.setText(patient.getFirstName());
        surNameLabel.setText(patient.getSurName());
        thirdNameLabel.setText(patient.getThirdName());
        fullAgeLabel.setText(patient.getFullAge());
        dateLabel.setText(patient.getDate());
        addressLabel.setText(patient.getAddress());
        patientDiagnosiesObservableList.addAll(patient.getPatientDiagnosies());
        this.patient = patient;
    }

    private void showFormWindow(ActionEvent event, String path, String title) throws IOException {
        WindowManager newWindow = new WindowManager(
                450,
                250,
                path,
                title,
                true,
                false,
                false
        );
      newWindow.openNewWindow((Node) event.getSource(), newWindow.getFXMLLoader().load());
    }

    private void showCouponForm(ActionEvent event, String path, String title) throws IOException {
        WindowManager newWindow = new WindowManager(
                450,
                250,
                path,
                title,
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        CouponForm controller = loader.getController();
        controller.initData(patient.getFirstName() + " " + patient.getSurName());
        newWindow.openNewWindow((Node) event.getSource(), root);
    }

    private void showDiagnosiesDetails(ActionEvent event, PatientDiagnosies patientDiagnosies) throws IOException{
        WindowManager newWindow = new WindowManager(
                560,
                450,
                "../view/showAnalysisView.fxml",
                "Анализы",
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        ShowAnalysis controller = loader.getController();
        controller.initData(patientDiagnosies);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }

    private void showEditForm(ActionEvent event, Patient patient) throws IOException{
        WindowManager newWindow = new WindowManager(
                580,
                410,
                "../view/addEditForm.fxml",
                "Редактировать информацию",
                false,
                true,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        AddEditForm controller = loader.getController();
        controller.initData(patient);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }

    private void showCouponView(ActionEvent event, String path, String title, Coupon[] data) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                path,
                title,
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        ShowCouponsPacient controller = loader.getController();
        controller.initData(data);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }

    private void showRecipeView(ActionEvent event, String path, String title, Recipe[] data) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                path,
                title,
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        ShowRecipesPacient controller = loader.getController();
        controller.initData(data);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }
    private void showSickDaysView(ActionEvent event, String path, String title, SickDay[] data) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                path,
                title,
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        ShowSickDaysPacient controller = loader.getController();
        controller.initData(data);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }
    private void showTestsView(ActionEvent event, String path, String title, Analysis[] data) throws IOException {
        WindowManager newWindow = new WindowManager(
                600,
                400,
                path,
                title,
                true,
                false,
                false
        );
        FXMLLoader loader = newWindow.getFXMLLoader();
        Parent root = loader.load();
        ShowTestsPacient controller = loader.getController();
        controller.initData(data);
        newWindow.openNewWindow((Node) event.getSource(), root);
    }
    
}
