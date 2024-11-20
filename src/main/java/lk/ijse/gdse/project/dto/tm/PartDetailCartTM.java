package lk.ijse.gdse.project.dto.tm;

import lombok.*;

import javafx.scene.control.Button;
import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PartDetailCartTM {
    private String partId;
    private String partName;
    private int cartQuantity;
    private double unitPrice;
    private double totalPrice;
    private Button removeButton;
}
