package entities;

import enumeration.OrderState;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Ordrer.deleteAllRows", query = "DELETE from Ordrer"),
    @NamedQuery(name = "Ordrer.findAll", query = "SELECT s FROM Ordrer s"),
    @NamedQuery(name = "Ordrer.findOrdrerID", query = "SELECT s FROM Ordrer s WHERE s.ordrerID = :ordrerID"),
    @NamedQuery(name = "Ordrer.findInvoiceID", query = "SELECT s FROM Ordrer s WHERE s.invoiceID = :invoiceID"),
    @NamedQuery(name = "Ordrer.findInvoiceDate", query = "SELECT s FROM Ordrer s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "Ordrer.findWorkDoneDate", query = "SELECT s FROM Ordrer s WHERE s.workDoneDate = :workDoneDate"),
    @NamedQuery(name = "Ordrer.findOrderState", query = "SELECT s FROM Ordrer s WHERE s.orderState = :orderState")})
public class Ordrer implements Serializable { //spelled like that to avoid possible DB mixup. Order is reserved

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordrerID;

    private Integer invoiceID;
    private LocalDate invoiceDate;
    private LocalDate workDoneDate;
    private OrderState orderState;

    @OneToMany(mappedBy = "ordrer", cascade = CascadeType.PERSIST)
//    @ElementCollection
    private List<OrderLine> orderLines = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    public Ordrer() {
        this.orderState = OrderState.ORDER_RECEIVED;
        this.invoiceID = this.hashCode();
        this.invoiceDate = LocalDate.now();
    }

    public Ordrer(LocalDate invoiceDate) {
    }

    public Integer getOrdrerID() {
        return ordrerID;
    }

    public void setOrderID(Integer orderID) {
        this.ordrerID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getWorkDoneDate() {
        return workDoneDate;
    }

    public void setWorkDoneDate(LocalDate workDoneDate) {
        this.workDoneDate = workDoneDate;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLine(OrderLine ol) {
        this.orderLines.add(ol);
        if (ol.getOrdrer() != this) {
            ol.setOrdrer(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ordrerID);
        hash = 97 * hash + Objects.hashCode(this.customer);
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
        final Ordrer other = (Ordrer) obj;
        if (!Objects.equals(this.ordrerID, other.ordrerID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ordrer{" + "ordrerID=" + ordrerID + ", invoiceID=" + invoiceID + ", invoiceDate=" + invoiceDate + ", workDoneDate=" + workDoneDate + ", orderState=" + orderState + ", orderLines=" + orderLines + ", customer=" + customer + '}';
    }

}
