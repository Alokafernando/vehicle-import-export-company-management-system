package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ExportCompaniesController {

    @FXML
    private Button btnDeleteExport;

    @FXML
    private Button btnExportCompanyRepo;

    @FXML
    private Button btnExportVehicleRepo;

    @FXML
    private Button btnSaveExport;

    @FXML
    private Button btnUpdateExport;

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
    private TableView<?> tblExport;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    @FXML
    void deleteExportCompany(ActionEvent event) {

    }

    @FXML
    void generateExportComapanyDetailsReport(ActionEvent event) {

    }

    @FXML
    void generateExportVehicleReport(ActionEvent event) {

    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void saveExportCompany(ActionEvent event) {

    }

    @FXML
    void updateExportCompany(ActionEvent event) {

    }

}
