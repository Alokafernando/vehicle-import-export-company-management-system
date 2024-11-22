package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.project.Model.CustomerModel;
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.CustomerDTO;
import lk.ijse.gdse.project.dto.tm.CustomerTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CustomerController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btncheckVehicle;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colContact;

    @FXML
    private TableColumn<CustomerTM, String> colCustID;

    @FXML
    private TableColumn<CustomerTM, String> colEmail;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private Label lblCustomerID;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    private final CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnCheckVehicleOnAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckVehicle.fxml"));
            AnchorPane pane = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Check Vehicle");
            Scene scene = new Scene(pane);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = lblCustomerID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = customerModel.deleteCustomer(customerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            lblCustomerID.setText(customerTM.getCust_ID());
            txtName.setText(customerTM.getName());
            txtAddress.setText(customerTM.getAddress());
            txtContact.setText(customerTM.getContact());
            txtEmail.setText(customerTM.getEmail());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void saveCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerID = lblCustomerID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "[a-zA-Z0-9@.]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtName.setStyle(errorStyle);
        }else{
            txtName.setStyle(style);
        }
        if(!isValidAddress){
            txtAddress.setStyle(errorStyle);
        }else{
            txtAddress.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidAddress && isValidContact && isValidEmail){
            CustomerDTO customerDTO = new CustomerDTO(customerID, name, address, contact, email);

            boolean isSaved = customerModel.saveCustomer(customerDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid input! Please ensure all fields are correctly filled.").show();
        }
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerID = lblCustomerID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "[a-zA-Z0-9@.]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtName.setStyle(errorStyle);
        }else{
            txtName.setStyle(style);
        }
        if(!isValidAddress){
            txtAddress.setStyle(errorStyle);
        }else{
            txtAddress.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidAddress && isValidContact && isValidEmail){
            CustomerDTO customerDTO = new CustomerDTO(customerID, name, address, contact, email);

            boolean isUpdated = customerModel.updateCustomer(customerDTO);
            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid input! Please ensure all fields are correctly filled.").show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("cust_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = customerModel.getAllCustomers();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCust_ID(),
                    customerDTO.getName(),
                    customerDTO.getAddress(),
                    customerDTO.getContact(),
                    customerDTO.getEmail()
            );
            customerTMS.add(customerTM);
        }
        tblCustomer.setItems(customerTMS);
    }

    private void loadNextCustomerId() throws SQLException, ClassNotFoundException {
        String nextCustomerID = customerModel.getNextCustomerId();
        lblCustomerID.setText(nextCustomerID);
    }

    public void generateCustomerReservReport(ActionEvent actionEvent) throws ClassNotFoundException {

        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();

        if (customerTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/CustReservationRepo.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("p_Cust_ID", customerTM.getCust_ID());

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

    public void generateCistomerReport(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Customer.jrxml"
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
}
