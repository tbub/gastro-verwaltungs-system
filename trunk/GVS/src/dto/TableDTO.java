package dto;

import java.util.ArrayList;
import java.util.Collection;
import model.gvsBase.Order;
import model.gvsBase.Table;

/**
 * 
 * @author Benedikt Zönnchen
 * DataTransferObjects dienen als Übergabeobjekte der Anwendungslogik an die Präsentationsschicht.
 * TableDTO steht für einen Tisch. TableDTO ist ein unveränderbares Objekt.
 *
 */
public class TableDTO
{
	private String username;
	private Collection<OrderDTO> orderCollection;
	private int id;
	
	public TableDTO(Table table)
	{
		username = table.getUser().getName();
		orderCollection = new ArrayList<OrderDTO>();
		id = table.getId();
		for(Order order : table.getOrders())
		{
			orderCollection.add(new OrderDTO(order));
		}
	}
	
	public String getUsername()
	{
		return username;
	}

	public Collection<OrderDTO> getOrders()
	{
		return orderCollection;
	}
	
	public int getId()
	{
		return id;
	}
}
