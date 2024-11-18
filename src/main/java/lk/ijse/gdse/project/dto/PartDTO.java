package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PartDTO {
    private String part_id;
    private String name;
    private double price;
    private int quantity;
}
