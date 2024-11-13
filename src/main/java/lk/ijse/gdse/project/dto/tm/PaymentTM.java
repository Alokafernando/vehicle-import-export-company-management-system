package lk.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentTM {
    private String reservation_id;
    private String pay_id;
    private String payment_method;
    private double deposite;
    private double amount;
    private double remain_amount;

}
