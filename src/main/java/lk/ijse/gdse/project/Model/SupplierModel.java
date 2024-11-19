package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.SupplierDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public String getNextSupplierID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);

            String subString = lastID.replaceAll("[^0-9]", "");

            if (!subString.isEmpty()) {
                int i = Integer.parseInt(subString);
                int newIndex = i + 1;
                return String.format("SU%03d", newIndex);
            }
        }
        return "SU001";
    }



    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from supplier");
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        while (resultSet.next()) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into supplier values(?,?,?,?)",
                supplierDTO.getSupplier_id(),
                supplierDTO.getName(),
                supplierDTO.getContact(),
                supplierDTO.getEmail());
    }

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update supplier set name=?, contact=?, email=? where supplier_id=?",
                supplierDTO.getName(),
                supplierDTO.getContact(),
                supplierDTO.getEmail(),
                supplierDTO.getSupplier_id());
    }

    public boolean deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from supplier where supplier_id=?", supplierID);
    }


    private final SupplierDetailsModel supplierDetailsModel = new SupplierDetailsModel();

    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select supplier_id from supplier order by supplier_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }

//    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        try {
//            connection.setAutoCommit(false); // 1
//
//            boolean isSupplierSaved = CrudUtil.execute(
//                    "insert into supplier values(?,?,?,?)",
//                supplierDTO.getSupplier_id(),
//                supplierDTO.getName(),
//                supplierDTO.getContact(),
//                supplierDTO.getEmail());
//
//            if (isSupplierSaved) {
//                boolean isSupplierDetailsSaved = supplierDetailsModel.saveSupplyDetailList(supplierDTO.getSupplier_details());//saveOrderDetailsList(orderDTO.getOrderDetailsDTOS());
//                if (isSupplierDetailsSaved) {
//                    connection.commit();
//                    return true;
//                }
//            }
//            connection.rollback();
//            return false;
//        } catch (Exception e) {
//            connection.rollback();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }

    public SupplierDTO findById(String selectedSupplierID) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from supplier where supplier_id=?", selectedSupplierID);

        if (rst.next()) {
            return new SupplierDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    public ArrayList<String> getSupplierIDs() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select supplier_id from supplier");
        ArrayList<String> supplierIDs = new ArrayList<>();
        while (resultSet.next()) {
            supplierIDs.add(resultSet.getString(1));
        }
        return supplierIDs;
    }
}
