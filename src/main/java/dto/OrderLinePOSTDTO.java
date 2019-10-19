package dto;

/**
 *
 * @author martin
 */
public class OrderLinePOSTDTO {
    
    private int itemTypeID;
    private int quantity;

    public OrderLinePOSTDTO() {
    }

    public OrderLinePOSTDTO(int itemTypeID, int quantity) {
        this.itemTypeID = itemTypeID;
        this.quantity = quantity;
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

}
