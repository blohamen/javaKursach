package client.controller;

import client.main.WindowManager;
import client.model.Coupon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowCouponsPacient {

    @FXML
    private TableView<Coupon> tableView;

    @FXML
    private TableColumn<Coupon, String> fioDoctorColumn;

    @FXML
    private TableColumn<Coupon, String> dateColumn;

    @FXML
    private TableColumn<Coupon, String> timeColumn;

    @FXML
    void onExitBtnPress(ActionEvent event) {
        WindowManager.closeWindow(event);
    }

    @FXML
    void initialize() {
        fioDoctorColumn.setCellValueFactory(new PropertyValueFactory<Coupon,String>("doctorTitle"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Coupon,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Coupon,String>("time"));
        tableView.setItems(list);
    }
    private ObservableList<Coupon> list = FXCollections.observableArrayList();
    void initData(Coupon[] data) {
        list.addAll(data);
    }
}
