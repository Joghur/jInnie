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
public class OrderDTO {

    private Integer ordrerID;
    private Integer invoiceID;
    private Integer customerID;
    private String invoiceDate;
    private String workDoneDate;
    private OrderState orderState;
    private List<OrderLine> orderLines;

    public OrderDTO() {
    }
    
    public OrderDTO(Ordrer m) {
        this.ordrerID = m.getOrdrerID();
        this.invoiceID = m.getInvoiceID();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLL-yyyy");
        this.invoiceDate = m.getInvoiceDate().format(formatter);
        this.workDoneDate = m.getWorkDoneDate().format(formatter);
        this.orderState = m.getOrderState();
        this.orderLines = m.getOrderLines();
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

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
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
