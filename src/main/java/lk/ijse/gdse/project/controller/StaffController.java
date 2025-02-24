package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.StaffModel;
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.StaffDTO;
import lk.ijse.gdse.project.dto.tm.StaffTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StaffController  implements Initializable {

    @FXML
    private Button btnDeletestaff;

    @FXML
    private Button btnSaveStaff;

    @FXML
    private Button btnStaffDetails;

    @FXML
    private Button btnUpdatestaff;

    @FXML
    private TableColumn<StaffTM, String> colAddress;

    @FXML
    private TableColumn<StaffTM, String> colName;

    @FXML
    private TableColumn<StaffTM, String> colRole;

    @FXML
    private TableColumn<StaffTM, Double> colSalary;

    @FXML
    private TableColumn<StaffTM, String> colStaffID;

    @FXML
    private Label lblStaffID;

    @FXML
    private TableView<StaffTM> tblStaff;

    @FXML
    private TextField txtStaffAddress;

    @FXML
    private TextField txtStaffName;

    @FXML
    private TextField txtStaffRole;

    @FXML
    private TextField txtStaffSalary;

    private final StaffModel staffModel = new StaffModel();

    @FXML
    void deletStaff(ActionEvent event) throws SQLException, ClassNotFoundException {
        String staffId = lblStaffID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = staffModel.deleteStaff(staffId);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Staff deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to delete staff").show();
            }
        }
    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void generateStaffDetailRepo(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Staff.jrxml"
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
    void onClickedtable(MouseEvent event) {
        StaffTM staff = tblStaff.getSelectionModel().getSelectedItem();
        if (staff != null) {
            lblStaffID.setText(staff.getStaff_id());
            txtStaffName.setText(staff.getStaff_name());
            txtStaffAddress.setText(staff.getAddress());
            txtStaffSalary.setText(String.valueOf(staff.getSalary()));
            txtStaffRole.setText(staff.getRole());

            btnSaveStaff.setDisable(true);
            btnUpdatestaff.setDisable(false);
            btnDeletestaff.setDisable(false);
        }

    }

    @FXML
    void saveStaff(ActionEvent event) throws SQLException, ClassNotFoundException {
        String staffID = lblStaffID.getText();
        String staffName = txtStaffName.getText();
        String staffAddress = txtStaffAddress.getText();
        String salaryInput = txtStaffSalary.getText();
        String role = txtStaffRole.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "[a-zA-Z0-9@.]+$";
        String salaryPattern = "^\\d+(\\.\\d{1,2})?$";
        String rolePattern = "^[A-Za-z ]+$";

        boolean isValidName = staffName.matches(namePattern);
        boolean isValidAddress = staffAddress.matches(addressPattern);
        boolean isValidSalary = salaryInput.matches(salaryPattern);
        boolean isValidRole = role.matches(rolePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtStaffName.setStyle(errorStyle);
        }else {
            txtStaffName.setStyle(style);
        }
        if(!isValidAddress){
            txtStaffAddress.setStyle(errorStyle);
        }else{
            txtStaffAddress.setStyle(style);
        }
        if(!isValidRole){
            txtStaffRole.setStyle(errorStyle);
        }else {
            txtStaffRole.setStyle(style);
        }
        Double salary = null;
        if (isValidSalary) {
            salary = Double.valueOf(salaryInput);
            txtStaffSalary.setStyle(style);
        } else {
            txtStaffSalary.setStyle(errorStyle);
            new Alert(Alert.AlertType.ERROR, "Invalid salary. Please enter a valid numeric value.").show();
        }

        if (isValidName && isValidAddress && isValidRole && isValidSalary) {
            StaffDTO staffDTO = new StaffDTO(
                    staffID,
                    staffName,
                    staffAddress,
                    salary,
                    role
            );

            boolean isSaved = staffModel.saveStaff(staffDTO);
            if (isSaved) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Staff saved...!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save staff...!").show();
            }

        }

    }

    @FXML
    void updateStaff(ActionEvent event) throws SQLException, ClassNotFoundException {
        String staffID = lblStaffID.getText();
        String staffName = txtStaffName.getText();
        String staffAddress = txtStaffAddress.getText();
        String salaryInput = txtStaffSalary.getText();
        String role = txtStaffRole.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "[a-zA-Z0-9@.]+$";
        String salaryPattern = "^\\d+(\\.\\d{1,2})?$";
        String rolePattern = "^[A-Za-z ]+$";

        boolean isValidName = staffName.matches(namePattern);
        boolean isValidAddress = staffAddress.matches(addressPattern);
        boolean isValidSalary = salaryInput.matches(salaryPattern);
        boolean isValidRole = role.matches(rolePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtStaffName.setStyle(errorStyle);
        }else {
            txtStaffName.setStyle(style);
        }
        if(!isValidAddress){
            txtStaffAddress.setStyle(errorStyle);
        }else{
            txtStaffAddress.setStyle(style);
        }
        if(!isValidRole){
            txtStaffRole.setStyle(errorStyle);
        }else {
            txtStaffRole.setStyle(style);
        }
        Double salary = null;
        if (isValidSalary) {
            salary = Double.valueOf(salaryInput);
            txtStaffSalary.setStyle(style);
        } else {
            txtStaffSalary.setStyle(errorStyle);
            new Alert(Alert.AlertType.ERROR, "Invalid salary. Please enter a valid numeric value.").show();
        }

        if (isValidName && isValidAddress && isValidRole && isValidSalary) {
            StaffDTO staffDTO = new StaffDTO(
                    staffID,
                    staffName,
                    staffAddress,
                    salary,
                    role
            );

            boolean isUpdated = staffModel.updateStaff(staffDTO);
            if (isUpdated) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Staff updated...!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to update staff...!").show();
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
            new Alert(Alert.AlertType.ERROR, "Fail to load staff id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextStaffID();
        loadTableData();

        btnSaveStaff.setDisable(false);
        btnUpdatestaff.setDisable(true);
        btnDeletestaff.setDisable(true);

        txtStaffName.setText("");
        txtStaffSalary.setText("");
        txtStaffRole.setText("");
        txtStaffAddress.setText("");

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<StaffDTO> staffDTOS = staffModel.getAllStaff();
        ObservableList<StaffTM> staffTMS = FXCollections.observableArrayList();

            for (StaffDTO staffDTO : staffDTOS) {
                StaffTM staffTM = new StaffTM(
                        staffDTO.getStaff_id(),
                        staffDTO.getStaff_name(),
                        staffDTO.getAddress(),
                        staffDTO.getSalary(),
                        staffDTO.getRole()
                );
                staffTMS.add(staffTM);
            }
    tblStaff.setItems(staffTMS);
    }

    private void loadNextStaffID() throws SQLException, ClassNotFoundException {
        String staffID = staffModel.getNextStaffId();
        lblStaffID.setText(staffID);
    }

    private void setCellValues() {
        colStaffID.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("staff_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }


}
