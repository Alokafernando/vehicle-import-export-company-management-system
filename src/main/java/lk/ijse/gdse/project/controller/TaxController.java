package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TaxController {

    @FXML
    private Button btnDeleteTax;

    @FXML
    private Button btnTaxDetails;

    @FXML
    private Button btnTaxSave;

    @FXML
    private Button btnUpdateTax;

    @FXML
    private ComboBox<?> cmbVehicleID;

    @FXML
    private TableColumn<?, ?> colExportTax;

    @FXML
    private TableColumn<?, ?> colGroundTAx;

    @FXML
    private TableColumn<?, ?> colImportTax;

    @FXML
    private TableColumn<?, ?> colTaxID;

    @FXML
    private TableColumn<?, ?> colVehicleID;

    @FXML
    private Label lblTaxID;

    @FXML
    private TableView<?> tblTax;

    @FXML
    private TextField txtExportTax;

    @FXML
    private TextField txtGroundTax;

    @FXML
    private TextField txtImportTax;

    @FXML
    void deleteTax(ActionEvent event) {

    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void generateTaxDetailsRepo(ActionEvent event) {

    }

    @FXML
    void saveTax(ActionEvent event) {

    }

    @FXML
    void updateTax(ActionEvent event) {

    }

}
