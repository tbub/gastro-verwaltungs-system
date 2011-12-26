package dto;

import java.util.ArrayList;
import java.util.Collection;
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
	private Collection<ProductDTO> products = new ArrayList<ProductDTO>();
	
	public OrderDTO(Order order)
	{
		this.id = order.getId();
		this.isCloesed = order.isClosed();
		this.productNames = new String[order.getProducts().size()];
		this.prices = new float[order.getProducts().size()];
		int index = 0;
		for(Product p : order.getProducts())
		{
			products.add(new ProductDTO(p));
			this.price += p.getPrice();
			prices[index] = p.getPrice();
			productNames[index++] = p.getName();
		}
	}
	
	public Collection<ProductDTO> getProducts()
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
