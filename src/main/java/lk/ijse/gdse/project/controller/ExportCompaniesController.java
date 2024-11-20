package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.ExportCompanyModel;
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.ExportCompanyDTO;
import lk.ijse.gdse.project.dto.tm.ExportCompanyTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExportCompaniesController implements Initializable {

    @FXML
    private Button btnDeleteExport;

    @FXML
    private Button btnExportCompanyRepo;

    @FXML
    private Button btnExportVehicleRepo;

    @FXML
    private Button btnSaveExport;

    @FXML
    private Button btnUpdateExport;

    @FXML
    private TableColumn<ExportCompanyTM, String> colCOmpanyName;

    @FXML
    private TableColumn<ExportCompanyTM, String> colCompanyID;

    @FXML
    private TableColumn<ExportCompanyTM, String> colContact;

    @FXML
    private TableColumn<ExportCompanyTM, String> colCountry;

    @FXML
    private TableColumn<ExportCompanyTM, String> colEmail;

    @FXML
    private Label lblCompanyID;

    @FXML
    private TableView<ExportCompanyTM> tblExport;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    ExportCompanyModel exportCompanyModel = new ExportCompanyModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       colCompanyID.setCellValueFactory(new PropertyValueFactory<>("company_ID"));
       colCOmpanyName.setCellValueFactory(new PropertyValueFactory<>("company_Name"));
       colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
       colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
       colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

       try{
           refeshPage();
       } catch (Exception e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR, "Fail to load Export company id").show();
       }

    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextExportCompanyID();
        loadTableData();

        btnSaveExport.setDisable(false);
        btnUpdateExport.setDisable(true);
        btnDeleteExport.setDisable(true);

        txtCompanyName.setText("");
        txtContact.setText("");
        txtCountry.setText("");
        txtEmail.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ExportCompanyDTO> exportCompanyDTOS = exportCompanyModel.getAllExportCompany();
        ObservableList<ExportCompanyTM> exportCompanyTMS = FXCollections.observableArrayList();

        for (ExportCompanyDTO exportCompanyDTO : exportCompanyDTOS) {
            ExportCompanyTM exportCompanyTM = new ExportCompanyTM(
                    exportCompanyDTO.getCompany_ID(),
                    exportCompanyDTO.getCompany_Name(),
                    exportCompanyDTO.getCountry(),
                    exportCompanyDTO.getContact(),
                    exportCompanyDTO.getEmail()
            );
            exportCompanyTMS.add(exportCompanyTM);
        }
        tblExport.setItems(exportCompanyTMS);
    }

    private void loadNextExportCompanyID() throws SQLException, ClassNotFoundException {
        String exportCompanyID = exportCompanyModel.getNextCompanyID();
        lblCompanyID.setText(exportCompanyID);
    }

    @FXML
    void deleteExportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String companyID = lblCompanyID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = exportCompanyModel.deleteExportCompany(companyID);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Export company deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Export company").show();
            }
        }

    }

    @FXML
    void generateExportComapanyDetailsReport(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/ExportCompany.jrxml"
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
    void generateExportVehicleReport(ActionEvent event) {

    }


    @FXML
    void saveExportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String exportCompanyID = lblCompanyID.getText();
        String exportCompanyName = txtCompanyName.getText();
        String exportCompanyContact = txtContact.getText();
        String exportCompanyCountry = txtCountry.getText();
        String exportCompanyEmail = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String countryPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = exportCompanyName.matches(namePattern);
        boolean isValidCountry = exportCompanyCountry.matches(countryPattern);
        boolean isValidEmail = exportCompanyEmail.matches(emailPattern);
        boolean isValidPhone = exportCompanyContact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidCountry){
            txtCountry.setStyle(errorStyle);
        }else {
            txtCountry.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }
        if(!isValidPhone){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }

        if(isValidName && isValidCountry && isValidEmail && isValidPhone){
            ExportCompanyDTO exportCompanyDTO = new ExportCompanyDTO(
                    exportCompanyID,
                    exportCompanyName,
                    exportCompanyCountry,
                    exportCompanyContact,
                    exportCompanyEmail
            );
            boolean isSaved = exportCompanyModel.saveExportCompany(exportCompanyDTO);
            if (isSaved) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Export company saved..!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to export company save").show();
            }
        }

    }

    @FXML
    void updateExportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String exportCompanyID = lblCompanyID.getText();
        String exportCompanyName = txtCompanyName.getText();
        String exportCompanyContact = txtContact.getText();
        String exportCompanyCountry = txtCountry.getText();
        String exportCompanyEmail = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String countryPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = exportCompanyName.matches(namePattern);
        boolean isValidCountry = exportCompanyCountry.matches(countryPattern);
        boolean isValidEmail = exportCompanyEmail.matches(emailPattern);
        boolean isValidPhone = exportCompanyContact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidCountry){
            txtCountry.setStyle(errorStyle);
        }else {
            txtCountry.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }
        if(!isValidPhone){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }

        if(isValidName && isValidCountry && isValidEmail && isValidPhone){
            ExportCompanyDTO exportCompanyDTO = new ExportCompanyDTO(
                    exportCompanyID,
                    exportCompanyName,
                    exportCompanyCountry,
                    exportCompanyContact,
                    exportCompanyEmail
            );
            boolean isUpdated = exportCompanyModel.updateExportCompany(exportCompanyDTO);
            if (isUpdated) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Export company updated..!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to export company updated").show();
            }
        }

    }

    @FXML
    void onClckedTable(MouseEvent event) {
        ExportCompanyTM exportCompanyTM = tblExport.getSelectionModel().getSelectedItem();
        if (exportCompanyTM != null) {
            lblCompanyID.setText(exportCompanyTM.getCompany_ID());
            txtCompanyName.setText(exportCompanyTM.getCompany_Name());
            txtContact.setText(exportCompanyTM.getContact());
            txtCountry.setText(exportCompanyTM.getCountry());
            txtEmail.setText(exportCompanyTM.getEmail());

            btnSaveExport.setDisable(true);
            btnDeleteExport.setDisable(false);
            btnUpdateExport.setDisable(false);
        }

    }

}
