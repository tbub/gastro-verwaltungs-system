package model;

import java.util.List;

import model.gvsBase.User;

public interface IUserUC
{
	public void login(String username);
	public void logout();
	public boolean checkUser(String name, String password);
	public boolean isManager(String username);
	public List<User> getUsers();
	public User getCurrentUser();
	public boolean isCurrentUser(String username);
}
