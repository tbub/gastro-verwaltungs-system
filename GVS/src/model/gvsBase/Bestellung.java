package model.gvsBase;
import java.util.ArrayList;

/**
 * Basiselement einer Bestellung
 * 
 * @author ifw10142
 *
 */

public class Bestellung {

	ArrayList<Produkt> Produkte;
	
	/**
	 * Konstruktor
	 */
	public Bestellung() {
		 Produkte =  new ArrayList<Produkt>();
	}
	
	public ArrayList<Produkt> getProdukte() {
		return Produkte;
	}
	
	public void setProdukte(ArrayList<Produkt> p) {
		Produkte = p;
	}
		
	/**
	 * addProduct()
	 * 
	 * f�gt ein Produkt der Liste an Produkten, die zu einer Bestellung geh�ren, hinzu
	 * 
	 * @author Sebbo
	 * @param p	Produktobjekt das hinzugef�gt wird
	 */
	public void addProdukt(Produkt p) {
		Produkte.add(p);
	}
	
	
	/**
	 * removeProduct()
	 * 
	 * l�scht ein Produkt aus der Liste der zugeh�rigen Produkte.
	 * Daf�r wird die Liste von hinten durchsucht und das erste Produkt
	 * das dem selben entspricht, wird gel�scht, da es wahrscheinlich
	 * das aktuellste ist
	 * 
	 * @author Sebbo
	 * @param p	Produktobjekt das gel�scht wird wird
	 */
	public void deleteProdukt(Produkt p) {
		for ( int i = Produkte.size()-1; i >= 0 ; i--) {
			if(Produkte.get(i).equals(p)) {
				Produkte.remove(i);
				Produkte.trimToSize();
				break;
			}
		}
	}
	
	/**
	 * toString
	 */
	public String toString() {
		String output = "";
		for (Produkt p: Produkte) {
			output = output
			.concat(p.toString())
			.concat(System.getProperty("line.separator"));
		}
		return output;
	}
	
}
