package dto;

import entities.Ordrer;
import enumeration.OrderState;
import java.time.LocalDate;

/**
 *
 * @author martin
 */
public class OrderDTO {

    private Integer ordrerID;
    private Integer invoiceID;
    private LocalDate invoiceDate;
    private LocalDate workDoneDate;
    private OrderState orderState;;

    public OrderDTO(Ordrer m) {
        this.ordrerID = m.getOrdrerID();
        this.invoiceID = m.getInvoiceID();
        this.invoiceDate = m.getInvoiceDate();
        this.workDoneDate = m.getWorkDoneDate();
        this.orderState = m.getOrderState();
    }

    public Integer getOrdrerID() {
        return ordrerID;
    }

    public void setOrdrerID(Integer ordrerID) {
        this.ordrerID = ordrerID;
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

}
