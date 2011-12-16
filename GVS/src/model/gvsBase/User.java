package model.gvsBase;

/**
 * 
 * @author Sebastian
 *
 * Kellnerklasse
 *
 */

public class User {

	private String Name;
	private String Password;
	private UserType type;
	
	public User(String Name, String Password, UserType type) {
		this.Name = Name;
		this.Password = Password;
		this.type = type;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public UserType getType()
	{
		return type;
	}
	
	@Override
	public String toString() {
		return "Name des Kellners: " + Name;
	}
}
