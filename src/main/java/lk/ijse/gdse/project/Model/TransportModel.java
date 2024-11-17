package lk.ijse.gdse.project.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.project.dto.TransportDTO;
import lk.ijse.gdse.project.dto.tm.TransportTM;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportModel {
    public String getNextTransportID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select transport_id from transport order by transport_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.replaceAll("[^0-9]", "");
            if (subString.isEmpty()) {
                throw new SQLException("Invalid ID format in the database: " + lastID);
            }
            int i = Integer.parseInt(subString);
            int newIndex = i + 1;
            return String.format("T%03d", newIndex);
        }
        return "T001";
    }


    public ArrayList<TransportDTO> getAllTransports() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from transport");
        ArrayList<TransportDTO> transportDTOS = new ArrayList<>();
        ObservableList<TransportTM> transportTMS = FXCollections.observableArrayList();

        while (resultSet.next()) {
            TransportDTO transportDTO = new TransportDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            transportDTOS.add(transportDTO);
        }
        return transportDTOS;
    }

    public boolean saveTransport(TransportDTO transportDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO transport (transport_id, transport_type, start_date, end_date, driver_id) VALUES (?, ?, ?, ?, ?)",
                transportDTO.getTransport_id(),
                transportDTO.getTransport_type(),
                transportDTO.getStart_date(),
                transportDTO.getEnd_date(),
                transportDTO.getDriver_id());
    }

    public boolean updateTransport(TransportDTO transportDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE transport SET transport_type = ?, start_date = ?, end_date = ?, driver_id = ? WHERE transport_id = ?",
                transportDTO.getTransport_type(),
                transportDTO.getStart_date(),
                transportDTO.getEnd_date(),
                transportDTO.getDriver_id(),
                transportDTO.getTransport_id());

    }

    public boolean deleteTransport(String tranportId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from transport where transport_id=?", tranportId);
    }

    public ArrayList<String> getAllTransportIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select transport_id from transport");
        ArrayList<String> transportIds = new ArrayList<>();

        while (rst.next()) {
            transportIds.add(rst.getString(1));
        }
        return transportIds;
    }
}
