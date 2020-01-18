package facades;

import dto.CustomerDTO;
import entities.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emfCustomer;

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
            emfCustomer = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emfCustomer.createEntityManager();
    }

    /**
     * ITEMTYPES
     *
     * @param customerContactName
     */
    public CustomerDTO createCustomer(CustomerDTO dto)
            throws WebApplicationException {

        EntityManager em = emfCustomer.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = new Customer(
                    dto.getCustomerFirmName(),
                    dto.getCustomerFirmAddress(),
                    dto.getCustomerContactName(),
                    dto.getCustomerContactEmail(),
                    dto.getCustomerContactPhone()
            );
            em.persist(c);
            em.getTransaction().commit();
            return new CustomerDTO(c);
        } finally {
            em.close();
        }
    }

    public CustomerDTO editCustomer(CustomerDTO item) throws WebApplicationException {
        EntityManager em = emfCustomer.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = em.find(Customer.class, item.getCustomerID());
            if (item.getCustomerFirmName() != null && !item.getCustomerFirmName().isEmpty()) {
                c.setCustomerFirmName(item.getCustomerFirmName());
            }

            if (item.getCustomerFirmAddress() != null && !item.getCustomerFirmAddress().isEmpty()) {
                c.setCustomerFirmAddress(item.getCustomerFirmAddress());
            }

            if (item.getCustomerContactEmail() != null && !item.getCustomerContactEmail().isEmpty()) {
                c.setCustomerContactEmail(item.getCustomerContactEmail());
            }

            if (item.getCustomerContactName() != null && !item.getCustomerContactName().isEmpty()) {
                c.setCustomerContactName(item.getCustomerContactName());
            }

            if (item.getCustomerContactPhone() != null && !item.getCustomerContactPhone().isEmpty()) {
                c.setCustomerContactPhone(item.getCustomerContactPhone());
            }
            em.merge(c);
            em.getTransaction().commit();
            return new CustomerDTO(c);
        } finally {
            em.close();
        }
    }

    public void deleteCustomer(int id) throws WebApplicationException {
        EntityManager em = emfCustomer.createEntityManager();
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
        EntityManager em = emfCustomer.createEntityManager();
        try {
            Customer c = em.find(Customer.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> findAllCustomers() {
        EntityManager em = emfCustomer.createEntityManager();
        List<Customer> entList = new ArrayList();
        List<CustomerDTO> dtoList = new ArrayList();

        try {
            TypedQuery<Customer> list = em.createQuery("SELECT s FROM Customer s", Customer.class);
            entList = list.getResultList();
            if (entList.size() > 0) {
                for (Customer object : entList) {
                    dtoList.add(new CustomerDTO(object));
                }
            }
            return dtoList;
        } finally {
            em.close();
        }
    }
//
//    public static void main(String[] args) throws IOException {
//        emfCustomer = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
//        CustomerFacade facade = CustomerFacade.getCustomerFacade(emfCustomer);
//    }
}
