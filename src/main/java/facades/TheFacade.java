package facades;

import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Ordrer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class TheFacade {

    private static TheFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private TheFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static TheFacade getTheFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TheFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * CUSTOMERS
     */
    public Customer addCustomer(String customerFirmName, String customerFirmAddress, String customerContactName, String customerContactEmail, String customerContactPhone) {
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

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> num = em.createQuery("Select c from Customer c", Customer.class);
            return num.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * ITEMTYPES
     */
    public ItemType addItemType(String name, String description, double price) {
        ItemType c = new ItemType(name, description, price);
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

    public ItemType findItemType(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            ItemType c = em.find(ItemType.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<ItemType> getAllItemTypes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ItemType> num = em.createQuery("Select c from ItemType c", ItemType.class);
            return num.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * ORD(R)ERS
     */
    public Customer addOrdrerToCustomer(int id, Ordrer o) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = em.find(Customer.class, id);
            c.addOrder(o);
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    /**
     * ORD(R)ERS
     */
    public Ordrer findOrdrer(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ordrer c = em.find(Ordrer.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    /**
     * ORD(R)ERS
     */
    public Ordrer addOrderLineToOrder(int orderID, int orderLineQuantity, int itemTypeID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ordrer o = em.find(Ordrer.class, orderID);
            ItemType it = em.find(ItemType.class, itemTypeID);
            OrderLine ol = new OrderLine(orderLineQuantity);
            ol.setItemType(it);
            em.persist(ol);
            o.addOrderLine(ol);
            em.persist(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }

    /**
     * Find all Orders, for a specific Customer
     */
    public List<Ordrer> getAllOrdersPerCustomer(int customerID) {
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

    /**
     * Find the total price of an order
     */
    public List<OrderLine> getTotalOrderPrice(int orderID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<OrderLine> num
                    = em.createQuery("SELECT ol FROM OrderLine ol where ol.ordrer.ordrerID = :orderID",
                            OrderLine.class)
                            .setParameter("orderID", orderID);
            if (num.getResultList() != null) {
                return num.getResultList();
            }
            return null;
        } finally {
            em.close();
        }
    }

}
