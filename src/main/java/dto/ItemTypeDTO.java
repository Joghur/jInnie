package dto;

import entities.ItemType;

/**
 *
 * @author martin
 */
public class ItemTypeDTO {

    private Integer itemTypeID;
    private String name;
    private String description;
    private Float price;

    public ItemTypeDTO() {
    }

    public ItemTypeDTO(ItemType m) {
        this.itemTypeID = m.getItemTypeID();
        this.name = m.getName();
        this.description = m.getDescription();
        this.price = m.getPrice();
    }

    public Integer getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(Integer itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    
}
