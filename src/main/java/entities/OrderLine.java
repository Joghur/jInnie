package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "OrderLine.findAll", query = "SELECT s FROM OrderLine s"),
    @NamedQuery(name = "OrderLine.findByOrderID", query = "SELECT s FROM OrderLine s where s.ordrer.ordrerID = :ordrerID"),
    @NamedQuery(name = "OrderLine.deleteAllRows", query = "DELETE from OrderLine")
})
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderLineID;
    private Integer quantity;
    private String orderLineDoneDate;

    private Ordrer ordrer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemTypeID")
    private ItemType itemType;

    public OrderLine() {
    }

    public OrderLine(Integer quantity) {
        this.quantity = quantity;
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

    public Ordrer getOrdrer() {
        return ordrer;
    }

    public void setOrdrer(Ordrer ordrer) {
        this.ordrer = ordrer;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getOrderLineDoneDate() {
        return orderLineDoneDate;
    }

    public void setOrderLineDoneDate(String orderLineDoneDate) {
        this.orderLineDoneDate = orderLineDoneDate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.orderLineID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderLine other = (OrderLine) obj;
        if (!Objects.equals(this.orderLineDoneDate, other.orderLineDoneDate)) {
            return false;
        }
        if (!Objects.equals(this.orderLineID, other.orderLineID)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.ordrer, other.ordrer)) {
            return false;
        }
        if (!Objects.equals(this.itemType, other.itemType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "orderLineID=" + orderLineID + ", quantity=" + quantity + ", orderLineDoneDate=" + orderLineDoneDate + ", ordrer=" + ordrer + ", itemType=" + itemType + '}';
    }

}
