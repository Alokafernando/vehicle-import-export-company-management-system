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
import lk.ijse.gdse.project.dto.ImportCompanyDTO;
import lk.ijse.gdse.project.dto.tm.ImportCompantTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

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

    ImportCompanyModel importCompanyModel = new ImportCompanyModel();

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

    }

    @FXML
    void generateImportVehicleReport(ActionEvent event) {

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

    @FXML
    void updateImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String importCompanyID = lblCompanyID.getText();
                String importCompanyName = txtCompanyName.getText();
                String importCompanyContact = txtContact.getText();
                String importCompanyCountry = txtCountry.getText();
                String importCompanyEmail = txtEmail.getText();

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
