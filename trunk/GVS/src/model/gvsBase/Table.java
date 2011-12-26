package model.gvsBase;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Basiselement eines Tisches
 * 
 * @author ifw10142
 *
 */

public class Table implements Comparable<Table>
{
	
	private int id;
	private User user;
	private Collection<Order> orders =  new HashSet<Order>();
	
	/**
	 * Konstruktor
	 * 
	 * @param TableNr
	 * @param User
	 */
	public Table(int id, User user)
	{
		this.id = id;
		this.user = user;
	}
	
	
	/**
	 * Wenn Bene die Kommunikation startet dann kann er einen Tisch anfragen ohne damit die User zu kennen.
	 * Damit weiß ich welche Daten ich bekomme
	 * 
	 * @param TableNr
	 */
	public Table(int id) 
	{
		this.id = id;
		this.user = null;
	}
	
	public void setOrders(List<Order> orders) 
	{
		this.orders = orders;
	}
	
	public Collection<Order> getOrders()
	{
		return this.orders;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setTableNr(int id) 
	{
		this.id = id;
	}
	
	/**
	 * addOrder
	 * 
	 * Fügt eine Bestellung einem Tisch hinzu
	 * 
	 * @param b
	 */
	public void addOrder(Order b)
	{
		if(b != null)
		{
			if(!b.getProducts().isEmpty()) 
			{
				orders.add(b);
			}
		}
	}
	
	
	public Order removeOrder(Order b) 
	{
		orders.remove(b);
		return b;
	}
	
	public Order removeOrder(long orderId)
	{
		Order order = getOrder(orderId);
		return removeOrder(order);
	}
	
	
	/**
	 * toString
	 */
	public String toString() 
	{
		String output = "Tisch: " + id + "\n" + user.toString()+"\n";
		for(Order b: orders) 
		{
			output = output + "\n- - -\n";
			for(Product p: b.getProducts())
			{
				output = output + p.toString() + "\n";
			}
		}
		return output;
	}

	public boolean containsOrder(long orderId)
	{
		return getOrder(orderId) != null;
	}
	
	public Order getOrder(long orderId)
	{
		for(Order order : orders)
		{
			if(order.getId() == orderId)
			{
				return order;
			}
		}
		return null;
	}
	
	public int getId()
	{
		return id;
	}

	@Override
	public int compareTo(Table o)
	{
		if(o.getId() < this.getId())
		{
			return 1;
		}
		else if(o.getId() > this.getId())
		{
			return -1;
		}
		return 0;
	}
	
}
