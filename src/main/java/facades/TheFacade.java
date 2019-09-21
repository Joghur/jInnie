package facades;

import entities.Customer;
import entities.ItemType;
import entities.MasterData;
import entities.OrderLine;
import entities.Ordrer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;
import utils.pdfMaker;

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
     * Create Order
     */
    public Ordrer createNewOrder(int customerID) {
        EntityManager em = emf.createEntityManager();
        try {
            Ordrer o = new Ordrer();
            Customer c = em.find(Customer.class, customerID);
            o.setCustomer(c);
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }

    /**
     * Create Order
     */
    public Ordrer updateOrder(int orderID, Ordrer orig) {
        Ordrer e = new Ordrer();
        e.setInvoiceDate(orig.getInvoiceDate());
        e.setWorkDoneDate(orig.getWorkDoneDate());
        e.setCustomer(orig.getCustomer());
        e.setOrderState(orig.getOrderState());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ordrer o = em.find(Ordrer.class, orderID);
            o.setInvoiceDate(e.getInvoiceDate());
            o.setWorkDoneDate(e.getWorkDoneDate());
            o.setCustomer(e.getCustomer());
            o.setOrderState(e.getOrderState());
            em.persist(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }

    /**
     * Create Order
     */
    public MasterData createMasterData(String name, String address, String phone, String email, String cvr, String bank, String account) {
        MasterData m = new MasterData(name, address, phone, email, cvr, bank, account);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return m;
        } finally {
            em.close();
        }
    }

    public MasterData getMasterData() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MasterData> num = em.createQuery("Select c from MasterData c", MasterData.class);
            return (MasterData) num.getResultList().get(0);
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

    public static void main(String[] args) throws IOException {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        TheFacade facade =   TheFacade.getTheFacade(emf);

        pdfMaker pdf = new pdfMaker();
        List<String> orderLineTextList = new ArrayList();

//      Find all Orders, for a specific Customer
        List<Ordrer> oList = facade.getAllOrdersPerCustomer(1);
        System.out.println("\nAll orders from customer #1:");
        double totalPrice = 0;
        Ordrer or = oList.get(0);
        or.setWorkDoneDate(LocalDate.of(2019, Month.SEPTEMBER, 17));
        or.setInvoiceDate(LocalDate.of(2019, Month.SEPTEMBER, 21));
        facade.updateOrder(or.getOrdrerID(), or);
        System.out.println("Order #" + or.getOrdrerID() + " - " + or.getCustomer());

//      Find the total price of an order   
        List<OrderLine> ol1List = facade.getTotalOrderPrice(or.getOrdrerID());

        System.out.println("Orderline price:");
        for (OrderLine orderLine : ol1List) {
            int quantity = orderLine.getQuantity();
            double price = orderLine.getItemType().getPrice();
            totalPrice += quantity * price;
            String text = orderLine.getItemType().getName()
                    + "                              " + or.getWorkDoneDate()
                    + "   " + quantity
                    + "       " + String.format("%.2f", price);
            orderLineTextList.add(text);
            orderLineTextList.add(orderLine.getItemType().getDescription());
            
            System.out.println(text);
        }

        System.out.println("Order total price: " + totalPrice + " kr.");
        pdf.invoicePDFFlow(facade.getMasterData(), orderLineTextList,
                facade.findCustomer(1), or, totalPrice);
    }
}
