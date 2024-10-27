package lk.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ReservationTM {
    private String cust_id;
    private String reservation_id;
    private String reservation_date;
}
