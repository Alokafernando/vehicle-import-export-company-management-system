package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class DriverController {

    @FXML
    private Button btnDriverDetails;

    @FXML
    private Button btnSaveDriver;

    @FXML
    private Button btndeleteDriver;

    @FXML
    private Button btnupdateDriver;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDiverID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblDiverID;

    @FXML
    private HBox lblTransporstID;

    @FXML
    private HBox lblTransporstID1;

    @FXML
    private HBox lblTransporstID11;

    @FXML
    private TableView<?> tblDriver;

    @FXML
    private TextField txtDriverContact;

    @FXML
    private TextField txtDriverName;

    @FXML
    void deleteDriver(ActionEvent event) {

    }

    @FXML
    void generateDriverdetailRepo(ActionEvent event) {

    }

    @FXML
    void saveDriver(ActionEvent event) {

    }

    @FXML
    void updateDriver(ActionEvent event) {

    }

}
