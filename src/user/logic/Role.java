package user.logic;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	
	private String name ;
	private ArrayList<String> privileges ;
	
	public Role() {
		this.privileges = new ArrayList<String>() ;
	}
	
	public Role( String name ) {
		
		this.setName(name) ;
		this.privileges = new ArrayList<String>() ;
		
	}
	
	public void addPrivilege( String privilege ) {
		this.privileges.add( privilege ) ;
	}
	
	public void removePrivilege( String privilege ) {
		this.privileges.remove(privilege) ;
	}
	
	public boolean hasPrivilege( String privilege ) {
		return this.privileges.contains(privilege) ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
