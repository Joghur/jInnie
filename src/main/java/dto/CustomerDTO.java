package dto;

import entities.Customer;

/**
 *
 * @author martin
 */
public class CustomerDTO {

    private Integer customerID;
    private Integer customerNumber;
    private String customerFirmName;
    private String customerFirmAddress;
    private String customerContactName;
    private String customerContactEmail;
    private String customerContactPhone;

    public CustomerDTO(Customer m) {
        this.customerID = m.getCustomerID();
        this.customerNumber = m.getCustomerNumber();
        this.customerFirmName = m.getCustomerFirmName();
        this.customerFirmAddress = m.getCustomerFirmAddress();
        this.customerContactName = m.getCustomerContactName();
        this.customerContactEmail = m.getCustomerContactEmail();
        this.customerContactPhone = m.getCustomerContactPhone();
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerFirmName() {
        return customerFirmName;
    }

    public void setCustomerFirmName(String customerFirmName) {
        this.customerFirmName = customerFirmName;
    }

    public String getCustomerFirmAddress() {
        return customerFirmAddress;
    }

    public void setCustomerFirmAddress(String customerFirmAddress) {
        this.customerFirmAddress = customerFirmAddress;
    }

    public String getCustomerContactName() {
        return customerContactName;
    }

    public void setCustomerContactName(String customerContactName) {
        this.customerContactName = customerContactName;
    }

    public String getCustomerContactEmail() {
        return customerContactEmail;
    }

    public void setCustomerContactEmail(String customerContactEmail) {
        this.customerContactEmail = customerContactEmail;
    }

    public String getCustomerContactPhone() {
        return customerContactPhone;
    }

    public void setCustomerContactPhone(String customerContactPhone) {
        this.customerContactPhone = customerContactPhone;
    }


}
