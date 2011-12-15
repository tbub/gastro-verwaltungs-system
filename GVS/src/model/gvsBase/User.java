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
	
	public User(String Name) {
		this.Name = Name;
	}
	
	public String getName() {
		return Name;
	}
	
	@Override
	public String toString() {
		return "Name des Kellners: " + Name;
	}
}
