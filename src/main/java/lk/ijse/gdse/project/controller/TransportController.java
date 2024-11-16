//package lk.ijse.gdse.project.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import lk.ijse.gdse.project.Model.DriverModel;
//import lk.ijse.gdse.project.Model.TransportModel;
//import lk.ijse.gdse.project.dto.TransportDTO;
//import lk.ijse.gdse.project.dto.tm.TransportTM;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class TransportController implements Initializable {
//
//    @FXML
//    private ToggleGroup TransportType1;
//
//    @FXML
//    private Button btnDeleteTransport;
//
//    @FXML
//    private Button btnSaveTransport;
//
//    @FXML
//    private Button btnTransportDetail;
//
//    @FXML
//    private Button btnUpdateTransport;
//
//    @FXML
//    private ComboBox<String> cmdDriverID;
//
//    @FXML
//    private TableColumn<TransportTM, String> colDriverID;
//
//    @FXML
//    private TableColumn<TransportTM, String> colEndDate;
//
//    @FXML
//    private TableColumn<TransportTM, String> colStartDate;
//
//    @FXML
//    private TableColumn<TransportTM, String> colTransportID;
//
//    @FXML
//    private TableColumn<TransportTM, String> colTransportType;
//
//    @FXML
//    private DatePicker dpEndDate;
//
//    @FXML
//    private DatePicker dpStartDate;
//
//    @FXML
//    private Label lblTransportID;
//
//    @FXML
//    private RadioButton rbExport;
//
//    @FXML
//    private RadioButton rbImport;
//
//    @FXML
//    private TableView<TransportTM> tblTransport;
//
//    private final TransportModel transportModel = new TransportModel();
//    private final DriverModel driverModel = new DriverModel();
//
//    @FXML
//    void deleteTransport(ActionEvent event) {
//
//    }
//
//    @FXML
//    void generateTransportRepo(ActionEvent event) {
//
//    }
//
//    @FXML
//    void saveTransport(ActionEvent event) throws SQLException, ClassNotFoundException {
////        String startdate = null;
////        String enddate = null;
////
////        String driverId = cmdDriverID.getValue();
////        String transportId = lblTransportID.getText();
////
////        if (dpStartDate.getValue() != null) {
////            startdate = dpStartDate.getValue().toString();
////        } else {
////            new Alert(Alert.AlertType.WARNING, "Please select a start date.").show();
////            return;
////        }
////        enddate = dpEndDate.getValue().toString();
////
////        String transportType;
////        if (rbExport.isSelected()) {
////            transportType = "export";
////        }else if (rbImport.isSelected()) {
////            transportType = "import";
////        }else {
////            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
////            return;
////        }
////
////        if (driverId == null || driverId.isEmpty()) {
////            new Alert(Alert.AlertType.WARNING, "Please select driver id.").show();
////            return;
////        }
////
////        TransportDTO transportDTO = new TransportDTO(
////                transportId,
////                transportType,
////                startdate,
////                enddate,
////                driverId
////        );
////
////        boolean isSaved = transportModel.saveTransport(transportDTO);
////        if (isSaved) {
////            refeshpage();
////            new Alert(Alert.AlertType.INFORMATION, "Transport saved..!").show();
////        } else {
////            new Alert(Alert.AlertType.ERROR, "Failed to save transport.").show();
////        }
//        String startdate = null;
//        String enddate = null;
//
//        String driverId = cmdDriverID.getValue();
//        String transportId = lblTransportID.getText();
//
//        if (dpStartDate.getValue() != null) {
//            startdate = dpStartDate.getValue().toString();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Please select a start date.").show();
//            return;
//        }
//
//        if (dpEndDate.getValue() != null) {
//            enddate = dpEndDate.getValue().toString();
//        }
//
//        String transportType;
//        if (rbExport.isSelected()) {
//            transportType = "export";
//        } else if (rbImport.isSelected()) {
//            transportType = "import";
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
//            return;
//        }
//
//        if (driverId == null || driverId.isEmpty()) {
//            new Alert(Alert.AlertType.WARNING, "Please select a driver ID.").show();
//            return;
//        }
//
//        TransportDTO transportDTO = new TransportDTO(
//                transportId,
//                transportType,
//                startdate,
//                enddate,
//                driverId
//        );
//
//        boolean isSaved = transportModel.saveTransport(transportDTO);
//        if (isSaved) {
//            refeshpage(); // Refresh the page or UI
//            new Alert(Alert.AlertType.INFORMATION, "Transport saved..!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Failed to save transport.").show();
//        }
//
//
//
//    }
//
//    @FXML
//    void updateTranport(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String driverId = cmdDriverID.getValue();
//        String transportId = lblTransportID.getText();
//        String startdate = dpStartDate.getValue().toString();
//        String enddate = dpEndDate.getValue().toString();
//
//        String transportType;
//        if (rbExport.isSelected()) {
//            transportType = "export";
//        }else if (rbImport.isSelected()) {
//            transportType = "import";
//        }else {
//            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
//            return;
//        }
//
//        if (driverId == null || driverId.isEmpty()) {
//            new Alert(Alert.AlertType.WARNING, "Please select driver id.").show();
//            return;
//        }
//
//        TransportDTO transportDTO = new TransportDTO(
//                transportId,
//                transportType,
//                startdate,
//                enddate,
//                driverId
//        );
//
//        boolean isUpdate = transportModel.updateTransport(transportDTO);
//        if (isUpdate) {
//            refeshpage();
//            new Alert(Alert.AlertType.INFORMATION, "Transport saved..!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Failed to save transport.").show();
//        }
//
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        setCellValues();
//        try{
//            refeshpage();
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Fail to load transport id").show();
//        }
//    }
//
//    private void refeshpage() throws SQLException, ClassNotFoundException {
//        lblTransportID.setText(transportModel.getNextTransportID());
//        cmdDriverID.getSelectionModel().clearSelection();
//        TransportType1.selectToggle(null);
//        dpStartDate.setValue(null);
//        dpEndDate.setValue(null);
//
//        btnSaveTransport.setDisable(false);
//        btnUpdateTransport.setDisable(true);
//        btnDeleteTransport.setDisable(true);
//
//        loadDriverIds();
//        loadTableData();
//
//    }
//
//    private void loadDriverIds() throws SQLException, ClassNotFoundException {
//        ArrayList<String> driverIds = driverModel.getAllDriverIds();
//        ObservableList<String> observableList = FXCollections.observableArrayList(driverIds);
//        cmdDriverID.setItems(observableList);
//    }
//
//    private void loadTableData() throws SQLException, ClassNotFoundException {
//        ArrayList<TransportDTO> transportDTOS = transportModel.getAllTransports();
//        ObservableList<TransportTM> transportTMS = FXCollections.observableArrayList();
//
//            for (TransportDTO transportDTO : transportDTOS) {
//                TransportTM transportTM = new TransportTM(
//                        transportDTO.getTransport_id(),
//                        transportDTO.getTransport_type(),
//                        transportDTO.getStart_date(),
//                        transportDTO.getEnd_date(),
//                        transportDTO.getDriver_id()
//                );
//                transportTMS.add(transportTM);
//            }
//            tblTransport.setItems(transportTMS);
//
//    }
//
//    private void setCellValues() {
//        colTransportID.setCellValueFactory(new PropertyValueFactory<>("transport_id"));
//        colTransportType.setCellValueFactory(new PropertyValueFactory<>("transport_type"));
//        colStartDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
//        colEndDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
//        colDriverID.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
//    }
//
//    public void onClickedTabel(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
//        TransportTM transportTM = tblTransport.getSelectionModel().getSelectedItem();
//        if (transportTM != null) {
//            cmdDriverID.getItems().clear();
//            List<String> driverIDs = driverModel.getAllDriverIds();
//            if (driverIDs != null && !driverIDs.isEmpty()) {
//                cmdDriverID.getItems().addAll(driverIDs);
//                cmdDriverID.setValue(transportTM.getDriver_id());
//            }
//        }
//
//        lblTransportID.setText(transportTM.getTransport_id());
//        dpStartDate.setValue(LocalDate.parse(transportTM.getStart_date()));
//        dpEndDate.setValue(LocalDate.parse(transportTM.getEnd_date()));
//
//        if("Export".equalsIgnoreCase(transportTM.getTransport_type())){
//            rbExport.setDisable(true);
//            rbImport.setDisable(false);
//        } else if ("Import".equalsIgnoreCase(transportTM.getTransport_type())) {
//            rbExport.setDisable(false);
//            rbImport.setDisable(true);
//        }
//
//        btnSaveTransport.setDisable(true);
//        btnUpdateTransport.setDisable(false);
//        btnDeleteTransport.setDisable(false);
//    }
//}

