package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ReservationDTO {
    private String cust_id;
    private String reservation_id;
    private String reservation_date;
}
