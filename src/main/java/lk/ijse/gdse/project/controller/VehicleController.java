package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.*;
import lk.ijse.gdse.project.dto.VehicleDTO;
import lk.ijse.gdse.project.dto.tm.VehicleTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleController implements Initializable {


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
    private ComboBox<String> cmbTransportID;

    @FXML
    private ComboBox<String> cmdExportCompanyID;

    @FXML
    private ComboBox<String> cmdImportCompanyID;

    @FXML
    private ComboBox<String> cmdRevervationID;

    @FXML
    private TableColumn<VehicleTM, String> colColor;

    @FXML
    private TableColumn<VehicleTM, String> colModel;

    @FXML
    private TableColumn<VehicleTM, String> colStatus;

    @FXML
    private TableColumn<VehicleTM, String> colVehicleID;

    @FXML
    private TableColumn<VehicleTM, Integer> colYear;

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
    private TableView<VehicleTM> tblVehicle;

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

    private final VehicleModel vehicle = new VehicleModel();
    private final ReservationModel reservation = new ReservationModel();
    private final ImportCompanyModel importCompany = new ImportCompanyModel();
    private final ExportCompanyModel exportCompany = new ExportCompanyModel();
    private final TransportModel transportModel = new TransportModel();

    @FXML
    void deleteVehicle(ActionEvent event) throws SQLException, ClassNotFoundException {
        String vId = lblVehicleID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = vehicle.deleteVehicle(vId);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Vehicle deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to delete vehicle").show();
            }
        }
    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void generateVehicleReport(ActionEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        VehicleTM vehicleTM = tblVehicle.getSelectionModel().getSelectedItem();
        if (vehicleTM != null) {
            //Import Company Ids
            cmdImportCompanyID.getItems().clear();
            List<String> importCompantIDs = importCompany.getAllImportCompantIDs();
            if (importCompantIDs != null && !importCompantIDs.isEmpty()) {
                cmdImportCompanyID.getItems().addAll(importCompantIDs);
                cmdImportCompanyID.setValue(vehicleTM.getImport_company_id());
            }

            //Export Comapny Ids
            cmdExportCompanyID.getItems().clear();
            List<String> expIDs = exportCompany.getAllExportCompanyIDs();
            if (expIDs != null && !expIDs.isEmpty()) {
                cmdExportCompanyID.getItems().addAll(expIDs);
                cmdExportCompanyID.setValue(vehicleTM.getExport_company_id());
            }

            //Reservation Ids
            cmdRevervationID.getItems().clear();
            List<String> reservIds = reservation.getAllReservationIDS();
            if (reservIds != null && !reservIds.isEmpty()) {
                cmdRevervationID.getItems().addAll(reservIds);
                cmdRevervationID.setValue(vehicleTM.getReservation_id());
            }

            //Transport Ids
            cmbTransportID.getItems().clear();
            List<String> transportIds = transportModel.getAllTransportIds();
            if (transportIds != null && !transportIds.isEmpty()) {
                cmbTransportID.getItems().addAll(transportIds);
                cmbTransportID.setValue(vehicleTM.getTransport_id());
            }

            lblVehicleID.setText(vehicleTM.getVehicle_id());
            txtModel.setText(vehicleTM.getModel());
            txtYear.setText(String.valueOf(vehicleTM.getYear()));
            txtColor.setText(vehicleTM.getColor());

            String vehicleStatus = vehicleTM.getCurrent_status();
            if ("Import".equalsIgnoreCase(vehicleStatus)) {
                rbImport.setSelected(true);
            } else if ("Repair".equalsIgnoreCase(vehicleStatus)) {
                rbRepair.setSelected(true);
            } else if ("Sale".equalsIgnoreCase(vehicleStatus)) {
                rbSale.setSelected(true);
            } else if ("Export".equalsIgnoreCase(vehicleStatus)) {
                rbExport.setSelected(true);
            }

            txtExportDate.setText(vehicleTM.getExport_date());
            txtImportDate.setText(vehicleTM.getImport_date());
            txtSaleDate.setText(vehicleTM.getSale_date());
            txtExportPrice.setText(String.valueOf(vehicleTM.getExport_price()));
            txtImportPrice.setText(String.valueOf(vehicleTM.getImport_price()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }


    @FXML
    void saveVehicle(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            String importCompanyID = cmdImportCompanyID.getValue();
            String exportCompanyID = cmdExportCompanyID.getValue();
            String vehicleId = lblVehicleID.getText();
            String model = txtModel.getText();
            String year = txtYear.getText();
            String color = txtColor.getText();
            String exportDate = txtExportDate.getText().isEmpty() ? null : txtExportDate.getText();
            String importDate = txtImportDate.getText().isEmpty() ? null : txtImportDate.getText();
            String saleDate = txtSaleDate.getText().isEmpty() ? null : txtSaleDate.getText();
            String exportPrice = txtExportPrice.getText().isEmpty() ? "0.0" : txtExportPrice.getText();
            String importPrice = txtImportPrice.getText().isEmpty() ? "0.0" : txtImportPrice.getText();
            String reservationID = cmdRevervationID.getValue();
            String transportID = cmbTransportID.getValue();

            if (importCompanyID == null) {
                new Alert(Alert.AlertType.WARNING, "Please select an import company.").show();
                return;
            }

            if (reservationID != null && exportCompanyID != null) {
                new Alert(Alert.AlertType.WARNING, "You cannot add both Reservation ID and Export Company ID at the same time.").show();
                return;
            }

            if (reservationID != null && rbExport.isSelected()) {
                new Alert(Alert.AlertType.WARNING, "Transport method cannot be 'Export' when a reservation ID is set.").show();
                return;
            }

            if (exportCompanyID != null) {
                if (rbSale.isSelected()) {
                    new Alert(Alert.AlertType.WARNING, "Transport method cannot be 'Sale' when an export company ID is set.").show();
                    return;
                }
                if (!txtSaleDate.getText().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Sale date cannot be added when an export company ID is set.").show();
                    return;
                }
                if (reservationID != null) {
                    new Alert(Alert.AlertType.WARNING, "Reservation ID cannot be added when an export company ID is set.").show();
                    return;
                }
            }

            String transportMethod;
            if (rbImport.isSelected()) {
                transportMethod = "Import";
            } else if (rbRepair.isSelected()) {
                transportMethod = "Repair";
            } else if (rbExport.isSelected()) {
                transportMethod = "Export";
            } else if (rbSale.isSelected()) {
                transportMethod = "Sale";
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a transport method.").show();
                return;
            }

            String modelPattern = "^[A-Za-z ]+$";
            String colorPattern = "^[A-Za-z ]+$";
            String datePattern = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";
            String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";
            String yearPattern = "^\\d{4}$";

            boolean isValidModel = model.matches(modelPattern);
            boolean isValidYear = year.matches(yearPattern);
            boolean isValidColor = color.matches(colorPattern);
            boolean isValidImportDate = importDate == null || importDate.matches(datePattern);
            boolean isValidExportDate = exportDate == null || exportDate.matches(datePattern);
            boolean isValidSaleDate = saleDate == null || saleDate.matches(datePattern);
            boolean isValidImportPrice = importPrice.matches(doubleValuesPattern);
            boolean isValidExportPrice = exportPrice.matches(doubleValuesPattern);

            String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
            String style = "-fx-border-color: #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

            txtModel.setStyle(isValidModel ? style : errorStyle);
            txtYear.setStyle(isValidYear ? style : errorStyle);
            txtColor.setStyle(isValidColor ? style : errorStyle);
            txtImportDate.setStyle(isValidImportDate ? style : errorStyle);
            txtExportDate.setStyle(isValidExportDate ? style : errorStyle);
            txtSaleDate.setStyle(isValidSaleDate ? style : errorStyle);
            txtImportPrice.setStyle(isValidImportPrice ? style : errorStyle);
            txtExportPrice.setStyle(isValidExportPrice ? style : errorStyle);

            int yearParsed = Integer.parseInt(year);
            double importPriceParsed = Double.parseDouble(importPrice);
            double exportPriceParsed = Double.parseDouble(exportPrice);

            if (yearParsed < 0 || importPriceParsed < 0 || exportPriceParsed < 0) {
                new Alert(Alert.AlertType.WARNING, "Numeric values cannot be negative.").show();
                return;
            }

            if (isValidModel && isValidYear && isValidColor && isValidImportDate && isValidExportDate
                    && isValidSaleDate && isValidImportPrice && isValidExportPrice) {
                VehicleDTO vehicleDTO = new VehicleDTO(
                        importCompanyID,
                        importDate,
                        vehicleId,
                        model,
                        yearParsed,
                        color,
                        transportMethod,
                        exportCompanyID,
                        exportDate,
                        saleDate,
                        importPriceParsed,
                        exportPriceParsed,
                        reservationID,
                        transportID
                );

                boolean isSaved = vehicle.saveVehicle(vehicleDTO);
                if (isSaved) {
                    refeshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Vehicle saved..!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save vehicle.").show();
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values where required.").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }

    }

    @FXML
    void updateVehicle(ActionEvent event) {
        try {
            String importCompanyID = cmdImportCompanyID.getValue();
            String exportCompanyID = cmdExportCompanyID.getValue();
            String vehicleId = lblVehicleID.getText();
            String model = txtModel.getText();
            String year = txtYear.getText();
            String color = txtColor.getText();
            String exportDate = txtExportDate.getText().isEmpty() ? null : txtExportDate.getText();
            String importDate = txtImportDate.getText().isEmpty() ? null : txtImportDate.getText();
            String saleDate = txtSaleDate.getText().isEmpty() ? null : txtSaleDate.getText();
            String exportPrice = txtExportPrice.getText().isEmpty() ? "0.0" : txtExportPrice.getText();
            String importPrice = txtImportPrice.getText().isEmpty() ? "0.0" : txtImportPrice.getText();
            String reservationID = cmdRevervationID.getValue();
            String transportID = cmbTransportID.getValue();

            if (importCompanyID == null) {
                new Alert(Alert.AlertType.WARNING, "Please select an import company.").show();
                return;
            }

            if (reservationID != null) {
                if (exportCompanyID != null || exportDate != null || !exportPrice.equals("0.0")) {
                    new Alert(Alert.AlertType.WARNING, "Export ID, Export Date, and Export Price cannot be added when Reservation ID is set.").show();
                    return;
                }
            }

            if (exportCompanyID != null) {
                if (reservationID != null || saleDate != null) {
                    new Alert(Alert.AlertType.WARNING, "Reservation ID and Sale Date cannot be added when Export ID is set.").show();
                    return;
                }
            }

            String transportMethod;
            if (rbImport.isSelected()) {
                transportMethod = "Import";
            } else if (rbRepair.isSelected()) {
                transportMethod = "Repair";
            } else if (rbExport.isSelected()) {
                transportMethod = "Export";
            } else if (rbSale.isSelected()) {
                transportMethod = "Sale";
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a transport method.").show();
                return;
            }

            String modelPattern = "^[A-Za-z ]+$";
            String colorPattern = "^[A-Za-z ]+$";
            String datePattern = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";
            String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";
            String yearPattern = "^\\d{4}$";

            boolean isValidModel = model.matches(modelPattern);
            boolean isValidYear = year.matches(yearPattern);
            boolean isValidColor = color.matches(colorPattern);
            boolean isValidImportDate = importDate == null || importDate.matches(datePattern);
            boolean isValidExportDate = exportDate == null || exportDate.matches(datePattern);
            boolean isValidSaleDate = saleDate == null || saleDate.matches(datePattern);
            boolean isValidImportPrice = importPrice.matches(doubleValuesPattern);
            boolean isValidExportPrice = exportPrice.matches(doubleValuesPattern);

            String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
            String style = "-fx-border-color: #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

            txtModel.setStyle(isValidModel ? style : errorStyle);
            txtYear.setStyle(isValidYear ? style : errorStyle);
            txtColor.setStyle(isValidColor ? style : errorStyle);
            txtImportDate.setStyle(isValidImportDate ? style : errorStyle);
            txtExportDate.setStyle(isValidExportDate ? style : errorStyle);
            txtSaleDate.setStyle(isValidSaleDate ? style : errorStyle);
            txtImportPrice.setStyle(isValidImportPrice ? style : errorStyle);
            txtExportPrice.setStyle(isValidExportPrice ? style : errorStyle);

            int yearParsed = Integer.parseInt(year);
            double importPriceParsed = Double.parseDouble(importPrice);
            double exportPriceParsed = Double.parseDouble(exportPrice);

            if (yearParsed < 0 || importPriceParsed < 0 || exportPriceParsed < 0) {
                new Alert(Alert.AlertType.WARNING, "Numeric values cannot be negative.").show();
                return;
            }

            if (isValidModel && isValidYear && isValidColor && isValidImportDate && isValidExportDate
                    && isValidSaleDate && isValidImportPrice && isValidExportPrice) {
                VehicleDTO vehicleDTO = new VehicleDTO(
                        importCompanyID,
                        importDate,
                        vehicleId,
                        model,
                        yearParsed,
                        color,
                        transportMethod,
                        exportCompanyID,
                        exportDate,
                        saleDate,
                        importPriceParsed,
                        exportPriceParsed,
                        reservationID,
                        transportID
                );

                boolean isSaved = vehicle.saveVehicle(vehicleDTO);
                if (isSaved) {
                    refeshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Vehicle saved..!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save vehicle.").show();
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values where required.").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
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
        lblVehicleID.setText(vehicle.getNextVehicleID());
        btnGroup.selectToggle(null);
        cmdImportCompanyID.getSelectionModel().clearSelection();
        cmdExportCompanyID.getSelectionModel().clearSelection();
        cmdRevervationID.getSelectionModel().clearSelection();
        cmbTransportID.getSelectionModel().clearSelection();
        txtModel.setText("");
        txtYear.setText("");
        txtColor.setText("");
        txtExportDate.setText("");
        txtExportPrice.setText("");
        txtImportDate.setText("");
        txtImportPrice.setText("");
        txtSaleDate.setText("");

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        loadImportCompaniesID();
        loadExportCompaniesID();
        loadReservationID();
        loadTransportID();
        loadTableData();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> vehicleDTOS = vehicle.getAllVehicles();
        ObservableList<VehicleTM> vehicleTMS = FXCollections.observableArrayList();

            for (VehicleDTO vehicleDTO : vehicleDTOS) {
                VehicleTM vehicleTM = new VehicleTM(
                        vehicleDTO.getImport_company_id(),
                        vehicleDTO.getImport_date(),
                        vehicleDTO.getVehicle_id(),
                        vehicleDTO.getModel(),
                        vehicleDTO.getYear(),
                        vehicleDTO.getColor(),
                        vehicleDTO.getCurrent_status(),
                        vehicleDTO.getExport_company_id(),
                        vehicleDTO.getExport_date(),
                        vehicleDTO.getSale_date(),
                        vehicleDTO.getImport_price(),
                        vehicleDTO.getExport_price(),
                        vehicleDTO.getReservation_id(),
                        vehicleDTO.getTransport_id()
                );
                vehicleTMS.add(vehicleTM);
            }
            tblVehicle.setItems(vehicleTMS);
    }

    private void loadTransportID() throws SQLException, ClassNotFoundException {
        ArrayList<String> transportID = transportModel.getAllTransportIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(transportID);
        cmbTransportID.setItems(observableList);
    }

    private void loadReservationID() throws SQLException, ClassNotFoundException {
        ArrayList<String> reservID = reservation.getAllReservationIDS();
        ObservableList<String> observableList = FXCollections.observableArrayList(reservID);
        cmdRevervationID.setItems(observableList);
    }


    private void loadExportCompaniesID() throws SQLException, ClassNotFoundException {
        ArrayList<String> exportIDS = exportCompany.getAllExportCompanyIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList(exportIDS);
        cmdExportCompanyID.setItems(observableList);
    }

    private void loadImportCompaniesID() throws SQLException, ClassNotFoundException {
        ArrayList<String> importIDS = importCompany.getAllImportCompantIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList(importIDS);
        cmdImportCompanyID.setItems(observableList);
    }

    private void setCellValues() {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("current_status"));
    }

    private void loadNextVehicleID() throws SQLException, ClassNotFoundException {
        String vehicleID = vehicle.getNextVehicleID();
        lblVehicleID.setText(vehicleID);
    }

    public void clearExportId(ActionEvent actionEvent) {
        cmdExportCompanyID.setValue(null);
    }

    public void clearreservationId(ActionEvent actionEvent) {
        cmdRevervationID.setValue(null);
    }
}
