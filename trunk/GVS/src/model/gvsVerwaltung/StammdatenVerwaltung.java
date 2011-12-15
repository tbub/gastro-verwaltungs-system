package model.gvsVerwaltung;
import java.util.ArrayList;
import model.gvsBase.*;

/**
 * Verantwortlich f�r das Laden von
 * - Liste an Tische (enthalten ihre Bestellungen selbst als Liste
 * - Liste an Kellnern
 * - Liste an geschlossenen Bestellungen
 * - Liste an offenen Bestellungen
 * 
 * 
 * fragt alle Datenelemente aus der Datenschnittstelle an und speichert sie tempor�r im System
 * 
 * @author Sebastian
 * 
 * 
 */

public class StammdatenVerwaltung {

	ArrayList<Produkt> ProdukteListe;
	ArrayList<User> KellnerListe;
	ArrayList<Tisch> TischeListe;
	ArrayList<Bestellung> offeneBestellungen;
	
	public StammdatenVerwaltung() {
		ProdukteListe = new ArrayList<Produkt>();
		TischeListe = new ArrayList<Tisch>();
		KellnerListe = new ArrayList<User>();
		LoadKellner();
		LoadProdukte();
		LoadTische();
	}
	
	public void LoadProdukte() {
		ProdukteListe.add(new Produkt("Schnitzel", true));
		ProdukteListe.add(new Produkt("Hamburger", true));
		ProdukteListe.add(new Produkt("Steak", true));
		ProdukteListe.add(new Produkt("Cola", false));
		ProdukteListe.add(new Produkt("Wasser", false));
		ProdukteListe.add(new Produkt("Kaffee", false));
	}
	
	public void LoadKellner() {
		KellnerListe.add(new User("Basti"));
		KellnerListe.add(new User("Bene"));
		KellnerListe.add(new User("Benni"));
		KellnerListe.add(new User("Sebastian"));
	}
	
	public void LoadTische() {
		TischeListe.add(new Tisch(1,KellnerListe.get(0)));
		TischeListe.add(new Tisch(2,KellnerListe.get(1)));
		TischeListe.add(new Tisch(3,KellnerListe.get(2)));
		TischeListe.add(new Tisch(4,KellnerListe.get(3)));
	}
	
	public ArrayList<Produkt> getProdukte() {
		return ProdukteListe;
	}
	
	public ArrayList<Tisch> getTische() {
		return TischeListe;
	}
	
	public ArrayList<User> getKellnerListe() {
		return KellnerListe;
	}
	
}
