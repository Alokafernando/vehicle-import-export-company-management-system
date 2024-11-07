package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ImportCompaniesController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExportCompanyRepo;

    @FXML
    private Button btnExportVehicleRepo;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCOmpanyName;

    @FXML
    private TableColumn<?, ?> colCompanyID;

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
    void generateImportComapanyDetailsReport(ActionEvent event) {

    }

    @FXML
    void generateImportVehicleReport(ActionEvent event) {

    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void saveImportCompany(ActionEvent event) {

    }

    @FXML
    void updateImportCompany(ActionEvent event) {

    }

}
