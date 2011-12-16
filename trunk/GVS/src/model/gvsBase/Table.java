package model.gvsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Basiselement eines Tisches
 * 
 * @author ifw10142
 *
 */

public class Table {
	
	private int id;
	private User user;
	private List<Order> orders =  new ArrayList<Order>();
	
	/**
	 * Konstruktor
	 * 
	 * @param TableNr
	 * @param User
	 */
	public Table(int id, User user) {
		this.id = id;
		this.user = user;
	}
	
	
	/**
	 * Wenn Bene die Kommunikation startet dann kann er einen Tisch anfragen ohne damit die User zu kennen.
	 * Damit weiß ich welche Daten ich bekomme
	 * 
	 * @param TableNr
	 */
	public Table(int id) {
		this.id = id;
		this.user = null;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public List<Order> getOrders()
	{
		return this.orders;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setTableNr(int id) {
		this.id = id;
	}
	
	/**
	 * addOrder
	 * 
	 * Fügt eine Bestellung einem Tisch hinzu
	 * 
	 * @param b
	 */
	public void addOrder(Order b) {
		if(b != null){
			if(!b.getProducts().isEmpty()) {
				orders.add(b);
			}
		}
	}
	
	
	public void removeOrder(Order b) {
		if(b != null) {
			for(Order o : orders) {
				if(o.equals(b)) {
					orders.remove(o);
				}
			}
		}
	}
	
	
	/**
	 * toString
	 */
	public String toString() {
		String output = "Tisch: " + id + "\n" + user.toString()+"\n";
		for(Order b: orders) {
			output = output + "\n- - -\n";
			for(Product p: b.getProducts()) {
				output = output + p.toString() + "\n";
			}
		}
		return output;
	}
	
}
