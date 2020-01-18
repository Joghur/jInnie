package dto;

import entities.OrderLine;
import entities.Ordrer;
import enumeration.OrderState;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private float totalPrice = 0;
    private List<OrderLineDTO> orderLines = new ArrayList();
//    private final Locale dk = new Locale("da", "DK");

    public OrderDTO() {
    }

    public OrderDTO(Ordrer m) {
        this.ordrerID = m.getOrdrerID();
        this.invoiceID = m.getInvoiceID();
        this.customerID = m.getCustomer().getCustomerID();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLL-yyyy", dk);
        this.invoiceDate = m.getInvoiceDate();
        this.workDoneDate = m.getWorkDoneDate();
        this.orderState = m.getOrderState();
        for (OrderLine orderLine : m.getOrderLines()) {
            if (orderLine != null) {
                OrderLineDTO or = new OrderLineDTO(orderLine);
                this.orderLines.add(or);
                this.totalPrice += or.getQuantity() * or.getItemtype().getPrice();
            }
        }
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

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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
