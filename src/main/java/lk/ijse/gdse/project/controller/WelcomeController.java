package lk.ijse.gdse.project.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private AnchorPane h;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ProgressBar loading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        simulateLoadingAnimation();

    }

    private void simulateLoadingAnimation() {
        Duration animationDuration = Duration.seconds(5);
        double endProgress = 1.0;

        KeyFrame startFrame = new KeyFrame(Duration.ZERO, new KeyValue(loading.progressProperty(), 0));
        KeyFrame endFrame = new KeyFrame(animationDuration, new KeyValue(loading.progressProperty(), endProgress));

        Timeline timeline = new Timeline(startFrame, endFrame);
        timeline.setOnFinished(event -> Platform.runLater(this::navigateToLoginPage));
        timeline.play();
    }

    private void navigateToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) loading.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
