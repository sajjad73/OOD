package user.service;

import java.util.ArrayList;

import user.logic.Role;
import user.persistence.RoleDAOImpl;

public class RoleCatalog {
	
	private ArrayList<Role> roleList ;

	public RoleCatalog() {
		this.roleList = new ArrayList<Role>() ;		
	}
	
	public String addRole(  Role role ) {
		return RoleDAOImpl.getUniqueInstance().addRole( role ) ;
	}
}
