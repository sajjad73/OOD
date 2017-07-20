package user.persistence;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import user.logic.Role;
import user.logic.User;

public class RoleDAOImpl implements RoleDAO {

	private static RoleDAOImpl uniqueInstance ;

	private static final String PERSISTENCE_UNIT_NAME = "OOD" ;
	private EntityManagerFactory factory ;
	
	public RoleDAOImpl() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	@Override
	public ArrayList<User> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addRole(Role role) {
		if( this.existRole(role) )
			return "role exist" ;
        EntityManager entityManager = factory.createEntityManager() ;
        entityManager.getTransaction().begin() ;
        entityManager.persist(role) ;
        entityManager.getTransaction().commit() ;
        entityManager.close();
        return "role created successfully" ;
	}

	@Override
	public String deleteRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	public static RoleDAOImpl getUniqueInstance() {
		if( uniqueInstance != null )
			return uniqueInstance;
		else
		{
			RoleDAOImpl.setUniqueInstance( new RoleDAOImpl() ) ;
			return uniqueInstance;
		}
	}

	public static void setUniqueInstance(RoleDAOImpl uniqueInstance) {
		RoleDAOImpl.uniqueInstance = uniqueInstance;
	}
	
	private boolean existRole( Role role ) {
		String roleName = role.getName() ;
        EntityManager entityManager = factory.createEntityManager() ;

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT role FROM Role role WHERE role.name = :roleName");
		query.setParameter("roleName" , roleName ) ;
		if( query.getResultList().size() > 0 )
		{
			return true ;
		}
		return false ;
	}

}
