package model;

/**
 * 
 * @author Benedikt Zönnchen
 * Interface welches von der View angesprochen wird
 * 
 * Anwendungsfälle: 
 * - Bestellung anlegen
 * - Bestellung schließen
 * - Bestellung erweitern 
 * - Bestellung umziehen
 *
 */
public interface IOrderUC
{
	/**
	 * Bestellung erweitern/anlegen
	 * @param orderID
	 * @param productID
	 */
	public void addProduct(int orderID, int productID);
	
	/**
	 * Bestellung schließen
	 * @param orderID
	 */
	public void closeOrder(int orderID);
	
	/**
	 * Bestellung umziehen
	 * @param orderID
	 * @param tableID
	 */
	public void changeOrder(int orderID, int tableID);
}
