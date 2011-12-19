package database;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import model.gvsBase.Order;

/**
 * Schnittstelle zum Speichern und laden von Bestellungen.
 * @author Benedikt Zönnchen
 *
 */
public interface IOrderPersistenz
{
	/**
	 * Speichert eine bereits existierende oder eine neue Bestellung persistent ab.
	 * @param order Bestellung
	 * @throws IOException
	 */
	public void saveOrder(Order order) throws IOException;
	
	/**
	 * Gibt eine Liste von Bestellungen in einem bestimmten Zeitraum zurück
	 * @param start Startdatum
	 * @param end Enddatum
	 * @return Liste von Bestellungen
	 * @throws IOException
	 */
	public Set<Order> getOrders(Date start, Date end) throws IOException;
	
	/**
	 * Diese Methode muss aufgerufen werden nachdem ein Datentransfer stattgefunden hat.
	 * Sie speichert alle Änderungen ab.
	 * @throws IOException
	 */
	public void endSession() throws IOException;
	
	/**
	 * Diese Methode muss aufgerufen werden bevor ein Datentransfer stattfinden kann
	 * Sie lädt die Datei in den Speicher
	 * @throws IOException
	 */
	public void startSession() throws IOException;
	
	/**
	 * Speichert eine Liste von Bestellungen in der aktuell angewählten XML-Datei ab. Ist die
	 * Datei nicht leer so werden die Einträge aktuallisiert oder gegebenfalls neue Einträge hinzu-
	 * gefügt.
	 * Achtung: Es muss entweder eine leere XML-Datei oder eine valide XML-Datei angegeben werden!
	 * @param orderList Bestellliste
	 * @throws IOException 
	 */
	public void saveOrders(Collection<Order> orderList) throws IOException;
}
