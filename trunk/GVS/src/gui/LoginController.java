package gui;

import model.IUserUC;

public class LoginController implements ILoginController
{
	private IUserUC userUC;
	private LoginDialog dialog;
	
	public LoginController(IUserUC userUC)
	{
		this.userUC = userUC;
	}
	
	@Override
	public void login(String username)
	{
		userUC.login(username);
	}

	@Override
	public boolean checkUser(String username, String password)
	{
		return userUC.checkUser(username, password);
	}

	@Override
	public void openDialog()
	{
		this.dialog = new LoginDialog(this);
	}

	
}
