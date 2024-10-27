package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ImportCompanyDTO {
    private String company_ID;
    private String company_Name;
    private String country;
    private String contact;
    private String email;
}
