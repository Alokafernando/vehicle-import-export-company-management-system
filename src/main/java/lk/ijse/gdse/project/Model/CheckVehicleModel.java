package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.VehicleDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckVehicleModel {
    public ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from vehicle");
        ArrayList<VehicleDTO> vehicles = new ArrayList<>();

        while (resultSet.next()) {
            VehicleDTO vehicle = new VehicleDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    Integer.parseInt(resultSet.getString(5)),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    Double.parseDouble(resultSet.getString(11)),
                    Double.parseDouble(resultSet.getString(12)),
                    resultSet.getString(13),
                    resultSet.getString(14)
            );
            vehicles.add(vehicle);
        }
        return vehicles;
    }

}
