package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin
 */
public class OrderPOSTDTO {

    private Integer customerID;
    private String workDoneDate;
    private List<OrderLinePOSTDTO> orderLines = new ArrayList();

    public OrderPOSTDTO() {
    }

    public OrderPOSTDTO(Integer customerID, String workDoneDate, List<OrderLinePOSTDTO> orderLines) {
        this.customerID = customerID;
        this.workDoneDate = workDoneDate;
        this.orderLines = orderLines;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getWorkDoneDate() {
        return workDoneDate;
    }

    public void setWorkDoneDate(String workDoneDate) {
        this.workDoneDate = workDoneDate;
    }

    public List<OrderLinePOSTDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLinePOSTDTO> orderLines) {
        this.orderLines = orderLines;
    }

     
}
