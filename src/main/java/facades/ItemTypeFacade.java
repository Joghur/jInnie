package facades;

import dto.ItemTypeDTO;
import entities.ItemType;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class ItemTypeFacade {

    private static ItemTypeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ItemTypeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ItemTypeFacade getItemTypeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemTypeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * ITEMTYPES
     */
    public ItemTypeDTO createItemType(String name, String description, Float price) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemType c = new ItemType(name, description, price);
            em.persist(c);
            em.getTransaction().commit();
            return new ItemTypeDTO(c);
        } finally {
            em.close();
        }
    }

    public ItemTypeDTO editItemType(ItemTypeDTO item) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemType c = em.find(ItemType.class, item.getItemTypeID());
            if (item.getName() != null && !item.getName().isEmpty()) {
                c.setName(item.getName());
            }
            if (item.getDescription() != null && !item.getDescription().isEmpty()) {
                c.setDescription(item.getDescription());
            }
            if (item.getPrice() > 0.0) {
                c.setPrice(item.getPrice());
            }
            em.merge(c);
            em.getTransaction().commit();
            return new ItemTypeDTO(c);
        } finally {
            em.close();
        }
    }

    public void deleteItemType(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemType p = em.find(ItemType.class, id);
            if (p == null) {
                throw new WebApplicationException("Could not delete, provided id does not exist");
            }
            em.remove(p);
            em.getTransaction().commit();
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

    public List<ItemType> findAllItemTypes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ItemType> num = em.createNamedQuery("ItemType.findAll", ItemType.class);
            return num.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) throws IOException {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        ItemTypeFacade facade = ItemTypeFacade.getItemTypeFacade(emf);
    }
}
