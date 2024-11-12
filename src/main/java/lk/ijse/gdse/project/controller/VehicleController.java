package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class VehicleController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExportVehicleRepo;

    @FXML
    private ToggleGroup btnGroup;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmdExportCompanyID;

    @FXML
    private ComboBox<?> cmdImportCompanyID;

    @FXML
    private ComboBox<?> cmdRevervationID;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colVehicleID;

    @FXML
    private TableColumn<?, ?> colYear;

    @FXML
    private Label lblVehicleID;

    @FXML
    private RadioButton rbExport;

    @FXML
    private RadioButton rbImport;

    @FXML
    private RadioButton rbRepair;

    @FXML
    private RadioButton rbSale;

    @FXML
    private TableView<?> tblVehicle;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtExportDate;

    @FXML
    private TextField txtExportPrice;

    @FXML
    private TextField txtImportDate;

    @FXML
    private TextField txtImportPrice;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtSaleDate;

    @FXML
    private TextField txtYear;

    @FXML
    void deleteVehicle(ActionEvent event) {

    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void generateVehicleReport(ActionEvent event) {

    }

    @FXML
    void saveVehicle(ActionEvent event) {

    }

    @FXML
    void updateVehicle(ActionEvent event) {

    }

}
