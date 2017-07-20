package user.persistence;

import java.util.ArrayList;

import user.logic.Role;
import user.logic.User;

public interface RoleDAO {
	public ArrayList<User> getAllRoles() ;
	public User getUserByUsername( String username ) ;
	public String addRole( Role role ) ;
	public String deleteRole( Role role ) ;
}
