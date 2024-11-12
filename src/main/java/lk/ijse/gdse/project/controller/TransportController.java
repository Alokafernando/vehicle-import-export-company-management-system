package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class TransportController {

    @FXML
    private ToggleGroup TransportType1;

    @FXML
    private Button btnDeleteTransport;

    @FXML
    private Button btnSaveTransport;

    @FXML
    private Button btnTransportDetail;

    @FXML
    private Button btnUpdateTransport;

    @FXML
    private ComboBox<?> cmdDriverID;

    @FXML
    private TableColumn<?, ?> colDriverID;

    @FXML
    private TableColumn<?, ?> colEndDate;

    @FXML
    private TableColumn<?, ?> colStartDate;

    @FXML
    private TableColumn<?, ?> colTransportID;

    @FXML
    private TableColumn<?, ?> colTransportType;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private Label lblTransportID;

    @FXML
    private RadioButton rbExport;

    @FXML
    private RadioButton rbImport;

    @FXML
    private TableView<?> tblTransport;

    @FXML
    void deleteTransport(ActionEvent event) {

    }

    @FXML
    void generateTransportRepo(ActionEvent event) {

    }

    @FXML
    void saveTransport(ActionEvent event) {

    }

    @FXML
    void updateTranport(ActionEvent event) {

    }

}
