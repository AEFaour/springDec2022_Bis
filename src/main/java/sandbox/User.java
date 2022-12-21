package sandbox;

import java.util.ArrayList;
import java.util.List;


public class User {
	
	String userName;
	String password;

	List<Role> roles = new ArrayList<Role>();
	
	public User() {	
	}
	
	public User(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		roles.add(new Role(role));
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}
	
}
