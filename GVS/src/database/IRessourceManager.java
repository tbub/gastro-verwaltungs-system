package database;

import java.io.IOException;
import java.util.Collection;
import model.gvsBase.Product;
import model.gvsBase.User;
import model.gvsBase.Table;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public interface IRessourceManager
{
	/**
	 * Gibt eine Collection von allen Benutzern zurück.
	 *
	 * @return liste aller benutzer
	 */
	public Collection<User> getUsers() throws IOException;
	
	/**
	 * Gibt eine Collection von allen Produkten zurück.
	 *
	 * @return liste aller produkte
	 */
	public Collection<Product> getProducts() throws IOException;
	
	/**
	 * Gibt den zuletzt benutzte id der zuletzt gespeicherten Bestellung zurück
	 * 
	 * @return id der zuletzt gespeicherten Bestellung 
	 */
	public long getLastOrderId() throws IOException;
	
	/**
	 * Gibt eine Collection voØn allen Tische, mit den jeweiligem Benutzer zurück.
	 * 
	 * @return id der zuletzt gespeicherten Bestellung 
	 */
	public Collection<Table> getTables() throws IOException;

}
