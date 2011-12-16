package dto;

import model.gvsBase.ProductType;

public class ProductTypeDTO
{
	private String name;
	
	public ProductTypeDTO(ProductType type)
	{
		this.name = type.toString();
	}
	
	public String getName()
	{
		return name;
	}
}
