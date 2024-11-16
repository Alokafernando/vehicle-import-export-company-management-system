package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.TaxModel;
import lk.ijse.gdse.project.Model.VehicleModel;
import lk.ijse.gdse.project.dto.TaxDTO;
import lk.ijse.gdse.project.dto.tm.TaxTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaxController implements Initializable {

    @FXML
    private Button btnDeleteTax;

    @FXML
    private Button btnTaxDetails;

    @FXML
    private Button btnTaxSave;

    @FXML
    private Button btnUpdateTax;

    @FXML
    private ComboBox<String> cmbVehicleID;

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
    private TableView<TaxTM> tblTax;

    @FXML
    private TextField txtExportTax;

    @FXML
    private TextField txtGroundTax;

    @FXML
    private TextField txtImportTax;

    private final TaxModel taxModel = new TaxModel();
    private final VehicleModel vehicleModel = new VehicleModel();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();
        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        cmbVehicleID.getSelectionModel().clearSelection();
        lblTaxID.setText(taxModel.getNextTaxID());
        txtExportTax.setText("");
        txtImportTax.setText("");
        txtGroundTax.setText("");

        btnTaxSave.setDisable(false);
        btnUpdateTax.setDisable(true);
        btnDeleteTax.setDisable(true);

        loadVehicleIds();
        loadTableData();
    }

    private void loadVehicleIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> vehicleIDs = vehicleModel.getAllVehicleIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList(vehicleIDs);
        cmbVehicleID.setItems(observableList);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<TaxDTO> taxDTOS = taxModel.getAllTaxes();
        ObservableList<TaxTM> taxTMS = FXCollections.observableArrayList();

            for (TaxDTO taxDTO : taxDTOS) {
                TaxTM taxTM = new TaxTM(
                        taxDTO.getVehicle_id(),
                        taxDTO.getTax_id(),
                        taxDTO.getImport_tax(),
                        taxDTO.getExport_tax(),
                        taxDTO.getGround_tax()

                );
                taxTMS.add(taxTM);
            }
            tblTax.setItems(taxTMS);
    }

    private void setCellValues() {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colTaxID.setCellValueFactory(new PropertyValueFactory<>("tax_id"));
        colImportTax.setCellValueFactory(new PropertyValueFactory<>("import_tax"));
        colExportTax.setCellValueFactory(new PropertyValueFactory<>("export_tax"));
        colGroundTAx.setCellValueFactory(new PropertyValueFactory<>("ground_tax"));
    }
}
