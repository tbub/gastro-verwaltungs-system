package model.gvsBase;

public enum ProductType
{
	food("food"), drink("drink");
	
	private String name;
	private ProductType(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
