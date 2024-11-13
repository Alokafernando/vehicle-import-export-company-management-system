package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.ReservationDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationModel {
    public String getNextReservationID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select reservation_id from reservation order by reservation_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            if (lastID != null && lastID.startsWith("R") && lastID.length() > 1) {
                String subString = lastID.substring(1);
                try {
                    int i = Integer.parseInt(subString);
                    int newIndex = i + 1;
                    return String.format("R%03d", newIndex);
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid reservation ID format: " + lastID);
                }
            }
        }
        return "R001";
    }


    public ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from reservation");

        ArrayList<ReservationDTO> reservations = new ArrayList<>();
        while (resultSet.next()) {
            ReservationDTO reservation = new ReservationDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            reservations.add(reservation);
        }
        return reservations;
    }

    public boolean saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into reservation values (?, ?, ?)",
                reservationDTO.getCust_id(),
                reservationDTO.getReservation_id(),
                reservationDTO.getReservation_date()
                );
    }

    public boolean updateReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update reservation set cust_id=?, reservation_date =? where reservation_id = ?",
                reservationDTO.getCust_id(),
                reservationDTO.getReservation_date(),
                reservationDTO.getReservation_id());
    }

    public boolean deleteReservation(String reservationId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from reservation where reservation_id=? ", reservationId);
    }

    public ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select reservation_id from reservation");
        ArrayList<String> reservationIds = new ArrayList<>();

        while (rst.next()) {
            reservationIds.add(rst.getString(1));
        }
        return reservationIds;
    }

    /*
       public ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select reservation_id from reservation");
        ArrayList<String> reservationIds = new ArrayList<>();

        while (rst.next()) {
            reservationIds.add(rst.getString(1));
        }

        if (reservationIds.isEmpty()) {
            System.out.println("No reservation IDs found.");
        }

        return reservationIds;
    }
     */

}
