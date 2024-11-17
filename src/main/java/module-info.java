module lk.ijse.gdse.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires lombok;
    requires net.sf.jasperreports.core;

    opens lk.ijse.gdse.project.dto.tm     to javafx.base;
    opens lk.ijse.gdse.project.controller to javafx.fxml;
    exports lk.ijse.gdse.project;
}