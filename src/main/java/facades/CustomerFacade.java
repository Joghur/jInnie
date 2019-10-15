package facades;

import dto.CustomerDTO;
import dto.ItemTypeDTO;
import entities.Customer;
import entities.ItemType;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * ITEMTYPES
     */
    public CustomerDTO createCustomer(
            String customerFirmName,
            String customerFirmAddress,
            String customerContactName,
            String customerContactEmail,
            String customerContactPhone)
            throws WebApplicationException {
        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = new Customer(
                    customerFirmName,
                    customerFirmAddress,
                    customerContactName,
                    customerContactEmail,
                    customerContactPhone
            );
            em.persist(c);
            em.getTransaction().commit();
            return new CustomerDTO(c);
        } finally {
            em.close();
        }
    }

    public CustomerDTO editCustomer(int id, CustomerDTO item) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = em.find(Customer.class, id);
            c.setCustomerFirmName(item.getCustomerFirmName());
            c.setCustomerFirmAddress(item.getCustomerFirmAddress());
            c.setCustomerContactEmail(item.getCustomerContactEmail());
            c.setCustomerContactName(item.getCustomerContactName());
            c.setCustomerContactPhone(item.getCustomerContactPhone());
            em.persist(c);
            em.getTransaction().commit();
            return new CustomerDTO(c);
        } finally {
            em.close();
        }
    }

    public void deleteCustomer(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer p = em.find(Customer.class, id);
            if (p == null) {
                throw new WebApplicationException("Could not delete, provided id does not exist");
            }
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Customer findCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<Customer> findAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> num = em.createNamedQuery("Customer.findAll", Customer.class);
            return num.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) throws IOException {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    }
}
