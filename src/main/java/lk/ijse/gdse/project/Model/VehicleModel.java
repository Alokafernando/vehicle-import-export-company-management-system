package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.VehicleDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public String getNextVehicleID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select vehicle_id from vehicle order by vehicle_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("V%03d", newIndex);
        }
        return "V001";
    }

    public ArrayList<VehicleDTO> getAllVehicles() throws SQLException, ClassNotFoundException {
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

    public boolean saveVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into vehicle values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                vehicle.getImport_company_id(),
                vehicle.getImport_date(),
                vehicle.getVehicle_id(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getCurrent_status(),
                vehicle.getExport_company_id(),
                vehicle.getExport_date(),
                vehicle.getSale_date(),
                vehicle.getImport_price(),
                vehicle.getExport_price(),
                vehicle.getReservation_id(),
                vehicle.getTransport_id());
    }

    public boolean updateVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE vehicle SET import_company_id = ?, import_date = ?, model = ?, year = ?, color = ?, current_status = ?, export_company_id = ?, export_date = ?, sale_date = ?, import_price = ?, export_price = ?, reservation_id = ?, transport_id = ? WHERE vehicle_id = ?",
                vehicle.getImport_company_id(),
                vehicle.getImport_date(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getCurrent_status(),
                vehicle.getExport_company_id(),
                vehicle.getExport_date(),
                vehicle.getSale_date(),
                vehicle.getImport_price(),
                vehicle.getExport_price(),
                vehicle.getReservation_id(),
                vehicle.getTransport_id(),
                vehicle.getVehicle_id()
        );
    }

        public boolean deleteVehicle(String vehicleId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from vehicle where vehicle_id =?", vehicleId);
    }


    public ArrayList<String> getAllVehicleIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select vehicle_id from vehicle");
        ArrayList<String> vehicleIds = new ArrayList<>();

        while (rst.next()) {
            vehicleIds.add(rst.getString(1));
        }
        return vehicleIds;

    }
}
