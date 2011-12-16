package model;

import java.util.Collection;

import dto.UserDTO;

public interface IUserUC
{
	public void login(String username);
	public void logout();
	public boolean checkUser(String name, String password);
	public boolean isManager(String username);
	public Collection<UserDTO> getUsers();
	public UserDTO getCurrentUser();
	public boolean isCurrentUser(String username);
}
