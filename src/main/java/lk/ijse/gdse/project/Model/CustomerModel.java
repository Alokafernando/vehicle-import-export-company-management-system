package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.CustomerDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public  String getNextCustomerId() throws SQLException, ClassNotFoundException {
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

    public  ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from customer");
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public  boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("insert into customer values(?, ?, ?, ?, ?)",
                customerDTO.getCust_ID(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact(),
                customerDTO.getEmail());
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update customer set name =?, address =?, contact =?, email =? where cust_ID = ?",
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact(),
                customerDTO.getEmail(),
                customerDTO.getCust_ID()
        );
    }

    public boolean deleteCustomer(String cust_ID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from customer where cust_ID = ?", cust_ID);
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select cust_ID from customer");
        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }
        return customerIds;
    }
}
