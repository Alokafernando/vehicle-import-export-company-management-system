package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

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


}
