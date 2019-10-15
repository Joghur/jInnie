package facades;

import dto.OrderDTO;
import entities.Customer;
import entities.OrderLine;
import entities.Ordrer;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class OrderFacade {

    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private OrderFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static OrderFacade getOrderFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * ITEMTYPES
     */
    public OrderDTO createOrder(LocalDate workDoneDate, Customer c, List<OrderLine> orderLines)
            throws WebApplicationException {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ordrer o = new Ordrer();
            o.setCustomer(c);
            o.setWorkDoneDate(workDoneDate);
            orderLines.forEach((orderLine) -> {
                o.addOrderLine(orderLine);
            });
            em.persist(o);
            em.getTransaction().commit();
            return new OrderDTO(o);
        } finally {
            em.close();
        }
    }

    public OrderDTO editOrder(int id, OrderDTO item) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ordrer c = em.find(Ordrer.class, id);
            c.setOrderState(item.getOrderState());
            em.persist(c);
            em.getTransaction().commit();
            return new OrderDTO(c);
        } finally {
            em.close();
        }
    }

    public void deleteOrder(int id) throws WebApplicationException {
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

    public Ordrer findOrder(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ordrer c = em.find(Ordrer.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<Ordrer> findAllOrders() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ordrer> num = em.createNamedQuery("Ordrer.findAll", Ordrer.class);
            return num.getResultList();
        } finally {
            em.close();
        }
    }

}
