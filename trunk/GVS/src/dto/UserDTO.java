package dto;

import model.gvsBase.User;

/**
 * 
 * @author Benedikt Zönnchen
 * DataTransferObjects dienen als Übergabeobjekte der Anwendungslogik an die Präsentationsschicht.
 * UserDTO steht für einen User. UserDTO ist ein unveränderbares Objekt.
 *
 */
public class UserDTO
{
	private String name;
	private String password;
	private UserTypeDTO type;
	
	public UserDTO(User user)
	{
		this.name = user.getName();
		this.password = user.getPassword();
		this.type = new UserTypeDTO(user.getType());
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName()
	{
		return name;
	}
	
	public UserTypeDTO getType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return "Name: " + name + "\nPassword: " + password + "\nTyp: " + type.toString();
	}
}

