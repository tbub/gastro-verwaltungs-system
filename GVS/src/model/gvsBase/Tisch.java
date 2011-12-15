package model.gvsBase;

import java.util.ArrayList;

/**
 * Basiselement eines Tisches
 * 
 * @author ifw10142
 *
 */

public class Tisch {
	
	int Tischnummer;
	Kellner Kellner;
	ArrayList<Bestellung> Bestellungen =  new ArrayList<Bestellung>();
	
	/**
	 * Konstruktor
	 * 
	 * @param Tischnummer
	 * @param Kellner
	 */
	public Tisch(int Tischnummer, Kellner Kellner) {
		this.Tischnummer = Tischnummer;
		this.Kellner = Kellner;
	}
	
	
	/**
	 * Wenn Bene die Kommunikation startet dann kann er einen Tisch anfragen ohne damit die Kellner zu kennen.
	 * Damit weiß ich welche Daten ich bekomme
	 * 
	 * @param Tischnummer
	 */
	public Tisch(int Tischnummer) {
		this.Tischnummer = Tischnummer;
		this.Kellner = null;
	}
	
	public void setBestellungen(ArrayList<Bestellung> bestellungen) {
		Bestellungen = bestellungen;
	}
	
	public void setKellner(Kellner kellner) {
		Kellner = kellner;
	}
	
	public void setTischnummer(int tischnummer) {
		Tischnummer = tischnummer;
	}
	
	/**
	 * addBestellung
	 * 
	 * Fügt eine Bestellung einem Tisch hinzu
	 * 
	 * @param b
	 */
	public void addBestellung(Bestellung b) {
		if(b != null){
			if(!b.getProdukte().isEmpty()) {
				Bestellungen.add(b);
			}
		}
	}
	
	/**
	 * toString
	 */
	public String toString() {
		String output = "Tisch: " + Tischnummer + "\n" + Kellner.toString()+"\n";
		for(Bestellung b: Bestellungen) {
			output = output + "\n- - -\n";
			for(Produkt p: b.getProdukte()) {
				output = output + p.toString() + "\n";
			}
		}
		return output;
	}
	
}
