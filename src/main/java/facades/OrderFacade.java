package facades;

import dto.OrderDTO;
import dto.OrderLinePOSTDTO;
import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Ordrer;
import java.time.LocalDate;
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

    public OrderDTO createOrder(LocalDate workDoneDate, Customer c, List<OrderLinePOSTDTO> orderLines)
            throws WebApplicationException {
        List<OrderLine> newOL = new ArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ordrer o = new Ordrer();
            o.setCustomer(c);
            o.setWorkDoneDate(workDoneDate);
            o.setOrderLines(newOL);
            em.persist(o);
            em.getTransaction().commit();

            //Updating invoice number and add orderlines
            em.getTransaction().begin();
            o.setInvoiceID(o.hashCode());
            em.getTransaction().commit();
            for (OrderLinePOSTDTO orderLine : orderLines) {
                addOrderLineToOrder(
                        o.getOrdrerID(),
                        orderLine.getQuantity(),
                        orderLine.getItemTypeID()
                );
            }

            return new OrderDTO(o);
        } finally {
            em.close();
        }
    }

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
            Ordrer p = em.find(Ordrer.class, id);
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

    /**
     * ITEMTYPES
     */
    public ItemType findItemType(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            ItemType c = em.find(ItemType.class, id);
            return c;
        } finally {
            em.close();
        }
    }
}
