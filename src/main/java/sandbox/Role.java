package sandbox;

public class Role {

	Integer id;
	String name;
	
	public Role(String string) {
		super();
		this.name = string;
	}
	
	public Role() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getString() {
		return name;
	}
	public void setString(String string) {
		this.name = string;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", string=" + name + "]";
	}
	
	
	

}
