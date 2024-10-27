package lk.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PartDetailTM {
    private String part_id;
    private String vehicle_id;
    private int quantity;
}
