package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.StaffDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffModel {
    public String getNextStaffId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select staff_id from staff order by staff_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("S%03d", newIndex);
        }
        return "S001";
    }

    public ArrayList<StaffDTO> getAllStaff() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from staff");
        ArrayList<StaffDTO> staffDTOS = new ArrayList<>();

        while (resultSet.next()) {
            StaffDTO staffDTO = new StaffDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Double.parseDouble(resultSet.getString(4)),
                    resultSet.getString(5)
            );
            staffDTOS.add(staffDTO);
        }
        return staffDTOS;
    }

    public boolean saveStaff(StaffDTO staffDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into staff values(?, ?, ?, ?, ?)",
                staffDTO.getStaff_id(),
                staffDTO.getStaff_name(),
                staffDTO.getAddress(),
                staffDTO.getSalary(),
                staffDTO.getRole());
    }
    public boolean updateStaff(StaffDTO staffDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update staff set name =?, address =?, salary =?, role =? where staff_id =?",
                staffDTO.getStaff_name(),
                staffDTO.getAddress(),
                staffDTO.getSalary(),
                staffDTO.getRole(),
                staffDTO.getStaff_id());
    }
    public boolean deleteStaff(String staffID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from staff where staff_id = ?", staffID);
    }
}
