package lk.ijse.gdse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.project.Model.PartModel;
import lk.ijse.gdse.project.Model.SupplierDetailsModel;
import lk.ijse.gdse.project.Model.SupplierModel;
import lk.ijse.gdse.project.dto.PartDTO;
import lk.ijse.gdse.project.dto.SupplierDTO;
import lk.ijse.gdse.project.dto.SupplierDetailDTO;
import lk.ijse.gdse.project.dto.tm.SupplyCartTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplyDetailsController  {

//    @FXML
//    private Button btnCart;
//
//    @FXML
//    private Button btnPlaceOrder;
//
//    @FXML
//    private Button btnReset;
//
//    @FXML
//    private ComboBox<String> cmbPartID;
//
//    @FXML
//    private ComboBox<String> cmbSupplierID;
//
//    @FXML
//    private TableColumn<?, ?> colAction;
//
//    @FXML
//    private TableColumn<SupplyCartTM, String> colName;
//
//    @FXML
//    private TableColumn<SupplyCartTM, String> colPartID;
//
//    @FXML
//    private TableColumn<SupplyCartTM, Integer> colQuantity;
//
//    @FXML
//    private TableColumn<SupplyCartTM, Double> colTotal;
//
//    @FXML
//    private TableColumn<SupplyCartTM, Double> colUnitPrice;
//
//    @FXML
//    private Label lblPartName;
//
//    @FXML
//    private Label lblQOnHand;
//
//    @FXML
//    private TextField lblQty;
//
//    @FXML
//    private Label lblSupplierName;
//
//    @FXML
//    private Label lblSupplyDate;
//
//    @FXML
//    private Label lblUnitPrice;
//
//    @FXML
//    private Label lblsupplyID;
//
//    @FXML
//    private TableView<?> tblSupplyDetails;
//
//    private final PartModel partModel = new PartModel();
//    private final SupplierModel supplierModel =  new SupplierModel();
//    private final SupplierDetailsModel supplierDetailsModel = new SupplierDetailsModel();
//
//    private final ObservableList<SupplyCartTM> cartTMS = FXCollections.observableArrayList();
//
//    @FXML
//    void actionOnPlaceorderButton(ActionEvent event) {
//        if(tblSupplyDetails.getItems().isEmpty()){
//            new Alert(Alert.AlertType.ERROR, "Please add parts to cart..!").show();
//            return;
//        }
//
//        if(cmbSupplierID.getSelectionModel().isEmpty()){
//            new Alert(Alert.AlertType.ERROR, "Please select supplier ID!").show();
//            return;
//        }
//
//        String supplierID = cmbSupplierID.getValue();
//        Date supplyDate  = Date.valueOf(lblSupplyDate.getText());
//        String orderId = cmbSupplierID.getValue();
//        int quantity = Integer.parseInt(lblQOnHand.getText());
//        double total = Double.parseDouble(lblUnitPrice.getText());
//
//        ArrayList<SupplierDetailDTO> supplierDetailDTOS = new ArrayList<>();
//
//        for(SupplyCartTM supplyCartTM : cartTMS){
//            SupplierDetailDTO supplierDetailDTO = new SupplierDetailDTO(
//                supplierID,
//                    orderId,
//                    supplyDate,
//                    quantity,
//                    total
//
//            );
//            supplierDetailDTOS.add(supplierDetailDTO);
//
//            SupplierDTO supplierDTO = new SupplierDTO(
//                    supplierID,
//                    supplierDetailDTO.ge
//
//            );
//        }
//
//
//    }
//
//    @FXML
//    void actionOnSupplyCart(MouseEvent event) {
//
//    }
//
//    @FXML
//    void cartButtonOnAction(ActionEvent event) {
//        //
//        String selectedPartId = cmbPartID.getValue();
//
//        if (selectedPartId == null) {
//            new Alert(Alert.AlertType.ERROR, "Please select part..!").show();
//            return;
//        }
//
//        String cartToQty = lblQOnHand.getText();
//
//        String qtyPattern = "^[0-9]+$";
//
//        if(!cartToQty.matches(qtyPattern)) {
//            new Alert(Alert.AlertType.ERROR, "Please enter valid quantity..!").show();
//            return;
//        }
//
//        String partName = lblPartName.getText();
//        int cartQty = Integer.parseInt(cartToQty);
//        int qtyOnHand = Integer.parseInt(lblQOnHand.getText());
//
//        if(qtyOnHand < cartQty){
//            new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
//        }
//
//        lblQty.setText("");
//
//        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
//        double total = unitPrice * cartQty;
//
//        for(SupplyCartTM supplyCartTM : cartTMS){
//            if(supplyCartTM.getPartId().equals(selectedPartId)){
//
//                int newQty = supplyCartTM.getCartQuantity() + qtyOnHand;
//                supplyCartTM.setCartQuantity(newQty);
//                supplyCartTM.setTotalPrice(unitPrice * newQty);
//
//                tblSupplyDetails.refresh();
//                return;
//            }
//        }
//
//        Button btn = new Button("Remove");
//        SupplyCartTM newCartTm = new SupplyCartTM(
//                selectedPartId,
//                partName,
//                cartQty,
//                unitPrice,
//                total,
//                btn
//        );
//
//        btn.setOnAction(actionEvent -> {
//            cartTMS.remove(newCartTm);
//            tblSupplyDetails.refresh();
//        });
//
//        cartTMS.add(newCartTm);
//
//    }
//
//    @FXML
//    void cmbPartIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String selectedPartId = cmbPartID.getSelectionModel().getSelectedItem();
//        PartDTO partDTO = partModel.findById(selectedPartId);
//        if(partDTO != null){
//            lblPartName.setText(partDTO.getName());
//            lblQOnHand.setText(String.valueOf(partDTO.getQuantity()));
//            lblUnitPrice.setText(String.valueOf(partDTO.getPrice()));
//        }
//
//    }
//
//    @FXML
//    void cmbSupplierIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String selectedSupplierId = cmbSupplierID.getSelectionModel().getSelectedItem();
//        SupplierDTO SupplierDTO = supplierModel.findById(selectedSupplierId);
//        if (SupplierDTO != null) {
//            lblSupplierName.setText(SupplierDTO.getName());
//        }
//    }
//
//    @FXML
//    void onResetButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
//        refreshPage();
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        setCellValues();
//        try {
//            refreshPage();
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
//        }
//    }
//
//    private void refreshPage() throws SQLException, ClassNotFoundException {
//        lblsupplyID.setText(supplierModel.getNextSupplierId());
//        lblSupplyDate.setText(LocalDate.now().toString());
//
//        loadSupplierID();
//        loadPartID();
//
//        cmbPartID.getSelectionModel().clearSelection();
//        cmbSupplierID.getSelectionModel().clearSelection();
//        lblSupplierName.setText("");
//        lblQOnHand.setText("");
//        lblUnitPrice.setText("");
//        lblQty.setText("");
//
//    }
//
//    private void loadPartID() throws SQLException, ClassNotFoundException {
//        ArrayList<String> partIDs = partModel.getAllPartIds();
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll(partIDs);
//        cmbPartID.setItems(observableList);
//
//    }
//
//    private void loadSupplierID() throws SQLException, ClassNotFoundException {
//        ArrayList<String> supplierIDs = supplierModel.getSupplierIDs();
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll(supplierIDs);
//        cmbSupplierID.setItems(observableList);
//    }
//
//    private void setCellValues() {
//        colPartID.setCellValueFactory(new PropertyValueFactory<>("partId"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("partName"));
//        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("removeButton"));
//    }
}
