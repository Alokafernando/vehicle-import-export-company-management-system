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
import lk.ijse.gdse.project.Model.ReservationModel;
import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.ReservationDTO;
import lk.ijse.gdse.project.dto.tm.CustomerTM;
import lk.ijse.gdse.project.dto.tm.ReservationTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private Button btnDeleteResetvation;

    @FXML
    private Button btnResercDetails;

    @FXML
    private Button btnSaveReservation;

    @FXML
    private Button btnUpdateReservation;

    @FXML
    private ComboBox<String> cmdCustID;

    @FXML
    private TableColumn<ReservationTM, String> colCustID;

    @FXML
    private TableColumn<ReservationTM, String> colReservDate;

    @FXML
    private TableColumn<ReservationTM, String> colReserveID;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblResreveID;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TextField txtReservDate;

    private final ReservationModel reservationModel = new ReservationModel();
    private final CustomerModel customerModel = new CustomerModel();

    @FXML
    void clickedOnTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        ReservationTM reservationTM = tblReservation.getSelectionModel().getSelectedItem();
        if (reservationTM != null) {
            cmdCustID.getItems().clear();
            List<String> customerIds = customerModel.getAllCustomerIds();
            if (customerIds != null && !customerIds.isEmpty()) {
                cmdCustID.getItems().addAll(customerIds);
                cmdCustID.setValue(reservationTM.getCust_id());
            }

            lblResreveID.setText(reservationTM.getReservation_id());
            txtReservDate.setText(reservationTM.getReservation_date());

            btnSaveReservation.setDisable(true);
            btnDeleteResetvation.setDisable(false);
            btnUpdateReservation.setDisable(false);
        }
    }

    @FXML
    void cmbCustIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedCustId = cmdCustID.getSelectionModel().getSelectedItem();
        CustomerTM customerTM = customerModel.findbyId(selectedCustId);
        if (customerTM != null) {
            lblCustName.setText(customerTM.getName());
        }

    }

    @FXML
    void deleteReservation(ActionEvent event) throws SQLException, ClassNotFoundException {
        String reservationID = lblResreveID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = reservationModel.deleteReservation(reservationID);
            if (isDeleted) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Reservation deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Reservation").show();
            }
        }


    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void generateReservDetailReport(ActionEvent event) throws ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Reservation.jrxml"
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
    void saveReservation(ActionEvent event) throws SQLException, ClassNotFoundException {
        String custID = cmdCustID.getValue();
        String reservationID = lblResreveID.getText();
        String reservationDate = txtReservDate.getText();

        if (custID == null || custID.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer ID before saving the reservation.").show();
            return;
        }

        String datePattern = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";

        boolean isValidDate = reservationDate.matches(datePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidDate){
            txtReservDate.setStyle(errorStyle);
        }else {
            txtReservDate.setStyle(style);

            ReservationDTO reservationDTO = new ReservationDTO(
                    custID, reservationID, reservationDate
            );


            boolean isSave = reservationModel.saveReservation(reservationDTO);
            if (isSave) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Reservation saved..!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Reservation not saved..!").show();
            }
        }

    }

    @FXML
    void updateReservation(ActionEvent event) throws SQLException, ClassNotFoundException {
        String custID = cmdCustID.getValue();
        String reservationID = lblResreveID.getText();
        String reservationDate = txtReservDate.getText();

        if (custID == null || custID.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer ID before saving the reservation.").show();
            return;
        }

        String datePattern = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";

        boolean isValidDate = reservationDate.matches(datePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidDate){
            txtReservDate.setStyle(errorStyle);
        }else {
            txtReservDate.setStyle(style);

            ReservationDTO reservationDTO = new ReservationDTO(
                    custID, reservationID, reservationDate
            );

            boolean isUpdated = reservationModel.updateReservation(reservationDTO);
            if (isUpdated) {
                refeshPage();
                new Alert(Alert.AlertType.INFORMATION, "Reservation updated..!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Reservation not updated..!").show();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();
        try {
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Reservation id").show();
        }
    }

    private void setCellValues() {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colReserveID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        colReservDate.setCellValueFactory(new PropertyValueFactory<>("reservation_date"));

    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        lblResreveID.setText(reservationModel.getNextReservationID());
        txtReservDate.setText(LocalDate.now().toString());
        cmdCustID.getSelectionModel().clearSelection();
        txtReservDate.clear();

        btnSaveReservation.setDisable(false);
        btnUpdateReservation.setDisable(true);
        btnDeleteResetvation.setDisable(true);
        loadCustomerIds();
        loadTableData();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> reservationDTOS = reservationModel.getAllReservations();
        ObservableList<ReservationTM> reservationTMS = FXCollections.observableArrayList();

        for (ReservationDTO reservationDTO : reservationDTOS) {
            ReservationTM reservationTM = new ReservationTM(
                    reservationDTO.getCust_id(),
                    reservationDTO.getReservation_id(),
                    reservationDTO.getReservation_date()
            );
            reservationTMS.add(reservationTM);
        }
        tblReservation.setItems(reservationTMS);

    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerModel.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmdCustID.setItems(observableList);
    }


    private  void loadNextReservationID() throws SQLException, ClassNotFoundException {
        String reseID = reservationModel.getNextReservationID();
        lblResreveID.setText(reseID);
    }
}
