package lk.ijse.gdse.project.Model;


import lk.ijse.gdse.project.dto.SupplierDetailDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDetailsModel {

//    private final PartModel partModel = new PartModel();
//
//    public boolean saveSupplyDetailList(ArrayList<SupplierDetailDTO> supplierDetailDTOS) throws SQLException, ClassNotFoundException {
//
//        for (SupplierDetailDTO supplierDetailDTO : supplierDetailDTOS) {
//            boolean isSupplyDetailsSaved = saveSupplierDetail(supplierDetailDTO);
//            if (!isSupplyDetailsSaved) {
//                return false;
//            }
//            boolean isPartUpdated = partModel.reduceQty(supplierDetailDTO);
//            if (!isPartUpdated) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    private boolean saveSupplierDetail(SupplierDetailDTO supplierDetailDTO) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute(
//                "insert into supplier_details values (?,?,?,?, ?)",
//                supplierDetailDTO.getSupplier_id(),
//                supplierDetailDTO.getPart_id(),
//                supplierDetailDTO.getSupply_date(),
//                supplierDetailDTO.getQuantity(),
//                supplierDetailDTO.getTotal()
//        );
//    }


}
