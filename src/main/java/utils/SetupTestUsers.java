package utils;

import entities.Role;
import entities.User;
import facades.TheFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

//    public void fill() {
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
//        EntityManager em = emf.createEntityManager();
//
//        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
//        // CHANGE the three passwords below, before you uncomment and execute the code below
//        // Also, either delete this file, when users are created or rename and add to .gitignore
//        // Whatever you do DO NOT COMMIT and PUSH with the real passwords
//        User user = new User("user", "test12");
//        User admin = new User("admin", "test12");
//        User both = new User("user_admin", "test12");
//
//        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test")) {
//            throw new UnsupportedOperationException("You have not changed the passwords");
//        }
//
//        em.getTransaction().begin();
//        Role userRole = new Role("user");
//        Role adminRole = new Role("admin");
//        user.addRole(userRole);
//        admin.addRole(adminRole);
//        both.addRole(userRole);
//        both.addRole(adminRole);
//        em.persist(userRole);
//        em.persist(adminRole);
//        em.persist(user);
//        em.persist(admin);
//        em.persist(both);
//        em.getTransaction().commit();
//        System.out.println("PW: " + user.getUserPass());
//        System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
//        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
//        System.out.println("Created TEST Users");
//    }
    public void fill() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        TheFacade facade = TheFacade.getTheFacade(emf);
        EntityManager em = emf.createEntityManager();

        System.out.println("\nAdding MasterData");
        facade.createMasterData("SanderSoft", "c/o Sander-Thomsen, Hjortevænget 604, 2980 Kokkedal", "26814687", "info@sandersoft.dk", "40696067", "Sparekassen Vendsyssel", "9070 2600021071");

    }

}