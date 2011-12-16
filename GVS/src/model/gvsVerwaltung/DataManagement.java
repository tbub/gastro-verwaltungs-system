package model.gvsVerwaltung;
import java.util.ArrayList;
import model.gvsBase.*;

/**
 * Verantwortlich für das Laden von
 * - Liste an Tische (enthalten ihre Bestellungen selbst als Liste
 * - Liste an Kellnern
 * - Liste an geschlossenen Bestellungen
 * - Liste an offenen Bestellungen
 * 
 * 
 * fragt alle Datenelemente aus der Datenschnittstelle an und speichert sie temporär im System
 * 
 * @author Sebastian
 * 
 * 
 */

public class DataManagement {

	ArrayList<Product> ProdukteListe;
	ArrayList<User> KellnerListe;
	ArrayList<Table> TischeListe;
	ArrayList<Order> offeneBestellungen;
	
	public DataManagement() {
		ProdukteListe = new ArrayList<Product>();
		TischeListe = new ArrayList<Table>();
		KellnerListe = new ArrayList<User>();
		LoadKellner();
		LoadProdukte();
		LoadTische();
	}
	
	public void LoadProdukte() {
		ProdukteListe.add(new Product("Schnitzel", ProductType.food));
		ProdukteListe.add(new Product("Hamburger", ProductType.food));
		ProdukteListe.add(new Product("Steak", ProductType.food));
		ProdukteListe.add(new Product("Cola", ProductType.drink));
		ProdukteListe.add(new Product("Wasser", ProductType.drink));
		ProdukteListe.add(new Product("Kaffee", ProductType.drink));
	}
	
	public void LoadKellner() {
		KellnerListe.add(new User("Basti","test", UserType.bartender));
		KellnerListe.add(new User("Bene","test", UserType.bartender));
		KellnerListe.add(new User("Benni","test", UserType.manger));
		KellnerListe.add(new User("Sebastian","test", UserType.bartender));
	}
	
	public void LoadTische() {
		TischeListe.add(new Table(1,KellnerListe.get(0)));
		TischeListe.add(new Table(2,KellnerListe.get(1)));
		TischeListe.add(new Table(3,KellnerListe.get(2)));
		TischeListe.add(new Table(4,KellnerListe.get(3)));
	}
	
	public ArrayList<Product> getProdukte() {
		return ProdukteListe;
	}
	
	public ArrayList<Table> getTische() {
		return TischeListe;
	}
	
	public ArrayList<User> getKellnerListe() {
		return KellnerListe;
	}
	
}
