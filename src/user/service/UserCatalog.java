package user.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import user.logic.Role;
import user.logic.User;
import user.persistence.UserDAOImpl;

public class UserCatalog {
	
	private ArrayList<User> userList ;
	
	public UserCatalog() {
		this.userList = new ArrayList<User>() ;
	}
	
	public ArrayList<User> getAllUsers() {
		this.userList = UserDAOImpl.getUniqueInstance().getAllUsers() ;
		return this.userList ;
	}
	
	public String addUser( String username , String name , String password , Role role ) {
		return UserDAOImpl.getUniqueInstance().addUser( username , name , password , role ) ;
	}
	
	public String deleteUser( String username ) {
		return UserDAOImpl.getUniqueInstance().deleteUser(username) ;
	}

	public String logIn( String username , String password ) {
		return UserDAOImpl.getUniqueInstance().logIn(username, password) ;
	}
	
	public String logOut( String username ) {
		return UserDAOImpl.getUniqueInstance().logOut(username) ;
	}
	
	public User getUserByUsername( String username ) {
		return UserDAOImpl.getUniqueInstance().getUserByUsername(username) ;		
	}
	
	public void closeFactory() {
		UserDAOImpl.getUniqueInstance().closeFactory() ;
	}
	

	

}
