package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.PartDTO;
import lk.ijse.gdse.project.dto.SupplierDetailDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

    public class PartModel {

        public String getNextPartID() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = CrudUtil.execute("SELECT part_id FROM part ORDER BY part_id DESC LIMIT 1");

            if (resultSet.next()) {
                String lastID = resultSet.getString(1);

                String subString = lastID.replaceAll("[^0-9]", "");

                if (!subString.isEmpty()) {
                    int i = Integer.parseInt(subString);
                    int newIndex = i + 1;
                    return String.format("PA%03d", newIndex);
                }
            }
            return "PA001";
        }


        public ArrayList<PartDTO> getAllParts() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = CrudUtil.execute("select * from part");
            ArrayList<PartDTO> partDTOS = new ArrayList<>();

            while (resultSet.next()) {
                PartDTO partDTO = new PartDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        Double.valueOf(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))

                );
                partDTOS.add(partDTO);
            }
            return partDTOS;
        }

        public boolean savePart(PartDTO partDTO) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute(
                    "insert into part  VALUES (?, ?, ?, ?)",
                    partDTO.getPart_id(),
                    partDTO.getName(),
                    partDTO.getPrice(),
                    partDTO.getQuantity()
            );
        }

        public boolean updatePart(PartDTO partDTO) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute(
                    "update part set name = ?, unit_price = ?, quantity = ? where part_id = ?",
                    partDTO.getName(),
                    partDTO.getPrice(),
                    partDTO.getQuantity(),
                    partDTO.getPart_id()
            );
        }

        public boolean deletePart(String partID) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("delete from part where part_id = ?", partID);
        }

        public ArrayList<String> getAllPartIDs() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = CrudUtil.execute("select part_id from part");
            ArrayList<String> partIDs = new ArrayList<>();
            while (resultSet.next()) {
                partIDs.add(resultSet.getString(1));
            }
            return partIDs;
        }

//        public ArrayList<String> getAllPartIds() throws SQLException, ClassNotFoundException {
//            ResultSet rst = CrudUtil.execute("select part_id from part");
//            ArrayList<String> partIds = new ArrayList<>();
//
//            while (rst.next()) {
//                partIds.add(rst.getString(1));
//            }
//            return partIds;
//        }
//
//
//        public PartDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException {
//            ResultSet rst = CrudUtil.execute("select * from part where part_id=?", selectedItemId);
//
//            if (rst.next()) {
//                return new PartDTO(
//                        rst.getString(1),
//                        rst.getString(2),
//                        rst.getDouble(3),
//                        rst.getInt(4)
//                );
//            }
//
//            return null;
//        }
//
//
//        public boolean reduceQty(SupplierDetailDTO supplierDetailDTO) throws SQLException, ClassNotFoundException {
//            return CrudUtil.execute(
//                    "update item set quantity = quantity - ? where item_id = ?",
//                    supplierDetailDTO.getQuantity(),
//                    supplierDetailDTO.getPart_id()
//            );
//        }


    }

