

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

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginController {
    private final String username = "a";
    private final String password = "a";


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

        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
