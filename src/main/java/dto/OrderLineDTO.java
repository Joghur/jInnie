package dto;

import entities.OrderLine;
import entities.Ordrer;
import enumeration.OrderState;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author martin
 */
public class OrderLineDTO {

    private Integer orderLineID;
    private Integer quantity;
    private ItemTypeDTO itemtype;

    public OrderLineDTO() {
    }

    public OrderLineDTO(OrderLine m) {
        this.orderLineID = m.getOrderLineID();
        this.quantity = m.getQuantity();
        this.itemtype = new ItemTypeDTO(m.getItemType());
    }

    public Integer getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(Integer orderLineID) {
        this.orderLineID = orderLineID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemTypeDTO getItemtype() {
        return itemtype;
    }

    public void setItemtype(ItemTypeDTO itemtype) {
        this.itemtype = itemtype;
    }


}
