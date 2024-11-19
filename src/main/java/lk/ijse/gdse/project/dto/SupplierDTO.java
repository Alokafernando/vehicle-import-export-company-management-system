package lk.ijse.gdse.project.dto;

import lombok.*;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplierDTO {
    private String supplier_id;
    private String name;
    private String contact;
    private String email;

   // private ArrayList<SupplierDetailDTO> supplier_details;


}
