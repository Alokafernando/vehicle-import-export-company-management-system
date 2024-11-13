package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.PaymentModel;
import lk.ijse.gdse.project.Model.ReservationModel;
import lk.ijse.gdse.project.dto.PaymentDTO;
import lk.ijse.gdse.project.dto.tm.PaymentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnPayDetails;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbReservationID;

    @FXML
    private TableColumn<PaymentTM, Double> colDeposite;

    @FXML
    private TableColumn<PaymentTM, String> colPayID;

    @FXML
    private TableColumn<PaymentTM, String> colPayMethod;

    @FXML
    private TableColumn<PaymentTM, Double> colRemainAmount;

    @FXML
    private TableColumn<PaymentTM, String> colReserID;

    @FXML
    private TableColumn<PaymentTM, Double> colTotalAmount;

    @FXML
    private Label lblPayID;

    @FXML
    private ToggleGroup payMethod;

    @FXML
    private RadioButton rbCash;

    @FXML
    private RadioButton rbCheque;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtDeposite;


    @FXML
    private TextField txtTotalAmount;

    private final PaymentModel paymentModel = new PaymentModel();
    private final ReservationModel reservationModel = new ReservationModel();

    @FXML
    void deletePayment(ActionEvent event) {

    }

    @FXML
    void generatePaymentRepo(ActionEvent event) {

    }

    @FXML
    void generateReport(MouseEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        PaymentTM paymentTM = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTM != null) {
            cmbReservationID.getItems().clear();
            List<String> reservationIDS = reservationModel.getAllReservationIDS();
            if(reservationIDS != null && !reservationIDS.isEmpty()) {
                cmbReservationID.getItems().addAll(reservationIDS);
                cmbReservationID.setValue(paymentTM.getReservation_id());
            }
        }

        lblPayID.setText(paymentTM.getPay_id());
        txtDeposite.setText(String.valueOf(paymentTM.getDeposite()));
        txtTotalAmount.setText(String.valueOf(paymentTM.getAmount()));


        if ("cash".equalsIgnoreCase(paymentTM.getPayment_method())) {
                rbCash.setSelected(true);
                rbCheque.setSelected(false);
            } else if ("cheque".equalsIgnoreCase(paymentTM.getPayment_method())) {
                rbCash.setSelected(false);
                rbCheque.setSelected(true);
            }

        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    @FXML
    void savePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        String reservationId = cmbReservationID.getValue();
        String payId = lblPayID.getText();

        if (reservationId == null || reservationId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a reservation ID.").show();
            return;
        }

        double deposit, totalAmount, remainingAmount;
        try {
            deposit = Double.parseDouble(txtDeposite.getText());
            totalAmount = Double.parseDouble(txtTotalAmount.getText());
            remainingAmount = totalAmount - deposit;
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid amounts for deposit and total amount.").show();
            return;
        }

        String payMethod;
        if (rbCash.isSelected()) {
            payMethod = "cash";
        } else if (rbCheque.isSelected()) {
            payMethod = "cheque";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method.").show();
            return;
        }

        PaymentDTO paymentDTO = new PaymentDTO(
                reservationId,
                payId,
                payMethod,
                deposit,
                totalAmount,
                remainingAmount
        );

        boolean isSaved = paymentModel.savePayment(paymentDTO);
        if (isSaved) {
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment saved..!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save payment.").show();
        }
    }

    @FXML
    void updatePayment(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();
        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load payment id").show();
        }

    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
//        lblPayID.setText(paymentModel.getNextPayId());
//        payMethod.selectToggle(null);
//        txtDeposite.clear();
//        txtTotalAmount.clear();
//        cmbReservationID.getSelectionModel().clearSelection();
//
//        btnSave.setDisable(false);
//        btnUpdate.setDisable(true);
//        btnDelete.setDisable(true);
//
//        loadReservationIDS();
//        loadTableData();
        lblPayID.setText(paymentModel.getNextPayId());
        payMethod.selectToggle(null);
        txtDeposite.clear();
        txtTotalAmount.clear();
        cmbReservationID.getSelectionModel().clearSelection();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        loadReservationIDS();
        loadTableData();
    }

//    private void loadTableData() throws SQLException, ClassNotFoundException {
//        ArrayList<PaymentDTO> paymentDTOS = paymentModel.getAllPayments();
//        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();
//
//        for (PaymentDTO paymentDTO : paymentDTOS) {
//            double remainAmount = paymentDTO.getAmount() - paymentDTO.getDeposite();
//
//            PaymentTM paymentTM = new PaymentTM(
//                    paymentDTO.getReservation_id(),
//                    paymentDTO.getPay_id(),
//                    paymentDTO.getPayment_method(),
//                    paymentDTO.getDeposite(),
//                    paymentDTO.getAmount(),
//                    remainAmount
//            );
//
//            paymentTMS.add(paymentTM);
//        }
//
//        tblPayment.setItems(paymentTMS);
//        tblPayment.refresh();
//    }
private void loadTableData() throws SQLException, ClassNotFoundException {
    ArrayList<PaymentDTO> paymentDTOS = paymentModel.getAllPayments();
    ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

    for (PaymentDTO paymentDTO : paymentDTOS) {
        double remainAmount = paymentDTO.getAmount() - paymentDTO.getDeposite();

        PaymentTM paymentTM = new PaymentTM(
                paymentDTO.getReservation_id(),
                paymentDTO.getPay_id(),
                paymentDTO.getPayment_method(),
                paymentDTO.getDeposite(),
                paymentDTO.getAmount(),
                remainAmount // setting remainingAmount here
        );

        paymentTMS.add(paymentTM);
    }

    tblPayment.setItems(paymentTMS);
    tblPayment.refresh();
}


    private void loadReservationIDS() throws SQLException, ClassNotFoundException {
        ArrayList<String> reserIDS = reservationModel.getAllReservationIDS();
        ObservableList<String> observableList = FXCollections.observableArrayList(reserIDS);
        cmbReservationID.setItems(observableList);
    }

//    private void setCellValues() {
//        colReserID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
//        colPayID.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
//        colPayMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
//        colDeposite.setCellValueFactory(new PropertyValueFactory<>("deposite"));
//        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//        colRemainAmount.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
//
//    }
private void setCellValues() {
    colReserID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
    colPayID.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
    colPayMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
    colDeposite.setCellValueFactory(new PropertyValueFactory<>("deposite"));
    colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    // For remainingAmount, use PropertyValueFactory with correct property name
    colRemainAmount.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
}


    private void loadNextpayID() throws SQLException, ClassNotFoundException {
        String nextPaymentID = paymentModel.getNextPayId();
        lblPayID.setText(nextPaymentID);
    }
}
