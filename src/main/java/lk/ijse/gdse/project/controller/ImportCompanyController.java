package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ImportCompanyController {

    @FXML
    private Button btnDeleteImport;

    @FXML
    private Button btnImportCompanyRepo;

    @FXML
    private Button btnImportVehicleRepo;

    @FXML
    private Button btnSaveImport;

    @FXML
    private Button btnUpdateImport;

    @FXML
    private TableColumn<?, ?> colCompanyID;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCountry;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private Label lblCompanyID;

    @FXML
    private TableView<?> tblImport;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    @FXML
    void deleteImportCompany(ActionEvent event) {

    }

    @FXML
    void generateImportComapnyDetailsReport(ActionEvent event) {

    }

    @FXML
    void generateImportVehicleReport(ActionEvent event) {

    }

    @FXML
    void saveImportCompany(ActionEvent event) {

    }

    @FXML
    void updateImportCompany(ActionEvent event) {

    }

}
