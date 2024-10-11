package lk.ijse.gdse.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitalizer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/welcome.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Main page");

        Image image = new Image(getClass().getResourceAsStream("/image/application.png"));
        stage.getIcons().add(image);

        stage.show();
    }
}
