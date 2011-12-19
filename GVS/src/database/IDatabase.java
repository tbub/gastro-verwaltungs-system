package database;

import java.util.ArrayList;
import java.util.Date;

import model.gvsBase.Order;
import model.gvsBase.Product;
import model.gvsBase.Table;
import model.gvsBase.User;

/**
 * The Interface IDatabase.
 * 
 * Die Schnittstelle zur Datenhaltung bietet Methoden zum Speichern von Daten und Auslesen von Daten.
 * Die Daten werden in XML Files abgelegt.
 */
public interface IDatabase {

	/**
	 * Gibt eine Liste von allen Benutzern des GVS zur�ck. Informationen wie Passwort und Name
	 * sind im Userobject hinterlegt.
	 *
	 * @return the users of the gvs.
	 */
	public ArrayList<User> getUsers();
	
	/**
	 * Gibt eine Liste von allen Tischen des GVS zurück.
	 * Jeder Tisch enthält eine Liste der Bestellungen, welche am angegebenen 
	 * Datum getätigt wurden (offen + geschlossen). Jede diese Bestellungen enthält
	 * Liste von bestellten Produkten.
	 *
	 * @param date date of the orders
	 * @return the tables in our restaurant
	 */
	public ArrayList<Table> getTableList(Date date);
	
	/**
	 * Speichert eine neue Bestellung in der Datenbank.
	 *
	 * @param newOrder the new order
	 * @param tableID the id of the table the new order has to be saved
	 */
	public void saveNewOrder(Order newOrder, int tableID);
	
	/**
	 * �berschreibt eine bereits vorhandene Bestellung in der Datenbank.
	 *
	 * @param existingOrder the existing order
	 * @param tableID the id of the table the has to be overwritten
	 */
	public void updateOrder(Order existingOrder, int tableID);
	
	/**
	 * Gibt die Gesamtabrechnung zur�ck. 
	 * 
	 * Diese ist eine Liste von Bestellungen in einem gegebenen Zeitraum. Die Liste ist unsortiert.
	 *
	 * @param start the start date
	 * @param end the end date
	 * @return the total payroll
	 */
	public ArrayList<Order> getTotalPayroll(Date start, Date end);
	
	/**
	 * Speichert eine Gesamtabrechnung als XML File ab.
	 * 
	 * Diese kann dann von einem Drucker-Objekt (nicht implementiert) 
	 * formatiert und an den Drucker geschickt werden.
	 *
	 * @param orders the list of orders part of the total payroll
	 */
	void saveTotalPayroll(ArrayList<Order> orders,String pathOftotalPayroll_csv);
	
	public ArrayList<Product> getProducts();
	
	public long getLastOrderId();

}
