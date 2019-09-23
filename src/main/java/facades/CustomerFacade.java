package facades;

import entities.Customer;
import entities.Ordrer;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
     * CUSTOMERS
     */
    public Customer createCustomer(String customerFirmName, String customerFirmAddress, String customerContactName, String customerContactEmail, String customerContactPhone) {
        Customer c = new Customer(customerFirmName, customerFirmAddress, customerContactName, customerContactEmail, customerContactPhone);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
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
            TypedQuery<Customer> num = em.createQuery("Select c from Customer c", Customer.class);
            return num.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * ORD(R)ERS
     */
    public Customer addOrdrerToCustomer(int customerID, int ordrerID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = em.find(Customer.class, customerID);
            Ordrer o = em.find(Ordrer.class, ordrerID);
            c.addOrder(o);
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }


    /**
     * Find all Orders, for a specific Customer
     */
    public List<Ordrer> findAllOrdersPerCustomer(int customerID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ordrer> num
                    = em.createQuery("SELECT o FROM Ordrer o JOIN o.customer c where c.customerID = :customerID",
                            Ordrer.class)
                            .setParameter("customerID", customerID);
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
