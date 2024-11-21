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
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.DriverDTO;
import lk.ijse.gdse.project.dto.tm.CustomerTM;
import lk.ijse.gdse.project.dto.tm.DriverTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
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
    void generateDriverdetailRepo(ActionEvent event) throws ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Driver.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }

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

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = driverName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidName){
            txtDriverName.setStyle(errorStyle);
        }else{
            txtDriverName.setStyle(style);
        }
        if (!isValidContact){
            txtDriverContact.setStyle(errorStyle);
        }else {
            txtDriverContact.setStyle(style);
        }

        if (isValidName && isValidContact){
            DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);

            boolean isSaved = driverModel.saveDriver(driverDTO);
            if (isSaved) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Driver saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save driver...!").show();
            }
        }

    }

    @FXML
    void updateDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = lblDiverID.getText();
        String driverName = txtDriverName.getText();
        String contact = txtDriverContact.getText();

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = driverName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidName){
            txtDriverName.setStyle(errorStyle);
        }else{
            txtDriverName.setStyle(style);
        }
        if (!isValidContact){
            txtDriverContact.setStyle(errorStyle);
        }else {
            txtDriverContact.setStyle(style);
        }

        if (isValidName && isValidContact){
            DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);

            boolean isUpdated = driverModel.updateDriver(driverDTO);
            if (isUpdated) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Driver updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update driver...!").show();
            }
        }

    }

}
