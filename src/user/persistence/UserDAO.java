package user.persistence;

import java.util.ArrayList;

import user.logic.Role;
import user.logic.User;

public interface UserDAO {
	
	public ArrayList<User> getAllUsers() ;
	public User getUserByUsername( String username ) ;
	public String addUser( String username , String name , String password , Role role ) ;
	public String deleteUser( String username ) ;
	public String logIn( String username , String password ) ;
	public String logOut( String username ) ;

}