package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.DriverModel;
import lk.ijse.gdse.project.Model.TransportModel;
import lk.ijse.gdse.project.dto.TransportDTO;
import lk.ijse.gdse.project.dto.tm.TransportTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransportController implements Initializable {

    @FXML
    private ToggleGroup TransportType1;

    @FXML
    private Button btnDeleteTransport;

    @FXML
    private Button btnSaveTransport;

    @FXML
    private Button btnTransportDetail;

    @FXML
    private Button btnUpdateTransport;

    @FXML
    private ComboBox<String> cmdDriverID;

    @FXML
    private TableColumn<TransportTM, String> colDriverID;

    @FXML
    private TableColumn<TransportTM, Date> colEndDate;

    @FXML
    private TableColumn<TransportTM, Date> colStartDate;

    @FXML
    private TableColumn<TransportTM, String> colTransportID;

    @FXML
    private TableColumn<TransportTM, String> colTransportType;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private Label lblTransportID;

    @FXML
    private RadioButton rbExport;

    @FXML
    private RadioButton rbImport;

    @FXML
    private TableView<TransportTM> tblTransport;

    private final TransportModel transportModel = new TransportModel();
    private final DriverModel driverModel = new DriverModel();

    @FXML
    void deleteTransport(ActionEvent event) throws SQLException, ClassNotFoundException {
        String transportId = lblTransportID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = transportModel.deleteTransport(transportId);
            if (isDeleted) {
                refeshpage();
                new Alert(Alert.AlertType.INFORMATION, "Transport deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to delete transport").show();
            }
        }

    }

    @FXML
    void generateTransportRepo(ActionEvent event) {

    }

    @FXML
    void onClickedTabel(MouseEvent event) throws SQLException, ClassNotFoundException {
        TransportTM transportTM = tblTransport.getSelectionModel().getSelectedItem();
        if (transportTM != null) {
            cmdDriverID.getItems().clear();
            List<String> driverIds = driverModel.getAllDriverIds();
            if(driverIds != null && !driverIds.isEmpty()) {
                cmdDriverID.getItems().addAll(driverIds);
                cmdDriverID.setValue(transportTM.getDriver_id());
            }
        }

        lblTransportID.setText(transportTM.getTransport_id());

        String startDateStr = transportTM.getStart_date();
        if (startDateStr != null && !startDateStr.isEmpty()) {
            try {
                dpStartDate.setValue(LocalDate.parse(startDateStr));
            } catch (DateTimeParseException e) {
                new Alert(Alert.AlertType.WARNING, "Invalid start date format.").show();
            }
        } else {
            dpStartDate.setValue(null);
        }

        String endDateStr = transportTM.getEnd_date();
        if (endDateStr != null && !endDateStr.isEmpty()) {
            try {
                dpEndDate.setValue(LocalDate.parse(endDateStr));
            } catch (DateTimeParseException e) {
                new Alert(Alert.AlertType.WARNING, "Invalid end date format.").show();
            }
        } else {
            dpEndDate.setValue(null);
        }

        if("Export".equalsIgnoreCase(transportTM.getTransport_type())) {
            rbExport.setSelected(true);
            rbImport.setSelected(false);
        } else if ("Import".equalsIgnoreCase(transportTM.getTransport_type())) {
            rbExport.setSelected(false);
            rbImport.setSelected(true);
        }

        btnSaveTransport.setDisable(true);
        btnDeleteTransport.setDisable(false);
        btnUpdateTransport.setDisable(false);

    }

    @FXML
    void saveTransport(ActionEvent event) throws SQLException, ClassNotFoundException {
        String startdate = null;
        String enddate = null;

        String driverId = cmdDriverID.getValue();
        String transportId = lblTransportID.getText();

        if (dpStartDate.getValue() != null) {
            startdate = dpStartDate.getValue().toString();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a start date.").show();
            return;
        }

        if (dpEndDate.getValue() != null) {
            enddate = dpEndDate.getValue().toString();
        }

        String transportType;
        if (rbExport.isSelected()) {
            transportType = "export";
        } else if (rbImport.isSelected()) {
            transportType = "import";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
            return;
        }

        if (driverId == null || driverId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a driver ID.").show();
            return;
        }

        TransportDTO transportDTO = new TransportDTO(
                transportId,
                transportType,
                startdate,
                enddate,
                driverId
        );

        boolean isSaved = transportModel.saveTransport(transportDTO);
        if (isSaved) {
            refeshpage();
            new Alert(Alert.AlertType.INFORMATION, "Transport saved..!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save transport.").show();
        }

    }

    @FXML
    void updateTranport(ActionEvent event) throws SQLException, ClassNotFoundException {
        String startdate = null;
        String enddate = null;

        String driverId = cmdDriverID.getValue();
        String transportId = lblTransportID.getText();

        if (dpStartDate.getValue() != null) {
            startdate = dpStartDate.getValue().toString();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a start date.").show();
            return;
        }

        if (dpEndDate.getValue() != null) {
            enddate = dpEndDate.getValue().toString();
        }

        String transportType;
        if (rbExport.isSelected()) {
            transportType = "export";
        } else if (rbImport.isSelected()) {
            transportType = "import";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
            return;
        }

        if (driverId == null || driverId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a driver ID.").show();
            return;
        }

        TransportDTO transportDTO = new TransportDTO(
                transportId,
                transportType,
                startdate,
                enddate,
                driverId
        );

        boolean isUpdate = transportModel.updateTransport(transportDTO);
        if (isUpdate) {
            refeshpage();
            new Alert(Alert.AlertType.INFORMATION, "Transport updated..!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update transport.").show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();
        try{
            refeshpage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load transport id").show();
        }
    }

    private void refeshpage() throws SQLException, ClassNotFoundException {
        lblTransportID.setText(transportModel.getNextTransportID());
        cmdDriverID.getSelectionModel().clearSelection();
        TransportType1.selectToggle(null);
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);

        btnSaveTransport.setDisable(false);
        btnUpdateTransport.setDisable(true);
        btnDeleteTransport.setDisable(true);

        loadDriverIds();
        loadTableData();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<TransportDTO> transportDTOS = transportModel.getAllTransports();
        ObservableList<TransportTM> transportTMS = FXCollections.observableArrayList();

            for (TransportDTO transportDTO : transportDTOS) {
                TransportTM transportTM = new TransportTM(
                        transportDTO.getTransport_id(),
                        transportDTO.getTransport_type(),
                        transportDTO.getStart_date(),
                        transportDTO.getEnd_date(),
                        transportDTO.getDriver_id()
                );
                transportTMS.add(transportTM);
            }
            tblTransport.setItems(transportTMS);

    }

    private void loadDriverIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> driverIds = driverModel.getAllDriverIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(driverIds);
        cmdDriverID.setItems(observableList);
    }

    private void setCellValues() {
        colTransportID.setCellValueFactory(new PropertyValueFactory<>("transport_id"));
        colTransportType.setCellValueFactory(new PropertyValueFactory<>("transport_type"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        colDriverID.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
    }
}

