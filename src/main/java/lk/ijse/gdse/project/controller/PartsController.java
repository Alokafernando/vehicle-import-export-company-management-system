package lk.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PartsController {

    @FXML
    private Button btnDeletePart;

    @FXML
    private Button btnSavePart;

    @FXML
    private Button btnUpdatePart;

    @FXML
    private Button btnpartRep;

    @FXML
    private TableColumn<?, ?> colPartID;

    @FXML
    private TableColumn<?, ?> colPartName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblPartID;

    @FXML
    private TableView<?> tblPart;

    @FXML
    private TextField txtPartName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void deletePart(ActionEvent event) {

    }

    @FXML
    void generatePartRepo(ActionEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {

    }

    @FXML
    void savePart(ActionEvent event) {

    }

    @FXML
    void updatePart(ActionEvent event) {

    }

}
