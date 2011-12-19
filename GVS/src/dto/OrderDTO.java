package dto;

import java.util.Set;

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
	private float[] prices;
	private float price;
	private long id;
	private boolean isCloesed;
	private Set<ProductDTO> products;
	
	public OrderDTO(Order order)
	{
		this.id = order.getId();
		this.isCloesed = order.isClosed();
		this.productNames = new String[order.getProducts().size()];
		int index = 0;
		for(Product p : order.getProducts())
		{
			products.add(new ProductDTO(p));
			this.price += p.getPrice();
			prices[index] = p.getPrice();
			productNames[index++] = p.getName();
		}
	}
	
	public Set<ProductDTO> getProducts()
	{
		return products;
	}
	
	public String[] getProductNames()
	{
		return productNames;
	}
	
	public float[] getPrices()
	{
		return prices;
	}

	public boolean isCloesed()
	{
		return isCloesed;
	}
	
	public float getPrice()
	{
		return price;
	}

	public long getId()
	{
		return id;
	}

}
