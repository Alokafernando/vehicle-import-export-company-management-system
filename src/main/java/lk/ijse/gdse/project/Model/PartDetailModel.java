package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.db.DBConnection;
import lk.ijse.gdse.project.dto.PartDetailDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartDetailModel {

    private  final PartModel partModel = new PartModel();

   public boolean savePartDetail(ArrayList<PartDetailDTO> partDetailDTO) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getInstance().getConnection();

       try{
           connection.setAutoCommit(false);

           for (PartDetailDTO dto : partDetailDTO) {
               boolean isSaved = CrudUtil.execute("insert into part_details values(?,?,?,?);",
                       dto.getPart_id(),
                       dto.getVehicle_id(),
                       dto.getQuantity(),
                       dto.getPrice()
               );
               if(!isSaved){
                   connection.rollback();
                   return false;
               }

               boolean isQtyReduced = partModel.decrementQty(dto);
               if(!isQtyReduced){
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
       }finally {
            connection.setAutoCommit(true);
       }
   }
}
