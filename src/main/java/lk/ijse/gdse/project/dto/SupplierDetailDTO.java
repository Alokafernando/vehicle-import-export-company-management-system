package lk.ijse.gdse.project.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplierDetailDTO extends ArrayList<SupplierDetailDTO> {
    private String supplier_id;
    private String part_id;
    private String supply_date;
    private int quantity;
    private double total;


}
