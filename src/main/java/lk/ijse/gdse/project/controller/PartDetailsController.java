package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import lk.ijse.gdse.project.Model.PartDetailModel;
import lk.ijse.gdse.project.Model.PartModel;
import lk.ijse.gdse.project.Model.VehicleModel;
import lk.ijse.gdse.project.dto.PartDTO;
import lk.ijse.gdse.project.dto.PartDetailDTO;
import lk.ijse.gdse.project.dto.VehicleDTO;
import lk.ijse.gdse.project.dto.tm.PartDetailCartTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PartDetailsController implements Initializable {

    @FXML
    private TableColumn<PartDetailCartTM, Double> ColQuantity;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbPartId;

    @FXML
    private ComboBox<String> cmbVehicleID;

    @FXML
    private TableColumn<PartDetailCartTM, Button> colAction;

    @FXML
    private TableColumn<PartDetailCartTM, String> colModel;

    @FXML
    private TableColumn<PartDetailCartTM, Double> colTotal;

    @FXML
    private TableColumn<PartDetailCartTM, Double> colUnitPrice;

    @FXML
    private TableColumn<PartDetailCartTM, String> colVehicleID;

    @FXML
    private Label lblPartName;

    @FXML
    private Label lblQuantityOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblVehicleName;

    @FXML
    private TableView<PartDetailCartTM> tblPartDetails;

    @FXML
    private HBox txtAddQty;

    @FXML
    private TextField txtAddedQuantity;

    private final PartModel partModel = new PartModel();
    private final VehicleModel vehicleModel = new VehicleModel();
    private final PartDetailModel partDetailModel = new PartDetailModel();

    private final ObservableList<PartDetailCartTM> cartTMS = FXCollections.observableArrayList();

    @FXML
    void AddtoCartOnAction(ActionEvent event) {
        String selectedPardId = cmbPartId.getSelectionModel().getSelectedItem();
        if (selectedPardId == null) {
            new Alert(Alert.AlertType.WARNING, "please select part Id!").show();
        }

        String selectedVehicleId = cmbVehicleID.getSelectionModel().getSelectedItem();
        if (selectedVehicleId == null) {
            new Alert(Alert.AlertType.WARNING, "please select vehicle Id!").show();
        }

        String QuantityOnHand = txtAddedQuantity.getText();
        String addQuantity = txtAddedQuantity.getText();

        String qtyPattern = "^[0-9]+$";

        if(!addQuantity.matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid quantity!").show();
            return;
        }

        String partName = lblPartName.getText();
        int Quantity = Integer.parseInt(lblQuantityOnHand.getText());
        int addedQuantity = Integer.parseInt(txtAddedQuantity.getText());

        txtAddedQuantity.setText("");

        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * addedQuantity;

        for (PartDetailCartTM partDetailCartTM : cartTMS) {
            if (partDetailCartTM.getPartId().equals(selectedPardId)) {
//                int qty = partDetailCartTM.getCartQuantity() + Quantity;
//                partDetailCartTM.setCartQuantity(qty);
//                partDetailCartTM.setTotalPrice(unitPrice * qty);

                tblPartDetails.refresh();
                return;
            }
        }
        Button btn = new Button("remove");

        PartDetailCartTM partDetailCartTM = new PartDetailCartTM(
                selectedPardId,
                selectedVehicleId,
                addedQuantity,
                unitPrice,
                total,
                btn
        );

        btn.setOnAction(actionEvent -> {
            cartTMS.remove(partDetailCartTM);
            tblPartDetails.refresh();
        });

        cartTMS.add(partDetailCartTM);
        tblPartDetails.setItems(cartTMS);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(tblPartDetails.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please add parts to cart..!").show();
            return;
        }
        if (cmbVehicleID.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select vehicle Id!").show();
            return;
        }
        String selectedPardId = cmbPartId.getSelectionModel().getSelectedItem();
        String selectedVehicleId = cmbVehicleID.getSelectionModel().getSelectedItem();

        ArrayList<PartDetailDTO> partDetailDTOS = new ArrayList<>();

        for (PartDetailCartTM partDetailCartTM : cartTMS) {
            PartDetailDTO partDetailDTO = new PartDetailDTO(
                    selectedPardId,
                    partDetailCartTM.getPartId(),
                    partDetailCartTM.getTotalPrice(),
                    partDetailCartTM.getCartQuantity()

            );
            partDetailDTOS.add(partDetailDTO);
        }

        boolean isSaved = partDetailModel.savePartDetail(partDetailDTOS);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "saved..!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, " fail..!").show();
        }

    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void cmbPartIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedPartId = cmbPartId.getSelectionModel().getSelectedItem();
        PartDTO partDTO = partModel.findById(selectedPartId);
        if(partDTO != null){
            lblPartName.setText(partDTO.getName());
            lblQuantityOnHand.setText(String.valueOf(partDTO.getQuantity()));
            lblUnitPrice.setText(String.valueOf(partDTO.getPrice()));
        }
    }

    @FXML
    void cmbVheicleIDOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedVehicleID = cmbVehicleID.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleDTO = vehicleModel.findById(selectedVehicleID);
        if(vehicleDTO != null){
            lblVehicleName.setText(vehicleDTO.getModel());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();
        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "cannot load table").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadPardIds();
        loadVehicleIDs();

        cmbPartId.getSelectionModel().clearSelection();
        cmbVehicleID.getSelectionModel().clearSelection();
        lblVehicleName.setText("");
        lblPartName.setText("");
        lblQuantityOnHand.setText("");
        lblUnitPrice.setText("");
        txtAddedQuantity.setText("");

        cartTMS.clear();
        tblPartDetails.refresh();

    }

    private void loadVehicleIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> vehicleIDs = vehicleModel.getAllVehicleIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(vehicleIDs);
        cmbVehicleID.setItems(observableList);
    }

    private void loadPardIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> partIDs = partModel.getAllPartIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(partIDs);
        cmbPartId.setItems(observableList);
    }

    private void setCellValues() {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("partId"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("partName"));
        ColQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

    }
}
