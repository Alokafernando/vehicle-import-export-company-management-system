package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TaxDTO {
    private String vehicle_id;
    private String tax_id;
    private double import_tax;
    private double export_tax;
    private double ground_tax;
}
