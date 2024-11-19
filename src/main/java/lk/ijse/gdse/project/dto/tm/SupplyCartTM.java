package lk.ijse.gdse.project.dto.tm;

import javafx.scene.control.Button;
import lombok.*;


import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplyCartTM {
    private String partId;
    private String partName;
    private int cartQuantity;
    private double unitPrice;
    private double totalPrice;
    private Button removeButton;
}
