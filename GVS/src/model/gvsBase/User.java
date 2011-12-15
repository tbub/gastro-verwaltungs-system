package model.gvsBase;

/**
 * 
 * @author Sebastian
 *
 * Kellnerklasse
 *
 */

public class User {

	String Name;
	String Password;
	
	public User(String Name, String Password) {
		this.Name = Name;
		this.Password = Password;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getPassword() {
		return Password;
	}
	
	@Override
	public String toString() {
		return "Name des Kellners: " + Name;
	}
}
