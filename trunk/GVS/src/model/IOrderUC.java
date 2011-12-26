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
	//public void addProduct(long orderID, String productname);
	
	public void addProduct(int tableId, long orderId, String productname);
	
	/**
	 * Bestellung schließen
	 * @param orderID
	 */
	public void closeOrder(long orderID);
	
	/**
	 * Bestellung umziehen
	 * @param orderID
	 * @param tableID
	 */
	public void changeOrder(long orderID, int tableID);
	
	/**
	 * gibt die Tischnummer der angegebenen Bestellung zurück
	 * @param orderID
	 * @return tableId oder -1 falls die Bestellung in keinem Tisch enthalten ist
	 */
	public int getTableId(long orderID);
}
