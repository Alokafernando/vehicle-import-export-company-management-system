/*package lk.ijse.gdse.project.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private AnchorPane contentPanel;

    @FXML
    private ImageView imageview;

    @FXML
    private AnchorPane manuPage;

    @FXML
    private AnchorPane slider;

    @FXML
    void openCustomerForm(ActionEvent event) throws IOException {
        getFilePath("/view/Customer.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {  // Change ActionEvent to Event
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
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
}

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
    private Button btnTesting;

    @FXML
    private AnchorPane contentPanel;

    @FXML
    private AnchorPane manuPage;

    @FXML
    private AnchorPane slider;

    @FXML
    void OpenCustomerPanel(ActionEvent event) throws IOException {
        //getFilePath("/view/Customer.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {  // Change ActionEvent to Event
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
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
}*/

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
    private Button btnTesting;

    @FXML
    private AnchorPane contentPanel;

    @FXML
    private AnchorPane manuPage;

    @FXML
    private AnchorPane slider;

    @FXML
    void OpenCustomerPanel(ActionEvent event) throws IOException {
        getFilePath("/view/Customer.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
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
}


