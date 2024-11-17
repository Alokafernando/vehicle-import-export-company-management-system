package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.PaymentDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public String getNextPayId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select pay_id from payment order by pay_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("P%03d", newIndex);
        }
        return "P001";
    }

    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from payment");
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        while (resultSet.next()) {
            PaymentDTO paymentDTO = new PaymentDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Double.parseDouble(resultSet.getString(4)),
                    Double.parseDouble(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    resultSet.getString(7)
            );
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into payment values (?, ?, ?, ?, ?, ?, ?)",
                paymentDTO.getReservation_id(),
                paymentDTO.getPay_id(),
                paymentDTO.getPayment_method(),
                paymentDTO.getDeposite(),
                paymentDTO.getAmount(),
                paymentDTO.getRemain_amount(),
                paymentDTO.getStatus());
    }
    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update payment set reservation_id=?,  pay_method=?, deposite=?, amount=?, remain_amount=?, status=? where pay_id=?",
                paymentDTO.getReservation_id(),
                paymentDTO.getPayment_method(),
                paymentDTO.getDeposite(),
                paymentDTO.getAmount(),
                paymentDTO.getRemain_amount(),
                paymentDTO.getStatus(),
                paymentDTO.getPay_id());
    }

    public boolean deletePayment(String paymentID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from payment where pay_id=?", paymentID);
    }
}
