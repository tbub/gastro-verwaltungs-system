package model;
/**
 * 
 * @author Benedikt Zönnchen
 * Interface welches von der View angesprochen wird
 * 
 * Anwendungsfälle: 
 * - Benutzer anmelden
 * - Benutzer abmelden
 *
 */
public interface IUserUC
{
	public boolean login(String username, String password);
	public void logout();
}
