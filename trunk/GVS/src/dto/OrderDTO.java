package dto;

import model.gvsBase.Order;
import model.gvsBase.Product;

/**
 * 
 * @author Benedikt Zönnchen
 * DataTransferObjects dienen als Übergabeobjekte der Anwendungslogik an die Präsentationsschicht.
 * OrderDTO steht für eine Bestellung. OrderDTO ist ein unveränderbares Objekt.
 *
 */
public class OrderDTO
{
	private String[] productNames;
	private float price;
	private int id;
	private boolean isCloesed;
	
	public OrderDTO(Order order)
	{
		this.id = order.getId();
		this.isCloesed = order.isClosed();
		this.productNames = new String[order.getProducts().size()];
		int index = 0;
		for(Product p : order.getProducts())
		{
			this.price += p.getPrice();
			productNames[index++] = p.getName();
		}
	}
	
	public String[] getProductNames()
	{
		return productNames;
	}

	public boolean isCloesed()
	{
		return isCloesed;
	}
	
	public float getPrice()
	{
		return price;
	}

	public int getId()
	{
		return id;
	}

}
