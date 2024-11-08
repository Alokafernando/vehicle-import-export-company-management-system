package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class PaymentController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnPayDetails;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbReservationID;

    @FXML
    private TableColumn<?, ?> colDeposite;

    @FXML
    private TableColumn<?, ?> colPayID;

    @FXML
    private TableColumn<?, ?> colPayMethod;

    @FXML
    private TableColumn<?, ?> colRemainAmount;

    @FXML
    private TableColumn<?, ?> colReserID;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private Label lblPayID;

    @FXML
    private ToggleGroup payMethod;

    @FXML
    private RadioButton rbCash;

    @FXML
    private RadioButton rbCheque;

    @FXML
    private TableView<?> tblReservation;

    @FXML
    private HBox txtAmount;

    @FXML
    private TextField txtDeposite;

    @FXML
    private TextField txtTotalAmount;

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
    void savePayment(ActionEvent event) {

    }

    @FXML
    void updatePayment(ActionEvent event) {

    }

}
