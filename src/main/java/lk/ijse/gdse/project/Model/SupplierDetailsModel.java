package lk.ijse.gdse.project.Model;


import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.SupplierDetailDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDetailsModel {

    private final PartModel partModel = new PartModel();

    private boolean saveSupplierDetail(SupplierDetailDTO supplierDetailDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into supplier_details values (?,?,?,?, ?)",
                supplierDetailDTO.getSupplier_id(),
                supplierDetailDTO.getPart_id(),
                supplierDetailDTO.getSupply_date(),
                supplierDetailDTO.getQuantity(),
                supplierDetailDTO.getTotal()
        );
    }

    public boolean save(ArrayList<SupplierDetailDTO> supplierDetailDTOS) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            for (SupplierDetailDTO dto : supplierDetailDTOS) {
                boolean isSaved = CrudUtil.execute(
                        "insert into supplier_details values (?,?,?,?,?)",
                        dto.getSupplier_id(),
                        dto.getPart_id(),
                        dto.getSupply_date(),
                        dto.getQuantity(),
                        dto.getTotal()
                );

                if (!isSaved) {
                    connection.rollback();
                    return false;
                }

                boolean isQtyReduced = partModel.redQty(dto);

                if (!isQtyReduced) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }

}

