package facades;

import dto.OrderDTO;
import dto.OrderLinePOSTDTO;
import dto.OrderPOSTDTO;
import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Ordrer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import utils.EMF_Creator;
import utils.pdfMaker;

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

    public OrderDTO createOrder(OrderPOSTDTO oDTO, CustomerFacade CUSTFACADE)
            throws WebApplicationException {
        //      List<OrderLinePOSTDTO> orderLines
        Customer c = CUSTFACADE.findCustomer(oDTO.getCustomerID());

        System.out.println(
                "oDTO.getWorkDoneDate(): " + oDTO.getWorkDoneDate());
//        List<OrderLine> newOL = new ArrayList();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Ordrer o = new Ordrer();
            o.setCustomer(c);
            o.setWorkDoneDate(oDTO.getWorkDoneDate());
//            o.setOrderLines(oDTO.getOrderLines());
            o.setInvoiceID(o.hashCode());
            em.persist(o);
            em.getTransaction().commit();

            //Updating invoice number and add orderlines
            for (OrderLinePOSTDTO orderLine : oDTO.getOrderLines()) {
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
            Ordrer o = em.find(Ordrer.class,
                    orderID);
            ItemType it = em.find(ItemType.class,
                    itemTypeID);
            OrderLine ol = new OrderLine(orderLineQuantity);
            ol.setItemType(it);
            em.persist(ol);
            em.getTransaction().commit();
            em.getTransaction().begin();
            o.addOrderLine(ol);
            em.merge(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }

    public OrderDTO editOrder(OrderDTO item) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ordrer c = em.find(Ordrer.class, item.getOrdrerID());
            c.setOrderState(item.getOrderState());
            em.merge(c);
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
            Ordrer p = em.find(Ordrer.class,
                    id);
            TypedQuery<OrderLine> ol = em.createNamedQuery("OrderLine.findByOrderID", OrderLine.class
            ).setParameter("ordrerID", p.getOrdrerID());
            if (ol.getResultList() != null) {
                for (OrderLine orderLine : ol.getResultList()) {
                    em.remove(orderLine);
                }
            }
            em.getTransaction().commit();
            em.getTransaction().begin();
            if (p != null) {
                em.remove(p);
            } else {
                throw new WebApplicationException("Could not delete, provided id does not exist");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Ordrer findOrder(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ordrer c = em.find(Ordrer.class,
                    id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<Ordrer> findAllOrders() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ordrer> num = em.createNamedQuery("Ordrer.findAll", Ordrer.class
            );
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
            ItemType c = em.find(ItemType.class,
                    id);
            return c;
        } finally {
            em.close();
        }
    }

    public ByteArrayOutputStream makePDF(int customerID, int ordrerID) throws IOException {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        TheFacade facade = TheFacade.getTheFacade(emf);
        System.out.println("Finding all orders:");
        System.out.println(facade.findAllOrders());

        pdfMaker pdf = new pdfMaker();
        List<String> orderLineTextList = new ArrayList();

//      Find all Orders, for a specific Customer
        List<Ordrer> oList = facade.findAllOrdersPerCustomer(customerID);
        System.out.println("\nAll orders from customer #" + customerID + ":");
        double totalPrice = 0;
        Ordrer or = null;
        for (Ordrer ordrer : oList) {
            if (ordrerID == ordrer.getOrdrerID()) {
                or = ordrer;
            }
        }
//        or.setWorkDoneDate(LocalDate.of(2019, Month.SEPTEMBER, 17));
//        or.setInvoiceDate(LocalDate.of(2019, Month.SEPTEMBER, 21));
//        facade.updateOrder(or.getOrdrerID(), or);
        System.out.println("Order #" + or.getOrdrerID() + " - " + or.getCustomer());

//      Find the total price of an order   
        List<OrderLine> ol1List = facade.findTotalOrderLineList(or.getOrdrerID());

        System.out.println("Orderline price:");
        for (OrderLine orderLine : ol1List) {
            int quantity = orderLine.getQuantity();
            double price = orderLine.getItemType().getPrice();
            totalPrice += quantity * price;
            StringBuilder text = new StringBuilder();
            text.append(orderLine.getItemType().getName());
            for (int i = 0; i < 48 - orderLine.getItemType().getName().length(); i++) {
                text.append(" ");
            }
            text.append(orderLine.getOrderLineDoneDate());
            for (int i = 0; i < 13 - orderLine.getOrderLineDoneDate().length(); i++) {
                text.append(" ");
            }
            text.append(quantity);

            for (int i = 0; i < 8 - String.valueOf(quantity).length(); i++) {
                text.append(" ");
            }
            text.append(String.format("%.2f", price));

//            String text = orderLine.getItemType().getName()
//                    + "                    " + orderLine.getOrderLineDoneDate()
//                    + "  " + quantity
//                    + "       " + String.format("%.2f", price);
            orderLineTextList.add(text.toString());
            orderLineTextList.add(orderLine.getItemType().getDescription());

            System.out.println(text);
        }

        System.out.println("Order total price: " + totalPrice + " kr.");
        return pdf.invoicePDFFlow(facade.getMasterData(), orderLineTextList,
                facade.findCustomer(customerID), or, (float) totalPrice);

    }

}
