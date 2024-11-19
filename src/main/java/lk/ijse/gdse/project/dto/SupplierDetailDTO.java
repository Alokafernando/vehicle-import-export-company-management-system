package lk.ijse.gdse.project.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplierDetailDTO {
    private String supplier_id;
    private String part_id;
    private Date supply_date;
    private int quantity;
    private double total;
}
