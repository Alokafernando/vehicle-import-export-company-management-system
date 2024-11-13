package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.DriverDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverModel {
    public  String getNextDriverId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select driver_id from driver order by driver_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("D%03d", newIndex);
        }
        return "D001";
    }

    public ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from driver");
        ArrayList<DriverDTO> driverDTOS = new ArrayList<>();

        while (resultSet.next()) {
            DriverDTO driverDTO = new DriverDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));

            driverDTOS.add(driverDTO);
        }
        return driverDTOS;
    }


    public  boolean saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into driver values(?,?,?)",
                driverDTO.getDriver_id(),
                driverDTO.getName(),
                driverDTO.getContact());
    }

    public  boolean updateDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update driver set name =?, contact=? where driver_id =?",
                driverDTO.getName(),
                driverDTO.getContact(),
                driverDTO.getDriver_id());
    }

    public boolean deleteDriver(String driverID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from driver where driver_id =? ", driverID);
    }
}
