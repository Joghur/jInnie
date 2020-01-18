package rest;

import dto.ItemTypeDTO;
import entities.ItemType;
import facades.ItemTypeFacade;
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
@Path("itemtype")
public class ItemTypeResource {

    private static final EntityManagerFactory EMF = EMF_Creator
            .createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final ItemTypeFacade FACADE = ItemTypeFacade.getItemTypeFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTypeDTO> getAllOrders() {
        List<ItemType> list = FACADE.findAllItemTypes();
        List<ItemTypeDTO> dtoList = new ArrayList();
        for (ItemType object : list) {
            dtoList.add(new ItemTypeDTO(object));
        }
        return dtoList;
    }

    @POST
    @Path("new")
    @RolesAllowed({"user", "admin"})
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ItemTypeDTO createItemType(ItemTypeDTO item) {
        return FACADE.createItemType(item.getName(), item.getDescription(), item.getPrice());
    }

    @PUT
    @Path("edit")
    @RolesAllowed({"user", "admin"})
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ItemTypeDTO editPerson(ItemTypeDTO changedItem) throws WebApplicationException {
        return FACADE.editItemType(changedItem);
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") int id) throws WebApplicationException {
        FACADE.deleteItemType(id);
        return "{\"Status\":\"Person deleted\"}";
    }
}
