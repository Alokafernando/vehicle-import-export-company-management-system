package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class PaymentDTO {
    private String part_id;
    private String vehicle_id;
    private int quantity;
}
