package lk.ijse.gdse.project.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnDriver;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnPart;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnStaff;

    @FXML
    private Button btnSupplier;

    @FXML
    private Button btnTax;

    @FXML
    private Button btnVehicle;

    @FXML
    private Button btntransport;

    @FXML
    private AnchorPane contentPanel;

    @FXML
    private AnchorPane slider;

    @FXML
    void OpenCustomerPanel(ActionEvent event) throws IOException {
        getFilePath("/view/CustomerView.fxml");
        setButtonColor(btnCustomer);
    }

    @FXML
    void openVehiclePanel(ActionEvent event) throws IOException {
        getFilePath("/view/VehicleView.fxml");
    }

    public void openExportCompanyPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/ExportCompanyView.fxml");
    }

    public void openImportCompanyPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/ImportComapnyView.fxml");
    }

    public void openStaffPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/StaffView.fxml");
    }

    public void openSupplierPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/Supplier.fxml");
    }

    public void openPartPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/Parts.fxml");
    }

    public void openReservationPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/ReservationView.fxml");
    }

    public void openPaymentPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/PaymentView.fxml");
    }

    public void openTaxPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/TaxView.fxml");
    }

    public void openTransportDetailPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/TransportView.fxml");
    }

    public void openDriverDetailPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/DriverView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.setTranslateX(-250);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-250);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-250);//-176
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });
    }

    private void getFilePath(String filePath) throws IOException {
        contentPanel.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(filePath));
        contentPanel.getChildren().add(pane);
    }

    private void setButtonColor(Button button) {
        btnExport.setStyle("");
        btnImport.setStyle("");
        btnStaff.setStyle("");
        btnCustomer.setStyle("-fx-background-color: #000000;");
        btnSupplier.setStyle("-fx-background-color: #000000;");
        btnPart.setStyle("-fx-background-color: #000000;");
    }
}



