package gui.login;

import gui.orderList.IOrderListController;
import gui.orderList.OrderListController;
import gui.orderList.OrderListDialog;

import java.io.IOException;

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
		try
		{
			return userUC.login(username, password);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void openDialog()
	{
		this.dialog = new LoginDialog(this);
	}

	@Override
	public void openOrderListDialog()
	{
		IOrderListController controller = new OrderListController(dialog);
		OrderListDialog dialog = new OrderListDialog(controller);
		controller.setDialog(dialog);
		dialog.updateModel();
	}

	
}
