package gui.login;

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
	public boolean login(String username, String password)
	{
		return userUC.login(username, password);
	}

	@Override
	public void openDialog()
	{
		this.dialog = new LoginDialog(this);
	}

	@Override
	public void openOrderListDialog()
	{
		// TODO Auto-generated method stub
		
	}

	
}
