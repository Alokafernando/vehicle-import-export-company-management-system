package lk.ijse.gdse.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO {
    private String cust_ID;
    private String name;
    private String address;
    private String contact;
    private String email;
}
