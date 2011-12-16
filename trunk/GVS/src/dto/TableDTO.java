package dto;

import java.util.ArrayList;
import java.util.List;

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
	private List<OrderDTO> orderList;
	private int id;
	
	public TableDTO(Table table)
	{
		username = table.getUser().getName();
		orderList = new ArrayList<OrderDTO>();
		id = table.getId();
		for(Order order : table.getOrders())
		{
			orderList.add(new OrderDTO(order));
		}
	}
	
	public String getUsername()
	{
		return username;
	}

	public List<OrderDTO> getOrderList()
	{
		return orderList;
	}
	
	public int getId()
	{
		return id;
	}
}
