package dto;

import entities.Ordrer;
import enumeration.OrderState;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author martin
 */
public class OrderDTO {

    private Integer ordrerID;
    private Integer invoiceID;
    private String invoiceDate;
    private String workDoneDate;
    private OrderState orderState;;

    public OrderDTO(Ordrer m) {
        this.ordrerID = m.getOrdrerID();
        this.invoiceID = m.getInvoiceID();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLL-yyyy");
        this.invoiceDate = m.getInvoiceDate().format(formatter);
        this.workDoneDate = m.getWorkDoneDate().format(formatter);
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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getWorkDoneDate() {
        return workDoneDate;
    }

    public void setWorkDoneDate(String workDoneDate) {
        this.workDoneDate = workDoneDate;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

}
