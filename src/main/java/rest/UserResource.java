package rest;

import dto.CustomerDTO;
import entities.Customer;
import facades.CustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import utils.EMF_Creator;
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

/**
 *
 * @author martin
 */
@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator
            .createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final CustomerFacade FACADE = CustomerFacade.getCustomerFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello user\"}";
    }

//    @GET
//    @Path("all")
////    @RolesAllowed({"user", "admin"})
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<CustomerDTO> getAllCustomers() {
//        List<Customer> list = FACADE.findAllCustomers();
//        List<CustomerDTO> dtoList = new ArrayList();
//        for (Customer object : list) {
//            dtoList.add(new CustomerDTO(object));
//        }
//        return dtoList;
//    }

//    @POST
//    @Path("new")
////    @RolesAllowed({"user", "admin"})
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public CustomerDTO createItemType(CustomerDTO item) {
//        return FACADE.createCustomer(
//                item.getCustomerFirmName(),
//                item.getCustomerFirmAddress(),
//                item.getCustomerContactName(),
//                item.getCustomerContactEmail(),
//                item.getCustomerContactPhone()
//        );
//    }

//    @PUT
//    @Path("{id}")
////    @RolesAllowed({"user", "admin"})
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public CustomerDTO editCustomer(CustomerDTO changedItem, @PathParam("id") int id) throws WebApplicationException {
//        return FACADE.editCustomer(id, changedItem);
//    }

//    @DELETE
//    @Path("{id}")
////    @RolesAllowed({"user", "admin"})
//    @Produces({MediaType.APPLICATION_JSON})
//    public String deleteCustomer(@PathParam("id") int id) throws WebApplicationException {
//        FACADE.deleteCustomer(id);
//        return "{\"Status\":\"Order deleted\"}";
//    }
    
    
    
}
