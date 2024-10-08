/*package lk.ijse.gdse.project.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  {
    private final String userName = "aloka";
    private final String password = "123";

    @FXML
    private Button btnlogin;

    @FXML
    private AnchorPane signupPage;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void checkPassword(ActionEvent event) {
    }

    @FXML
    void checkUserName(ActionEvent event) {
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        String u = txtUserName.getText();
        String p = txtPassword.getText();

           if (!(userName.equals(u))){
               txtUserName.clear();
               txtUserName.setStyle("-fx-text-box-border: red; -fx-prompt-text-fill: #ff5e57;");
               txtUserName.setPromptText("wrong username");
           }else if (!(p.equals(password))){
               txtPassword.clear();
               txtPassword.setStyle("-fx-text-box-border: red; -fx-prompt-text-fill: #ff5e57;");
               txtPassword.setPromptText("wrong password");
           } else {
               signupPage.getChildren().clear();
               AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
               signupPage.getChildren().add(load);
           }
       }

}*/
/*
package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginController {
    private final String password = "aa";
    private  final String username = "123";

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblCheckPassword;

    @FXML
    private Label lblCheckUsername;

    @FXML
    private AnchorPane loginPage;;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUseName;

    @FXML
    void login(ActionEvent event) throws IOException {
        String u = txtUseName.getText();
        String p = txtPassword.getText();


        if (u == null || u.trim().isEmpty()) {
            lblCheckUsername.setText("Username cannot be empty.");
            return; // Exit the method if the username is invalid
        }

        if (p == null || p.trim().isEmpty()) {
            lblCheckUsername.setText("Password cannot be empty.");
            return; // Exit the method if the password is invalid
        }
        /*AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) txtUseName.getScene().getWindow();
        stage.setScene(scene);
     //   stage.centerOnScreen();
        stage.setTitle("Dashboard Form");


    }

}*/

package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private  final String username = "aa";
    private final String password = "123";


    @FXML
    private Button btnLogin;

    @FXML
    private Label lblCheckPassword;

    @FXML
    private Label lblCheckUsername;

    @FXML
    private AnchorPane loginPage;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void login(ActionEvent event) throws IOException {
        String u = txtUserName.getText();
        String p = txtPassword.getText();

        if (u.isEmpty() || p.isEmpty() || !username.equals(u) || !password.equals(p)) {
            if (u.isEmpty()) {
                txtUserName.setStyle("-fx-border-color: #ff5e57; -fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
                lblCheckUsername.setText("required");
                lblCheckUsername.setStyle("-fx-text-fill: #ff5e57;");
            } else {
                lblCheckUsername.setText("");
                txtUserName.setStyle("");
                if (!username.equals(u)) {
                    lblCheckUsername.setText("wrong username");
                    lblCheckUsername.setStyle("-fx-text-fill: #ff5e57;");
                    txtUserName.setStyle("-fx-border-color: #ff5e57; -fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
                } else {
                    lblCheckUsername.setStyle("");
                    txtUserName.setStyle("");
                }
            }
            if (p.isEmpty()) {
                lblCheckPassword.setText("required");
                lblCheckPassword.setStyle("-fx-text-fill: #ff5e57;");
                txtPassword.setStyle("-fx-border-color: #ff5e57; -fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent; ");
            } else {
                lblCheckPassword.setText("");
                txtPassword.setStyle("");
                if (!password.equals(p)) {
                    lblCheckPassword.setText("wrong password");
                    lblCheckPassword.setStyle("-fx-text-fill: #ff5e57;");
                    txtPassword.setStyle("-fx-border-color: #ff5e57; -fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
                } else {
                    lblCheckPassword.setStyle("");
                    txtPassword.setStyle("");
                }
            }
        } else {
           //make label background colours - transparent
            lblCheckUsername.setText("");
            lblCheckPassword.setText("");

            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
            Scene scene = new Scene(load);
            Stage stage = (Stage) txtUserName.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Form");
        }
    }
}
