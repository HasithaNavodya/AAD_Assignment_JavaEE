package lk.ijse.gdse66.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Item {

    private String itemCode;
    private String itemDescription;
    private Double itemPrice;
    private int itemQty;

    public Item(String itemCode, int itemQty) {
        this.itemCode=itemCode;
        this.itemQty=itemQty;
    }
}
