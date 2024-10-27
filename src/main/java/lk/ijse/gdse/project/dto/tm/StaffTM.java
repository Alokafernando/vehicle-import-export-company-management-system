package lk.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class StaffTM {
    private String staff_id;
    private String staff_name;
    private String address;
    private double salary;
    private String role;
}
