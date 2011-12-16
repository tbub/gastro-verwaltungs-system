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
	
	public UserDTO(User user)
	{
		this.name = user.getName();
		this.password = user.getPassword();
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName()
	{
		return name;
	}
}

