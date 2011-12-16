package dto;

import model.gvsBase.Product;

public class ProductDTO
{
	private String name;
	private ProductTypeDTO type;
	private float price;
	
	public ProductDTO(Product product)
	{
		this.name = product.getName();
		this.price = product.getPrice();
		this.type = new ProductTypeDTO(product.getType());
	}
	
	public String getName()
	{
		return name;
	}
	
	public ProductTypeDTO getType()
	{
		return type;
	}
	
	public float getPrice()
	{
		return price;
	}
}
