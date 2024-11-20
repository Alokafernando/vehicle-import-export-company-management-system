package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PartDetailDTO {
    private String part_id;
    private String vehicle_id;
    private double price;
    private int quantity;

}
