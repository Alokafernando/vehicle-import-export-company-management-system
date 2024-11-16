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
        setButtonColor(btnVehicle);
    }

    public void openExportCompanyPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/ExportCompanyView.fxml");
        setButtonColor(btnExport);
    }

    public void openImportCompanyPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/ImportComapnyView.fxml");
        setButtonColor(btnImport);
    }

    public void openStaffPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/StaffView.fxml");
        setButtonColor(btnStaff);
    }

    public void openSupplierPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/Supplier.fxml");
        setButtonColor(btnSupplier);
    }

    public void openPartPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/Parts.fxml");
        setButtonColor(btnPart);
    }

    public void openReservationPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/ReservationView.fxml");
        setButtonColor(btnReservation);
    }

    public void openPaymentPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/PaymentView.fxml");
        setButtonColor(btnPayment);
    }

    public void openTaxPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/TaxView.fxml");
        setButtonColor(btnTax);
    }

    public void openTransportDetailPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/TransportView.fxml");
        setButtonColor(btntransport);
    }

    public void openDriverDetailPanel(ActionEvent actionEvent) throws IOException {
        getFilePath("/view/DriverView.fxml");
        setButtonColor(btnDriver);
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

    private void setButtonColor(Button selectedButton) {
        resetButtonStyles();

        String selectedColor = "-fx-background-color: transparent; -fx-border-color: #000; -fx-border-radius: 25; -fx-text-fill: #000;";
        selectedButton.setStyle(selectedColor);
    }

    private void resetButtonStyles() {
        String defaultStyle = "-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-radius: 25; -fx-text-fill: #fff;";

        btnCustomer.setStyle(defaultStyle);
        btnDriver.setStyle(defaultStyle);
        btnExport.setStyle(defaultStyle);
        btnImport.setStyle(defaultStyle);
        btnPart.setStyle(defaultStyle);
        btnPayment.setStyle(defaultStyle);
        btnReservation.setStyle(defaultStyle);
        btnStaff.setStyle(defaultStyle);
        btnSupplier.setStyle(defaultStyle);
        btnTax.setStyle(defaultStyle);
        btnVehicle.setStyle(defaultStyle);
        btntransport.setStyle(defaultStyle);
    }

}


