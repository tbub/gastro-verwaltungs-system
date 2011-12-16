package model.gvsBase;

public enum UserType
{
	manger("manager"), bartender("bartender");
	
	private String name;
	private UserType(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
