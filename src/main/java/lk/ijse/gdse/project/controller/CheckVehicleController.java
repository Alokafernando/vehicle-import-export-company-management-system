package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.project.Model.CheckVehicleModel;
import lk.ijse.gdse.project.dto.VehicleDTO;
import lk.ijse.gdse.project.dto.tm.CheckVehicleTM;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckVehicleController implements Initializable {

    @FXML
    private TableColumn<CheckVehicleTM, String> colColor;

    @FXML
    private TableColumn<CheckVehicleTM, String> colID;

    @FXML
    private TableColumn<CheckVehicleTM, String> colModel;

    @FXML
    private TableColumn<CheckVehicleTM, String> colStatus;

    @FXML
    private TableColumn<CheckVehicleTM, Integer> colYear;

    @FXML
    private TableView<CheckVehicleTM> tblcheckVehicle;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtYear;

    private ObservableList<CheckVehicleTM> vehicleTMS;

    private final CheckVehicleModel checkVehicleModel = new CheckVehicleModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();
        try {
            loadVehicleData();
            initializeFilters();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValues() {
        colID.setCellValueFactory(new PropertyValueFactory<>("vID"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("vModel"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("vYear"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("vType"));
    }

    private void loadVehicleData() throws SQLException, ClassNotFoundException {
        vehicleTMS = FXCollections.observableArrayList();

        ArrayList<VehicleDTO> vehicleDTOS = checkVehicleModel.getData();

        for (VehicleDTO vehicleDTO : vehicleDTOS) {

            String status = vehicleDTO.getCurrent_status();

            if ("import".equalsIgnoreCase(status)) {
                status = "importing";
            } else if ("repair".equalsIgnoreCase(status)) {
                status = "repairing";
            } else if ("sale".equalsIgnoreCase(status)) {
                status = "reserved";
            } else {
                status = "export";
            }

            CheckVehicleTM vehicleTM = new CheckVehicleTM(
                    vehicleDTO.getVehicle_id(),
                    vehicleDTO.getModel(),
                    vehicleDTO.getYear(),
                    vehicleDTO.getColor(),
                    status
            );
            vehicleTMS.add(vehicleTM);
        }

        tblcheckVehicle.setItems(vehicleTMS);
    }

    private void initializeFilters() {
        txtColor.setOnAction(event -> filterTableData());
        txtModel.setOnAction(event -> filterTableData());
        txtYear.setOnAction(event -> filterTableData());
    }

    private void filterTableData() {
        String color = txtColor.getText().toLowerCase();
        String model = txtModel.getText().toLowerCase();
        String year = txtYear.getText().toLowerCase();

        ObservableList<CheckVehicleTM> filteredList = FXCollections.observableArrayList();

        for (CheckVehicleTM vehicleTM : vehicleTMS) {
            boolean matches = true;

            if (!color.isEmpty() && !vehicleTM.getColor().toLowerCase().contains(color)) {
                matches = false;
            }
            if (!model.isEmpty() && !vehicleTM.getVModel().toLowerCase().contains(model)) {
                matches = false;
            }
            if (!year.isEmpty() && !String.valueOf(vehicleTM.getVYear()).contains(year)) {
                matches = false;
            }

            if (matches) {
                filteredList.add(vehicleTM);
            }
        }

        tblcheckVehicle.setItems(filteredList);
    }
}
