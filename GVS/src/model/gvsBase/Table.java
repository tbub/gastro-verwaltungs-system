package model.gvsBase;

import java.util.ArrayList;

/**
 * Basiselement eines Tisches
 * 
 * @author ifw10142
 *
 */

public class Table {
	
	int TableNr;
	User User;
	ArrayList<Order> Orders =  new ArrayList<Order>();
	
	/**
	 * Konstruktor
	 * 
	 * @param TableNr
	 * @param User
	 */
	public Table(int TableNr, User User) {
		this.TableNr = TableNr;
		this.User = User;
	}
	
	
	/**
	 * Wenn Bene die Kommunikation startet dann kann er einen Tisch anfragen ohne damit die User zu kennen.
	 * Damit wei� ich welche Daten ich bekomme
	 * 
	 * @param TableNr
	 */
	public Table(int TableNr) {
		this.TableNr = TableNr;
		this.User = null;
	}
	
	public void setOrders(ArrayList<Order> Orders) {
		this.Orders = Orders;
	}
	
	public void setUser(User User) {
		this.User = User;
	}
	
	public void setTableNr(int TableNr) {
		this.TableNr = TableNr;
	}
	
	/**
	 * addOrder
	 * 
	 * F�gt eine Bestellung einem Tisch hinzu
	 * 
	 * @param b
	 */
	public void addOrder(Order b) {
		if(b != null){
			if(!b.getProducts().isEmpty()) {
				Orders.add(b);
			}
		}
	}
	
	
	public void removeOrder(Order b) {
		if(b != null) {
			for(Order o : Orders) {
				if(o.equals(b)) {
					Orders.remove(o);
				}
			}
		}
	}
	
	
	/**
	 * toString
	 */
	public String toString() {
		String output = "Tisch: " + TableNr + "\n" + User.toString()+"\n";
		for(Order b: Orders) {
			output = output + "\n- - -\n";
			for(Product p: b.getProducts()) {
				output = output + p.toString() + "\n";
			}
		}
		return output;
	}
	
}
