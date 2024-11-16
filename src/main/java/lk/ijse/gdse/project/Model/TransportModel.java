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
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("Tr%04d", newIndex);
        }
        return "Tr001";
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
        return CrudUtil.execute("insert into transport values(?,?,?,?,?)",
                transportDTO.getTransport_id(),
                transportDTO.getTransport_type(),
                transportDTO.getStart_date(),
                transportDTO.getEnd_date(),
                transportDTO.getDriver_id());
    }

    public boolean updateTransport(TransportDTO transportDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update transport set transport_type =?, start_date =?, end_date=?, driver_id=? where driver_id=?" ,
                transportDTO.getTransport_type(),
                transportDTO.getStart_date(),
                transportDTO.getEnd_date(),
                transportDTO.getDriver_id(),
                transportDTO.getTransport_id());
    }

    public boolean deleteTransport(String tranportId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from transport where transport_id=?", tranportId);
    }
}
