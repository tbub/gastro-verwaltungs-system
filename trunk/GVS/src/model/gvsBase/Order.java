package model.gvsBase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

/**
 * Basiselement einer Bestellung
 * 
 * @author ifw10142
 *
 */

public class Order {

	private List<Product> products;
	private boolean isClosed;
	private long orderID;
	// added by Basti
	private Date date;
	
	
	/**
	 * Konstruktor
	 */
	public Order() {
		products =  new ArrayList<Product>();
		 isClosed = false;
	}
	
	// added by basti, den brauch ich um aus meinen datensätzen die orderlist zu erstellen
	public Order(int orderID, boolean isClosed, Date date, List<Product> products) {
		super();
		this.date = date;
		this.products = products;
		this.isClosed = isClosed;
		this.orderID = orderID;
	}
	
	public void setProducts(ArrayList<Product> p) {
		products = p;
	}

	public boolean isClosed() {
		return isClosed;
	}
	
	/**
	 * close
	 * 
	 * @author Sebastian
	 */
	public void close() {
		isClosed = true;
	}
	
	/**
	 * open
	 * 
	 * @author Sebastian
	 */
	public void open() {
		isClosed = false;
	}
		
	/**
	 * addProduct()
	 * 
	 * fügt ein Produkt der Liste an Productsn, die zu einer Bestellung gehoeren, hinzu
	 * 
	 * @author Sebastian
	 * @param p	Produktobjekt das hinzugefügt wird
	 */
	public void addProdukt(Product p) {
		products.add(p);
	}
	
	
	/**
	 * removeProduct()
	 * 
	 * loescht ein Produkt aus der Liste der zugehörigen Products.
	 * Dafuer wird die Liste von hinten durchsucht und das erste Produkt
	 * das dem selben entspricht, wird geloescht, da es wahrscheinlich
	 * das aktuellste ist
	 * 
	 * @author Sebastian
	 * @param p	Produktobjekt das geloescht wird wird
	 */
	public void deleteProdukt(Product p) {
		for ( int i = products.size()-1; i >= 0 ; i--) {
			if(products.get(i).equals(p)) {
				products.remove(i);
				break;
			}
		}
	}
	
	/**
	 * clone
	 * 
	 * @author Sebastian
	 * @return 
	 */
	protected Object clone() {
		return null;
	}
	
	/**
	 * equals
	 * 
	 * @author Sebastian
	 * @param o
	 */
	public boolean equals(Order o) {
		int equalCounter = 0;
		if(o instanceof Order) {
			if(o.getClass() == this.getClass()) {
				for(Product p : o.getProducts()) {
					for(Product p1 : this.getProducts()) {
						if(p1 == p) {
							equalCounter++;
						}
					}
				}
			}
		}
		if(equalCounter == this.getProducts().size()) { return true; }
		else { return false; }
	}
	
	/**
	 * @author Benedikt
	 * @return orderID die Bestellungsnummer (eindeutige nummer)
	 */
	public long getId()
	{
		return orderID;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		String output = "";
		for (Product p: products) {
			output = output
			.concat(p.toString())
			.concat(System.getProperty("line.separator"));
		}
		return output;
	}

	// added von Basti
	public Element getDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
