package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "MasterData.deleteAllRows", query = "DELETE from MasterData")
public class MasterData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masterDataID;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String cvr;
    private String bank;
    private String account;
    
    
    public MasterData() {
    }

    public MasterData(String name, String address, String phone, String email, String cvr, String bank, String account) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.cvr = cvr;
        this.bank = bank;
        this.account = account;
    }

    public Integer getMasterDataID() {
        return masterDataID;
    }

    public void setMasterDataID(Integer masterDataID) {
        this.masterDataID = masterDataID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.masterDataID);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + Objects.hashCode(this.phone);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.cvr);
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
        final MasterData other = (MasterData) obj;
        if (!Objects.equals(this.masterDataID, other.masterDataID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MasterData{" + "masterDataID=" + masterDataID + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", cvr=" + cvr + ", bank=" + bank + ", account=" + account + '}';
    }

}
