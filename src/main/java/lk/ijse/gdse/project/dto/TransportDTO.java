package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TransportDTO {
    private String transport_id;
    private String transport_type;
    private String start_date;
    private String end_date;
    private String driver_id;
}
