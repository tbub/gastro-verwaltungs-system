package model.gvsBase;
import java.util.ArrayList;

/**
 * Basiselement einer Bestellung
 * 
 * @author ifw10142
 *
 */

public class Order {

	ArrayList<Product> Products;
	
	/**
	 * Konstruktor
	 */
	public Order() {
		 Products =  new ArrayList<Product>();
	}
	
	public ArrayList<Product> getProducts() {
		return Products;
	}
	
	public void setProducts(ArrayList<Product> p) {
		Products = p;
	}
		
	/**
	 * addProduct()
	 * 
	 * f�gt ein Produkt der Liste an Productsn, die zu einer Bestellung geh�ren, hinzu
	 * 
	 * @author Sebastian
	 * @param p	Produktobjekt das hinzugef�gt wird
	 */
	public void addProdukt(Product p) {
		Products.add(p);
	}
	
	
	/**
	 * removeProduct()
	 * 
	 * l�scht ein Produkt aus der Liste der zugeh�rigen Products.
	 * Daf�r wird die Liste von hinten durchsucht und das erste Produkt
	 * das dem selben entspricht, wird gel�scht, da es wahrscheinlich
	 * das aktuellste ist
	 * 
	 * @author Sebastian
	 * @param p	Produktobjekt das gel�scht wird wird
	 */
	public void deleteProdukt(Product p) {
		for ( int i = Products.size()-1; i >= 0 ; i--) {
			if(Products.get(i).equals(p)) {
				Products.remove(i);
				Products.trimToSize();
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
	private boolean equals(Order o) {
		int EqualCounter = 0;
		if(o instanceof Order) {
			if(o.getClass() == this.getClass()) {
				for(Product p : o.getProducts()) {
					for(Product p1 : this.getProducts()) {
						if(p1 == p) {
							EqualCounter++;
						}
					}
				}
			}
		}
		if(EqualCounter == this.getProducts().size()) { return true; }
		else { return false; }
	}
	
	/**
	 * toString
	 */
	public String toString() {
		String output = "";
		for (Product p: Products) {
			output = output
			.concat(p.toString())
			.concat(System.getProperty("line.separator"));
		}
		return output;
	}
	
}
