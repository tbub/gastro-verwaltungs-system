package model;

import java.util.List;

import dto.UserDTO;

public interface IUserUC
{
	public void login(String username);
	public void logout();
	public boolean checkUser(String name, String password);
	public boolean isManager(String username);
	public List<UserDTO> getUsers();
	public UserDTO getCurrentUser();
	public boolean isCurrentUser(String username);
}
