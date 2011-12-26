package model.gvsBase;

/**
 * 
 * @author Sebastian
 *
 * Kellnerklasse
 *
 */

public class User implements Comparable<User>
{

	private String name;
	private String password;
	private UserType type;
	
	public User(String name, String Password, UserType type) {
		this.name = name;
		this.password = Password;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public UserType getType()
	{
		return type;
	}
	
	@Override
	public String toString() {
		return "Name des Kellners: " + name;
	}

	@Override
	public int compareTo(User o)
	{
		return this.getName().compareTo(o.getName());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null && obj.getClass() == name.getClass())
		{
			return ((User)obj).getName().equals(name);
		}
		return false;
	}
}
