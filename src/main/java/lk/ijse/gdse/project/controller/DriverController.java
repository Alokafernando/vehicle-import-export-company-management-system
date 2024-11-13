//package lk.ijse.gdse.project.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.HBox;
//import lk.ijse.gdse.project.Model.CustomerModel;
//import lk.ijse.gdse.project.Model.DriverModel;
//import lk.ijse.gdse.project.dto.DriverDTO;
//import lk.ijse.gdse.project.dto.tm.DriverTM;
//
//import java.awt.event.MouseEvent;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.ResourceBundle;
//
//public class DriverController implements Initializable {
//
//    @FXML
//    private Button btnDriverDetails;
//
//    @FXML
//    private Button btnSaveDriver;
//
//    @FXML
//    private Button btndeleteDriver;
//
//    @FXML
//    private Button btnupdateDriver;
//
//    @FXML
//    private TableColumn<DriverTM, String> colContact;
//
//    @FXML
//    private TableColumn<DriverTM, String> colDiverID;
//
//    @FXML
//    private TableColumn<DriverTM, String> colName;
//
//    @FXML
//    private Label lblDiverID;
//
//    @FXML
//    private HBox lblTransporstID;
//
//    @FXML
//    private HBox lblTransporstID1;
//
//    @FXML
//    private HBox lblTransporstID11;
//
//    @FXML
//    private TableView<DriverTM> tblDriver;
//
//    @FXML
//    private TextField txtDriverContact;
//
//    @FXML
//    private TextField txtDriverName;
//
//    DriverModel driverModel = new DriverModel();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        colDiverID.setCellValueFactory(new PropertyValueFactory<>("diver_id"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
//
//        try{
//            refreshPage();
//        } catch (SQLException e) {
//           e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Fail to load driver table").show();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Fail to load driver table").show();
//        }
//    }
//
//    @FXML
//    void deleteDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String driverID = lblDiverID.getText();
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
//        Optional<ButtonType> optionalButtonType = alert.showAndWait();
//
//        if (optionalButtonType.get() == ButtonType.YES) {
//            boolean isDeleted = driverModel.deleteDriver(driverID);
//            if (isDeleted) {
//                refreshPage();
//                new Alert(Alert.AlertType.INFORMATION, "Driver deleted").show();
//            }else{
//                new Alert(Alert.AlertType.ERROR, "Fail to delete driver").show();
//            }
//        }
//
//    }
//
//    @FXML
//    void generateDriverdetailRepo(ActionEvent event) {
//
//    }
//
//    @FXML
//    void saveDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String driverId = lblDiverID.getText();
//        String driverName = txtDriverName.getText();
//        String contact = txtDriverContact.getText();
//
//        DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);
//
//        boolean isSaved = driverModel.saveDriver(driverDTO);
//        if (isSaved) {
//            refreshPage();
//            new Alert(Alert.AlertType.INFORMATION, "Driver saved...!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Fail to save driver...!").show();
//        }
//    }
//
//    private void refreshPage() throws SQLException, ClassNotFoundException {
//        loadNextDriverID();
//        loadTableData();
//
//        btnSaveDriver.setDisable(false);
//        btnupdateDriver.setDisable(true);
//        btndeleteDriver.setDisable(true);
//
//        txtDriverName.setText("");
//        txtDriverContact.setText("");
//    }
//
//    private void loadTableData() throws SQLException, ClassNotFoundException {
//        ArrayList<DriverDTO> driverDTOS = driverModel.getAllDrivers();
//        ObservableList<DriverTM> driverTMS = FXCollections.observableArrayList();
//
//        for (DriverDTO driverDTO : driverDTOS) {
//            DriverTM driverTM = new DriverTM(
//                    driverDTO.getDriver_id(),
//                    driverDTO.getName(),
//                    driverDTO.getContact()
//            );
//            driverTMS.add(driverTM);
//        }
//        tblDriver.setItems(driverTMS);
//    }
//
//
//    private void loadNextDriverID() throws SQLException, ClassNotFoundException {
//        String nextDriverId = driverModel.getNextDriverId();
//        lblDiverID.setText(nextDriverId);
//    }
//
//    @FXML
//    void updateDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String driverId = lblDiverID.getText();
//        String driverName = txtDriverName.getText();
//        String contact = txtDriverContact.getText();
//
//        DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);
//
//        boolean isSaved = driverModel.updateDriver(driverDTO);
//        if (isSaved) {
//            refreshPage();
//            new Alert(Alert.AlertType.INFORMATION, "Driver updated...!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Fail to update driver...!").show();
//        }
//
//
//    }
//
//    @FXML
//    void onClickedTable(MouseEvent event) {
//        DriverTM driverTM = tblDriver.getSelectionModel().getSelectedItem();
//        if (driverTM != null) {
//            lblDiverID.setText(driverTM.getDriver_id());
//            txtDriverName.setText(driverTM.getName());
//            txtDriverContact.setText(driverTM.getContact());
//
//            btnSaveDriver.setDisable(true);
//            btnupdateDriver.setDisable(false);
//            btndeleteDriver.setDisable(false);
//        }
//    }
//
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
import lk.ijse.gdse.project.dto.DriverDTO;
import lk.ijse.gdse.project.dto.tm.CustomerTM;
import lk.ijse.gdse.project.dto.tm.DriverTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DriverController implements Initializable {


    @FXML
    private Button btnDriverDetails;

    @FXML
    private Button btnSaveDriver;

    @FXML
    private Button btndeleteDriver;

    @FXML
    private Button btnupdateDriver;

    @FXML
    private TableColumn<DriverTM, String> colContact;

    @FXML
    private TableColumn<DriverTM, String> colDiverID;

    @FXML
    private TableColumn<DriverTM, String> colName;

    @FXML
    private Label lblDiverID;

    @FXML
    private TableView<DriverTM> tblDriver;

    @FXML
    private TextField txtDriverContact;

    @FXML
    private TextField txtDriverName;

    DriverModel driverModel = new DriverModel();

    @FXML
    void deleteDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverID = lblDiverID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = driverModel.deleteDriver(driverID);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Driver deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to delete driver").show();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDiverID.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load driver id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextDriverID();
        loadTableData();

        btnSaveDriver.setDisable(false);
        btnupdateDriver.setDisable(true);
        btndeleteDriver.setDisable(true);

        txtDriverName.setText("");
        txtDriverContact.setText("");

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDTO> driverDTOS = driverModel.getAllDrivers();
        ObservableList<DriverTM> driverTMS = FXCollections.observableArrayList();

        for (DriverDTO driverDTO : driverDTOS) {
            DriverTM driverTM = new DriverTM(
                    driverDTO.getDriver_id(),
                    driverDTO.getName(),
                    driverDTO.getContact());

            driverTMS.add(driverTM);
        }
        tblDriver.setItems(driverTMS);
    }

    private void loadNextDriverID() throws SQLException, ClassNotFoundException {
        String  driverID = driverModel.getNextDriverId();
        lblDiverID.setText(driverID);
    }

    @FXML
    void generateDriverdetailRepo(ActionEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {
        DriverTM driverTM = tblDriver.getSelectionModel().getSelectedItem();
        if (driverTM != null) {
            lblDiverID.setText(driverTM.getDriver_id());
            txtDriverName.setText(driverTM.getName());
            txtDriverContact.setText(driverTM.getContact());

            btnSaveDriver.setDisable(true);
            btnupdateDriver.setDisable(false);
            btndeleteDriver.setDisable(false);
        }
    }

    @FXML
    void saveDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = lblDiverID.getText();
        String driverName = txtDriverName.getText();
        String contact = txtDriverContact.getText();

        DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);

        boolean isSaved = driverModel.saveDriver(driverDTO);
        if (isSaved) {
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Driver saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save driver...!").show();
        }
    }

    @FXML
    void updateDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = lblDiverID.getText();
        String driverName = txtDriverName.getText();
        String contact = txtDriverContact.getText();

        DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);

        boolean isSaved = driverModel.updateDriver(driverDTO);
        if (isSaved) {
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Driver updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update driver...!").show();
        }
    }

}
