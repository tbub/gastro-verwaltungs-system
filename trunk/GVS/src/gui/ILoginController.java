package gui;

public interface ILoginController 
{
	public void openDialog();
	public boolean login(String username, String password);
	public void openOrderListDialog();
}
