package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.TaxModel;
import lk.ijse.gdse.project.Model.VehicleModel;
import lk.ijse.gdse.project.dto.TaxDTO;
import lk.ijse.gdse.project.dto.tm.TaxTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private TableColumn<TaxTM, Double> colExportTax;

    @FXML
    private TableColumn<TaxTM, Double> colGroundTAx;

    @FXML
    private TableColumn<TaxTM, Double> colImportTax;

    @FXML
    private TableColumn<TaxTM, String> colTaxID;

    @FXML
    private TableColumn<TaxTM, String> colVehicleID;

    @FXML
    private Label lblTaxID;

    @FXML
    private TableView<TaxTM> tblTax;

    @FXML
    private TextField txtExportTax;

    @FXML
    private TextField txtGroundTax;

    private final TaxModel taxModel = new TaxModel();
    private final VehicleModel vehicleModel = new VehicleModel();

    @FXML
    private TextField txtImportTax;

    @FXML
    void deleteTax(ActionEvent event) throws SQLException, ClassNotFoundException {
        String taxId = lblTaxID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = taxModel.deleteTax(taxId);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Tax deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete tax").show();
            }
        }

    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void generateTaxDetailsRepo(ActionEvent event) {

    }

    @FXML
    void onClickedtable(MouseEvent event) throws SQLException, ClassNotFoundException {
        TaxTM taxTM = tblTax.getSelectionModel().getSelectedItem();
        if (taxTM != null) {
            cmbVehicleID.getItems().clear();
            List<String> reservationIDS = vehicleModel.getAllVehicleIDs();
            if(reservationIDS != null && !reservationIDS.isEmpty()) {
                cmbVehicleID.getItems().addAll(reservationIDS);
                cmbVehicleID.setValue(taxTM.getVehicle_id());
            }
        }

        lblTaxID.setText(taxTM.getTax_id());
        txtImportTax.setText(String.valueOf(taxTM.getImport_tax()));
        txtExportTax.setText(String.valueOf(taxTM.getExport_tax()));
        txtGroundTax.setText(String.valueOf(taxTM.getGround_tax()));

        btnTaxSave.setDisable(true);
        btnDeleteTax.setDisable(false);
        btnUpdateTax.setDisable(false);

    }

    @FXML
    void saveTax(ActionEvent event) throws SQLException, ClassNotFoundException {
        String vehicleId = cmbVehicleID.getValue();
        String taxId = lblTaxID.getText();

        if (vehicleId == null || vehicleId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a vehicle ID.").show();
            return;
        }

        String importTax = txtImportTax.getText();
        String exportTax = txtExportTax.getText();
        String groundTax = txtGroundTax.getText();

        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";

        boolean isValidImportTax = importTax.matches(doubleValuesPattern);
        boolean isValidExportTax = exportTax.matches(doubleValuesPattern);
        boolean isValidGroundTax = groundTax.matches(doubleValuesPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidImportTax){
            txtImportTax.setStyle(errorStyle);
        }else{
            txtImportTax.setStyle(style);
        }
        if(!isValidExportTax){
            txtExportTax.setStyle(errorStyle);
        }else{
            txtExportTax.setStyle(style);
        }
        if(!isValidGroundTax){
            txtGroundTax.setStyle(errorStyle);
        }else{
            txtGroundTax.setStyle(style);
        }


        double importt = 0.0, export = 0.0, ground = 0.0;

        try {
            importt = Double.parseDouble(importTax);
            export = Double.parseDouble(exportTax);
            ground = Double.parseDouble(groundTax);

            if (importt < 0 || export < 0 || ground < 0) {
                new Alert(Alert.AlertType.WARNING, "Tax values cannot be negative.").show();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values for all taxes.").show();
            return;
        }

        if(isValidExportTax && isValidImportTax && isValidGroundTax){
            TaxDTO taxDTO = new TaxDTO(
                    vehicleId,
                    taxId,
                    importt,
                    export,
                    ground
            );

            boolean isSaved = taxModel.saveTax(taxDTO);
            if (isSaved) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Tax was saved.").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Tax was not saved.").show();
            }
        }

    }

    @FXML
    void updateTax(ActionEvent event) throws SQLException, ClassNotFoundException {
        String vehicleId = cmbVehicleID.getValue();
        String taxId = lblTaxID.getText();

        if (vehicleId == null || vehicleId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a vehicle ID.").show();
            return;
        }

        String importTax = txtImportTax.getText();
        String exportTax = txtExportTax.getText();
        String groundTax = txtGroundTax.getText();

        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";

        boolean isValidImportTax = importTax.matches(doubleValuesPattern);
        boolean isValidExportTax = exportTax.matches(doubleValuesPattern);
        boolean isValidGroundTax = groundTax.matches(doubleValuesPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidImportTax){
            txtImportTax.setStyle(errorStyle);
        }else{
            txtImportTax.setStyle(style);
        }
        if(!isValidExportTax){
            txtExportTax.setStyle(errorStyle);
        }else{
            txtExportTax.setStyle(style);
        }
        if(!isValidGroundTax){
            txtGroundTax.setStyle(errorStyle);
        }else{
            txtGroundTax.setStyle(style);
        }


        double importt = 0.0, export = 0.0, ground = 0.0;

        try {
            importt = Double.parseDouble(importTax);
            export = Double.parseDouble(exportTax);
            ground = Double.parseDouble(groundTax);

            if (importt < 0 || export < 0 || ground < 0) {
                new Alert(Alert.AlertType.WARNING, "Tax values cannot be negative.").show();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values for all taxes.").show();
            return;
        }

        if(isValidExportTax && isValidImportTax && isValidGroundTax){
            TaxDTO taxDTO = new TaxDTO(
                    vehicleId,
                    taxId,
                    importt,
                    export,
                    ground
            );

            boolean isUpdated = taxModel.updateTax(taxDTO);
            if (isUpdated) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Tax was updated.").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Tax was not update.").show();
            }
        }

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

    private void loadVehicleIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> vehicleIDs = vehicleModel.getAllVehicleIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList(vehicleIDs);
        cmbVehicleID.setItems(observableList);
    }

    private void setCellValues() {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colTaxID.setCellValueFactory(new PropertyValueFactory<>("tax_id"));
        colImportTax.setCellValueFactory(new PropertyValueFactory<>("import_tax"));
        colExportTax.setCellValueFactory(new PropertyValueFactory<>("export_tax"));
        colGroundTAx.setCellValueFactory(new PropertyValueFactory<>("ground_tax"));
    }

    private void loadNextTaxId() throws SQLException, ClassNotFoundException {
        String taxId = taxModel.getNextTaxID();
        lblTaxID.setText(taxId);
    }
}
