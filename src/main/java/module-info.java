module lk.ijse.gdse.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.gdse.project.controller to javafx.fxml;
    exports lk.ijse.gdse.project;
}