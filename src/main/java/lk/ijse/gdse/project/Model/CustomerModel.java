package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {

    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select cust_ID from customer order by cust_ID desc limit 1");

       if (resultSet.next()) {
           String lastID = resultSet.getString(1);
           String subString = lastID.substring(1);
           int i = Integer.parseInt(subString);
           int newIndex = i+1;
           return String.format("C%03d", newIndex);
       }
       return "C001";
    }
}
