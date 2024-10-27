package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplierDetailDTO {
    private String supplier_id;
    private String part_id;
    private String supply_date;
    private int quantity;
}
