package user.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import user.service.RoleCatalog;
import user.service.UserCatalog;


public class Main {
   

    public static void main(String[] args) {
    	Role role = new Role("role2") ;
        UserCatalog userCatalog = new UserCatalog() ;
        RoleCatalog roleCatalog = new RoleCatalog() ;
        roleCatalog.addRole(role);
        userCatalog.addUser("user4", "user2", "user1pass", role ) ;
        System.out.println(userCatalog.getAllUsers().size());
    //    userCatalog.closeFactory();
        // read the existing entries and write to console
        
        
        /*
        Query q = em.createQuery("select t from User t");
        List<User> todoList = q.getResultList();
        for (User todo : todoList) {
            System.out.println(todo.getName());
        }
        System.out.println("Size: " + todoList.size());

       
        // create new todo
        em.getTransaction().begin();
       // User todo = new User(6 , "user1" , "role1" );

        em.persist(todo);
        em.getTransaction().commit();
        
        factory.close();
      //  em.close();
       * */
       
        
    }
}