package model.gvsUC;

/**
 * 
 * @author Benedikt Zönnchen
 * Interface welches von der View angesprochen wird
 * 
 * Anwendungsfälle: 
 * - Kellner/User ändern
 *
 */
public interface ITableUC
{
	public void changeUser(int tableID, String username);
}
