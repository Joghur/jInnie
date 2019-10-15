package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.deleteAllRows", query = "DELETE from Customer"),
    @NamedQuery(name = "Customer.findAll", query = "SELECT s FROM Customer s"),
    @NamedQuery(name = "Customer.findCustomerID", query = "SELECT s FROM Customer s WHERE s.customerID = :customerID"),
    @NamedQuery(name = "Customer.findCustomerNumber", query = "SELECT s FROM Customer s WHERE s.customerNumber = :customerNumber"),
    @NamedQuery(name = "Customer.findCustomerFirmName", query = "SELECT s FROM Customer s WHERE s.customerFirmName = :customerFirmName"),
    @NamedQuery(name = "Customer.findCustomerFirmAddress", query = "SELECT s FROM Customer s WHERE s.customerFirmAddress = :customerFirmAddress"),
    @NamedQuery(name = "Customer.findCustomerContactName", query = "SELECT s FROM Customer s WHERE s.customerContactName = :customerContactName"),
    @NamedQuery(name = "Customer.findCustomerContactEmail", query = "SELECT s FROM Customer s WHERE s.customerContactEmail = :customerContactEmail"),
    @NamedQuery(name = "Customer.findCustomerContactPhone", query = "SELECT s FROM Customer s WHERE s.customerContactPhone = :customerContactPhone")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;
    private Integer customerNumber;
    private String customerFirmName;
    private String customerFirmAddress;
    private String customerContactName;
    private String customerContactEmail;
    private String customerContactPhone;

//    @ElementCollection
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Ordrer> ordrers = new ArrayList();

    public Customer() {
    }

    public Customer(String customerFirmName, String customerFirmAddress, String customerContactName, String customerContactEmail, String customerContactPhone) {
        this.customerFirmName = customerFirmName;
        this.customerFirmAddress = customerFirmAddress;
        this.customerContactName = customerContactName;
        this.customerContactEmail = customerContactEmail;
        this.customerContactPhone = customerContactPhone;
        this.customerNumber = Integer.parseInt(customerContactPhone);
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
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

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<Ordrer> getOrdrers() {
        return ordrers;
    }

    public void addOrder(Ordrer o) {
        this.ordrers.add(o);
        if (o.getCustomer() != this) {
            o.setCustomer(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.customerID);
        hash = 71 * hash + Objects.hashCode(this.customerFirmName);
        hash = 71 * hash + Objects.hashCode(this.customerFirmAddress);
        hash = 71 * hash + Objects.hashCode(this.customerContactName);
        hash = 71 * hash + Objects.hashCode(this.customerContactEmail);
        hash = 71 * hash + Objects.hashCode(this.customerContactPhone);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.customerID, other.customerID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", customerFirmName=" + customerFirmName + ", customerFirmAddress=" + customerFirmAddress + ", customerContactName=" + customerContactName + ", customerContactEmail=" + customerContactEmail + ", customerContactPhone=" + customerContactPhone + ", ordrers=" + ordrers + '}';
    }

}
