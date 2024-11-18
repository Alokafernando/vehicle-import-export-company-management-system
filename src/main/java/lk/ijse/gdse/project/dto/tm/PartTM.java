package lk.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PartTM {
    private String part_id;
    private String name;
    private double price;
    private int quantity;
}
