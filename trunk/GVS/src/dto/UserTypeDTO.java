package dto;

import model.gvsBase.UserType;

public class UserTypeDTO
{
	private String name;
	
	public UserTypeDTO(UserType type)
	{
		this.name = type.toString();
	}
	
	public String getName()
	{
		return name;
	}
}
