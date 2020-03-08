package dto;

/**
 *
 * @author martin
 */
public class OrderLinePOSTDTO {
    
    private int itemTypeID;
    private int quantity;
    private String orderLineDoneDate;

    public OrderLinePOSTDTO() {
    }

    public OrderLinePOSTDTO(int itemTypeID, int quantity, String orderLineDoneDate) {
        this.itemTypeID = itemTypeID;
        this.quantity = quantity;
        this.orderLineDoneDate = orderLineDoneDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(int itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public String getOrderLineDoneDate() {
        return orderLineDoneDate;
    }

    public void setOrderLineDoneDate(String orderLineDoneDate) {
        this.orderLineDoneDate = orderLineDoneDate;
    }

    
    
}
