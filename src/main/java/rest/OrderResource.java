package rest;

import dto.OrderDTO;
import dto.OrderPOSTDTO;
import entities.Ordrer;
import facades.CustomerFacade;
import facades.OrderFacade;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
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

    @GET
    @Path("all")
    @RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public List<OrderDTO> getAllOrders() {
        List<Ordrer> list = FACADE.findAllOrders();
        List<OrderDTO> dtoList = new ArrayList();
        for (Ordrer object : list) {
            dtoList.add(new OrderDTO(object));
        }
        return dtoList;
    }

    @POST
    @Path("new")
    @RolesAllowed({"user", "admin"})
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public OrderDTO createOrder(OrderPOSTDTO o) {
        return FACADE.createOrder(o, CUSTFACADE);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("edit")
    public OrderDTO editOrder(OrderDTO changedOrder) throws WebApplicationException {
        return FACADE.editOrder(changedOrder);
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteOrder(@PathParam("id") int id) throws WebApplicationException {
        FACADE.deleteOrder(id);
        return "{\"Status\":\"Order deleted\"}";
    }

    @GET
    @Path("pdf/download/{customerID}/{ordrerID}")
    @Produces("application/pdf")
    @RolesAllowed({"user", "admin"})
    public Response makeDownloadPDF(@PathParam("customerID") int customerID, @PathParam("ordrerID") int ordrerID) throws WebApplicationException, IOException {
        ResponseBuilder response = null;
        ByteArrayOutputStream output;
        try {
            output = FACADE.makePDF(customerID, ordrerID);

            response = Response.ok(output.toByteArray());
            response.header("Content-Disposition", "attachment; filename=sandersoft.pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response.build();
    }

    @GET
    @Path("pdf/show/{customerID}/{ordrerID}")
    @Produces("application/pdf")
    @RolesAllowed({"user", "admin"})
    public Response makeShowPDF(@PathParam("customerID") int customerID, @PathParam("ordrerID") int ordrerID) throws WebApplicationException, IOException {
        ResponseBuilder response = null;
        ByteArrayOutputStream output;
        try {
            output = FACADE.makePDF(customerID, ordrerID);

            response = Response.ok(output.toByteArray());
            response.header("Content-Disposition", "filename=sandersoft.pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response.build();
    }

}
