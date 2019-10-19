package rest;

import dto.OrderDTO;
import dto.OrderPOSTDTO;
import entities.Customer;
import entities.Ordrer;
import facades.CustomerFacade;
import facades.OrderFacade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author martin
 */
@Path("order")
public class OrderResource {

    private static final EntityManagerFactory EMF = EMF_Creator
            .createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final OrderFacade FACADE = OrderFacade.getOrderFacade(EMF);
    private static final CustomerFacade CUSTFACADE = CustomerFacade.getCustomerFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Order\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<OrderDTO> getAllOrders() {
        List<Ordrer> list = FACADE.findAllOrders();
        List<OrderDTO> dtoList = new ArrayList();
        for (Ordrer object : list) {
            dtoList.add(new OrderDTO(object));
        }
        return dtoList;
    }

//    @POST
    @Path("new")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public OrderDTO createOrder(OrderPOSTDTO o) {
        System.out.println("o.getWorkDoneDate(): " + o.getWorkDoneDate());
        final Locale dk = new Locale("da", "DK");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLL-yyyy", dk);
        LocalDate localDate = LocalDate.parse(o.getWorkDoneDate(), formatter);
        Customer c = CUSTFACADE.findCustomer(o.getCustomerID());
        return FACADE.createOrder(
                localDate,
                c,
                o.getOrderLines());
    }

    //@PUT
//    @PUT
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("{id}")
//    public OrderDTO editOrder(OrderDTO changedOrder, @PathParam("id") int id) throws WebApplicationException {
//        return FACADE.editOrder(id, changedOrder);
//    }
    //@DELETE
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public String deleteOrder(@PathParam("id") int id) throws WebApplicationException {
        FACADE.deleteOrder(id);
        return "{\"Status\":\"Order deleted\"}";
    }
}
