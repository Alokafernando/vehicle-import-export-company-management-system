package lk.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class VehicleDTO {
    private String import_company_id;
    private String import_date;
    private String vehicle_id;
    private String model;
    private int year;
    private String color;
    private String current_status;
    private String export_company_id;
    private String export_date;
    private String sale_date;
    private double import_price;
    private double export_price;
    private String reservation_id;
    private String transport_id;
}
