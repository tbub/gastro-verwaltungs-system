package model.gvsUC;

import java.io.IOException;

import dto.UserDTO;

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
	public boolean login(String username, String password) throws IOException;
	public void logout();
	public UserDTO getCurrentUser() throws IOException;
}
