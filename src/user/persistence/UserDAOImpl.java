package user.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import user.logic.Role;
import user.logic.User;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl uniqueInstance ;

	private static final String PERSISTENCE_UNIT_NAME = "OOD" ;
	private EntityManagerFactory factory ;
	
	public UserDAOImpl() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	@Override
	public ArrayList<User> getAllUsers() {
		EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT user FROM User user");
		List<User> userList = query.getResultList();
        ArrayList<User> userArrayList = new ArrayList<User>() ;
        for (User user : userList) {
        	userArrayList.add(user) ;
        }

		return userArrayList ;
	}

	@Override
	public User getUserByUsername(String username) {
		EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username");
		query.setParameter("username" , username) ;
		User user = (User) query.getSingleResult() ;
		return user ;
	}
		
	@Override
	public String addUser( String username , String name , String password , Role role ) {
		User user = new User( username ) ;
		user.setName(name);
		user.setPassword(password);
		user.setRole(role);
		return this.addUserToDataBase(user);
	}
	
	private String addUserToDataBase( User user ) { 
		if( this.existUser(user) )
			return "username already exists" ;
        EntityManager entityManager = factory.createEntityManager() ;
        entityManager.getTransaction().begin() ;
        entityManager.persist(user) ;
        entityManager.getTransaction().commit() ;
        entityManager.close();
        
        return "user created successfully" ;
	}
	
	private boolean existUser( User user ) {
		String username = user.getUsername() ;
        EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username");
		query.setParameter("username" , username) ;
		if( query.getResultList().size() > 0 )
		{
			return true ;
		}
		return false ;
	}

	@Override
	public String logIn( String username , String password ) {
        EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username AND user.password = :password");
		query.setParameter("username" , username) ;
		query.setParameter("password", password) ;
		User user = (User) query.getSingleResult() ;
		if( user != null )
		{
			user.setLoggedIn( true );
			return "user logged in successfully" ;
		}
		return "log in failed" ;
	}

	@Override
	public String logOut( String username ) {
        EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username");
		query.setParameter("username" , username) ;
		User user = (User) query.getSingleResult() ;
		
		if( user != null )
		{
			if( user.isLoggedIn() )
			{
				user.setLoggedIn(false);
				return "user logged out successfully" ;
			}
			else
				return "user is not logged in" ;
		}
		
		return "username does not exist" ; 
	}
	
	@Override
	public String deleteUser(String username) {
        EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username");
		query.setParameter("username" , username) ;
		User user = (User) query.getSingleResult() ;
		
		if( user == null )
			return "user does not exist" ;
		else
		{
			entityManager.remove(user) ;
			entityManager.getTransaction().commit() ;
			entityManager.close() ;
			return "user deleted successfully" ;
		}
		
	}

	public static UserDAOImpl getUniqueInstance() {
		if( uniqueInstance != null )
			return uniqueInstance;
		else
		{
			UserDAOImpl.setUniqueInstance( new UserDAOImpl() ) ;
			return uniqueInstance;
		}
	}

	public static void setUniqueInstance(UserDAOImpl userDAOImpl) {
		UserDAOImpl.uniqueInstance = userDAOImpl ;
	}

	public void closeFactory() {
		this.factory.close() ;
	}
}
