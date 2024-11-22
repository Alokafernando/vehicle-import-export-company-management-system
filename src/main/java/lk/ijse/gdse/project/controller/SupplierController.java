package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.SupplierModel;
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.SupplierDTO;
import lk.ijse.gdse.project.dto.SupplierDetailDTO;
import lk.ijse.gdse.project.dto.tm.CustomerTM;
import lk.ijse.gdse.project.dto.tm.SupplierTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class SupplierController implements Initializable {

    @FXML
    private Button btnDeleteSupplier;

    @FXML
    private Button btnSaveSupplier;

    @FXML
    private Button btnSupplierDetailRepo;

    @FXML
    private Button btnSupply;

    @FXML
    private Button btnUpdateSupplier;

    @FXML
    private TableColumn<SupplierTM, String> colCompanyName;

    @FXML
    private TableColumn<SupplierTM, String> colContact;

    @FXML
    private TableColumn<SupplierTM, String> colEmail;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierID;

    @FXML
    private Label lblSupplierID;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    private final SupplierModel supplierModel = new SupplierModel();

    @FXML
    void deleteSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {String supplierID = lblSupplierID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = supplierModel.deleteSupplier(supplierID);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Supplier deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete supplier...!").show();
            }
        }
    }

    @FXML
    void generateSupplierDetailsRepo(ActionEvent event)throws SQLException, ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Supplier.jrxml"
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
    void generateSupplyDetailsRepo(ActionEvent event) throws ClassNotFoundException{
        SupplierTM  supplierTM = tblSupplier.getSelectionModel().getSelectedItem();

        if (supplierTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/SupplyDetails.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Supplier_id", supplierTM.getSupplier_id());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
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
        SupplierTM staffTM = tblSupplier.getSelectionModel().getSelectedItem();
        if (staffTM != null) {
            lblSupplierID.setText(staffTM.getSupplier_id());
            txtCompanyName.setText(staffTM.getName());
            txtContact.setText(staffTM.getContact());
            txtEmail.setText(staffTM.getEmail());

            btnSaveSupplier.setDisable(true);
            btnUpdateSupplier.setDisable(false);
            btnDeleteSupplier.setDisable(false);
        }
    }

    @FXML
    void saveSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierID = lblSupplierID.getText();
        String companyName = txtCompanyName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = companyName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else{
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidContact && isValidEmail){
            SupplierDTO supplierDTO = new SupplierDTO(supplierID, companyName, contact, email
            );


            boolean isSaved = supplierModel.saveSupplier(supplierDTO);
            if(isSaved) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "supplier saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }

    }

    @FXML
    void updateSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierID = lblSupplierID.getText();
        String companyName = txtCompanyName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = companyName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else{
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidContact && isValidEmail){
            SupplierDTO supplierDTO = new SupplierDTO(supplierID, companyName, contact, email);
            boolean isUpdated = supplierModel.updateSupplier(supplierDTO);
            if(isUpdated){
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "supplier updated...!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed suppler update.").show();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellvalues();
        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        lblSupplierID.setText(supplierModel.getNextSupplierID());
        txtCompanyName.setText("");
        txtContact.setText("");
        txtEmail.setText("");

        loadTableData();
        loadNextSupplierID();

        btnSaveSupplier.setDisable(false);
        btnDeleteSupplier.setDisable(true);
        btnUpdateSupplier.setDisable(true);
    }

    private void loadNextSupplierID() throws SQLException, ClassNotFoundException {
        String supID = supplierModel.getNextSupplierID();
        lblSupplierID.setText(supID);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = supplierModel.getAllSuppliers();
        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

            for (SupplierDTO supplierDTO : supplierDTOS) {
                SupplierTM supplierTM = new SupplierTM(
                        supplierDTO.getSupplier_id(),
                        supplierDTO.getName(),
                        supplierDTO.getContact(),
                        supplierDTO.getEmail()
                );
                supplierTMS.add(supplierTM);
            }
            tblSupplier.setItems(supplierTMS);
    }

    private void setCellvalues() {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
