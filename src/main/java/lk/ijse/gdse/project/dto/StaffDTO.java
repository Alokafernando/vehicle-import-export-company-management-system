package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class StaffDTO {
    private String staff_id;
    private String staff_name;
    private String address;
    private double salary;
    private String role;
}
