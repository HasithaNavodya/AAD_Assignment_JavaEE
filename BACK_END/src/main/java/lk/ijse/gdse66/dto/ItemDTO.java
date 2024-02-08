package lk.ijse.gdse66.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {

    private String itemCode;
    private String itemDescription;
    private Double itemPrice;
    private int itemQty;
}
