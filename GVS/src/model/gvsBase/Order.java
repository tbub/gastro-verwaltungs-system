package model.gvsBase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

/**
 * Basiselement einer Bestellung
 * 
 * @author ifw10142
 *
 */

public class Order implements Comparable<Order>
{	
	private Collection<Product> products = new ArrayList<Product>();;
	private boolean isClosed;
	private long orderID;
	// added by Basti
	private Date date;
	
	
	/**
	 * Konstruktor
	 */
	public Order(long id)
	{ 
		isClosed = false;
		date = new Date();
		this.orderID = id;
		System.out.println("create order" + this.toString());
	}
	
	// added by basti, den brauch ich um aus meinen datensätzen die orderlist zu erstellen
	public Order(long orderID, boolean isClosed, Date date, Collection<Product> products) 
	{
		super();
		this.date = date;
		this.products = products;
		this.isClosed = isClosed;
		this.orderID = orderID;
		System.out.println("create order" + this.toString());
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
	 * Bearbeitet durch: Benedikt Zönnchen 19.12.2011
	 * @author Sebastian
	 * @param p	Produktobjekt das geloescht wird wird
	 */
	public void deleteProdukt(Product p) {
		products.remove(p);
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
	 * Bearbeitet durch: Benedikt Zönnchen 19.12.2011
	 * 
	 * @author Sebastian
	 * @param o
	 */
	public boolean equals(Order o) 
	{
		if(o instanceof Order) 
		{
			if(o.getClass() == this.getClass() && o.getProducts().size() == this.getProducts().size()) 
			{
				for(Product p : o.getProducts()) 
				{
					if(!products.contains(o))
					{
						return false;
					}
				}
				return true;
			}
		}
		return false;
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
		String output = "id: " + orderID + System.getProperty("line.separator");
		for (Product p: products) {
			output = output
			.concat(p.toString())
			.concat(System.getProperty("line.separator"));
		}
		return output;
	}

	// added von Basti
	public Date getDate() 
	{
		return date;
	}
	
	/**
	 * @author Benedikt Zönnchen
	 * @return alle Produkte dieser Bestellung
	 */
	public Collection<Product> getProducts()
	{
		return products;
	}

	@Override
	public int compareTo(Order o)
	{
		if(o.getId() == this.getId())
		{
			return 0;
		}
		else
		{
			return this.getDate().compareTo(o.getDate());
		}
	}
	
}
