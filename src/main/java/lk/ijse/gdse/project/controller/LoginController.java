

package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

        final String baseStyle = "-fx-border-color: #ffffff; -fx-background-radius: 25; -fx-border-radius: 15; -fx-background-color: transparent; -fx-text-fill: #ffffff;";
        final String errorStyle = "-fx-border-color: #ff5e57; -fx-background-radius: 25; -fx-border-radius: 15; -fx-background-color: transparent; -fx-text-fill: #ffffff;";
        final String errorTextColor = "-fx-text-fill: #ff5e57;";

        txtPassword.setStyle(baseStyle);
        txtUserName.setStyle(baseStyle);
        lblCheckUsername.setText("");
        lblCheckPassword.setText("");

        try {
            if (u.isEmpty()) {
                setUsernameError("required", errorStyle, errorTextColor);
            } else if (!username.equals(u)) {
                setUsernameError("wrong username", errorStyle, errorTextColor);
            }

            if (p.isEmpty()) {
                setPasswordError("required", errorStyle, errorTextColor);
            } else if (!password.equals(p)) {
                setPasswordError("wrong password", errorStyle, errorTextColor);
            }

            if (username.equals(u) && password.equals(p)) {
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
                Scene scene = new Scene(load);
                Stage stage = (Stage) txtUserName.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Dashboard Form");
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setUsernameError(String message, String style, String textColor) {
        lblCheckUsername.setText(message);
        lblCheckUsername.setStyle(textColor);
        txtUserName.setStyle(style);
    }

    private void setPasswordError(String message, String style, String textColor) {
        lblCheckPassword.setText(message);
        lblCheckPassword.setStyle(textColor);
        txtPassword.setStyle(style);
    }

}
