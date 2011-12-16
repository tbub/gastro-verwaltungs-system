package gui;

public interface ILoginController 
{
	public void openDialog();
	public void login(String username);
	public boolean checkUser(String username, String password);
}
