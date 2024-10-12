package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.CustomerModel;
import lk.ijse.gdse.project.dto.CustomerDTO;
import lk.ijse.gdse.project.dto.tm.CustomerTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

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

    CustomerModel customerModel = new CustomerModel();

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

        CustomerDTO customerDTO = new CustomerDTO(customerID, name, address, contact, email);

        boolean isSaved = customerModel.saveCustomer(customerDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }

    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerID = lblCustomerID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        CustomerDTO customerDTO = new CustomerDTO(customerID, name, address, contact, email);
        boolean isUpdated = customerModel.updateCustomer(customerDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
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
}
