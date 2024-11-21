package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.ImportCompanyModel;
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.ImportCompanyDTO;
import lk.ijse.gdse.project.dto.tm.ImportCompantTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ImportCompanyController implements Initializable {

    @FXML
    private Button btnDeleteImport;

    @FXML
    private Button btnImportCompanyRepo;

    @FXML
    private Button btnImportVehicleRepo;

    @FXML
    private Button btnSaveImport;

    @FXML
    private Button btnUpdateImport;

    @FXML
    private TableColumn<ImportCompantTM, String> colCompanyID;

    @FXML
    private TableColumn<ImportCompantTM, String> colCompanyName;

    @FXML
    private TableColumn<ImportCompantTM, String> colContact;

    @FXML
    private TableColumn<ImportCompantTM, String> colCountry;

    @FXML
    private TableColumn<ImportCompantTM, String> colEmail;

    @FXML
    private Label lblCompanyID;

    @FXML
    private TableView<ImportCompantTM> tblImport;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    private final ImportCompanyModel importCompanyModel = new ImportCompanyModel();

    @FXML
    void deleteImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String companyID = lblCompanyID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = importCompanyModel.deleteImportCompany(companyID);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Import Company deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Import Company").show();
            }
        }
    }

    @FXML
    void generateImportComapnyDetailsReport(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/ImportCompanyRepo.jrxml"
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
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }

    }

    @FXML
    void generateImportVehicleReport(ActionEvent event)throws ClassNotFoundException {
        ImportCompantTM importCompantTM = tblImport.getSelectionModel().getSelectedItem();

        if (importCompantTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/importVehicle.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Vehicle_ID", importCompantTM.getCompany_ID());

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
        ImportCompantTM importCompantTM = tblImport.getSelectionModel().getSelectedItem();
                if (importCompantTM != null) {
                    lblCompanyID.setText(importCompantTM.getCompany_ID());
                    txtCompanyName.setText(importCompantTM.getCompany_Name());
                    txtContact.setText(importCompantTM.getContact());
                    txtCountry.setText(importCompantTM.getCountry());
                    txtEmail.setText(importCompantTM.getEmail());

                    btnSaveImport.setDisable(true);
                    btnDeleteImport.setDisable(false);
                    btnUpdateImport.setDisable(false);

                }

    }

    @FXML
    void saveImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
                String importCompanyID = lblCompanyID.getText();
                String importCompanyName = txtCompanyName.getText();
                String importCompanyContact = txtContact.getText();
                String importCompanyCountry = txtCountry.getText();
                String importCompanyEmail = txtEmail.getText();

            String namePattern = "^[A-Za-z ]+$";
            String countryPattern = "^[A-Za-z ]+$";
            String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = importCompanyName.matches(namePattern);
            boolean isValidContact = importCompanyContact.matches(countryPattern);
            boolean isValidEmail = importCompanyEmail.matches(emailPattern);
            boolean isValidPhone = importCompanyContact.matches(phonePattern);
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
            }else {
                txtEmail.setStyle(style);
            }
            if(!isValidPhone){
                txtContact.setStyle(errorStyle);
            }else {
                txtContact.setStyle(style);
            }

            if(isValidName && isValidContact && isValidEmail && isValidPhone) {
                ImportCompanyDTO importCompanyDTO = new ImportCompanyDTO(
                        importCompanyID,
                        importCompanyName,
                        importCompanyCountry,
                        importCompanyContact,
                        importCompanyEmail
                );

                boolean isSaved = importCompanyModel.saveImportCompany(importCompanyDTO);
                if(isSaved){
                    refeshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Import company saved..!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Fail to import company save").show();
                }
            }

    }

    @FXML
    void updateImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String importCompanyID = lblCompanyID.getText();
                String importCompanyName = txtCompanyName.getText();
                String importCompanyContact = txtContact.getText();
                String importCompanyCountry = txtCountry.getText();
                String importCompanyEmail = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String countryPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = importCompanyName.matches(namePattern);
        boolean isValidContact = importCompanyContact.matches(countryPattern);
        boolean isValidEmail = importCompanyEmail.matches(emailPattern);
        boolean isValidPhone = importCompanyContact.matches(phonePattern);
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
        }else {
            txtEmail.setStyle(style);
        }
        if(!isValidPhone){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }

        if(isValidName && isValidContact && isValidEmail && isValidPhone) {
            ImportCompanyDTO importCompanyDTO = new ImportCompanyDTO(
                    importCompanyID,
                    importCompanyName,
                    importCompanyCountry,
                    importCompanyContact,
                    importCompanyEmail
            );

            boolean isUpdated = importCompanyModel.updateImportCompany(importCompanyDTO);
            if(isUpdated){
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Import company updated..!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to import company update").show();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCompanyID.setCellValueFactory(new PropertyValueFactory<>("company_ID"));
                colCompanyName.setCellValueFactory(new PropertyValueFactory<>("company_Name"));
                colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


                try{
                    refeshPage();
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Fail to load Import company id").show();
                }


    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextImportCompanyID();
        loadTableDat();

        btnSaveImport.setDisable(false);
        btnDeleteImport.setDisable(true);
        btnUpdateImport.setDisable(true);

        txtCompanyName.setText("");
        txtContact.setText("");
        txtCountry.setText("");
        txtEmail.setText("");

    }

    private void loadTableDat() throws SQLException, ClassNotFoundException {
        ArrayList<ImportCompanyDTO> importCompanyDTOS = importCompanyModel.getAllImportCompanies();
        ObservableList<ImportCompantTM> importCompanyTMS = FXCollections.observableArrayList();

        for (ImportCompanyDTO importCompanyDTO : importCompanyDTOS) {
            ImportCompantTM importCompanyTM = new ImportCompantTM(
                    importCompanyDTO.getCompany_ID(),
                    importCompanyDTO.getCompany_Name(),
                    importCompanyDTO.getCountry(),
                    importCompanyDTO.getContact(),
                    importCompanyDTO.getEmail()
            );
            importCompanyTMS.add(importCompanyTM);
        }
        tblImport.setItems(importCompanyTMS);

    }


    private void loadNextImportCompanyID() throws SQLException, ClassNotFoundException {
        String importCompanyID = importCompanyModel.getNextImportComapnyID();
                lblCompanyID.setText(importCompanyID);
    }

}
