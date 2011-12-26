package gui.orderList;

import gui.GraphicFactory;
import gui.IDialog;
import gui.login.LoginController;
import gui.login.LoginDialog;
import gui.order.IOrderController;
import gui.order.OrderController;
import gui.order.OrderDialog;

import java.awt.Window;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import dto.TableDTO;
import dto.UserDTO;
import model.gvsDataManagement.IDataManagement;
import model.gvsUC.GvsUsecaseController;
import model.gvsUC.IGvsController;

public class OrderListController implements IOrderListController, ICalculationController
{
	private IGvsController useCaseController;	
	private IDataManagement dataUC;
	private int selectedTable = 1;
	private IDialog dialog;
	private IDialog loginDialog;
	
	public OrderListController(IDialog loginDialog)
	{
		this.useCaseController = GvsUsecaseController.getInstance();
		this.dataUC = useCaseController.getDataManagement();
		this.loginDialog = loginDialog;
	}
	
	public Set<String> getUsers()
	{
		Set<String> userSet = new HashSet<String>();
		try
		{
			for(UserDTO user : dataUC.getUsers())
			{
				userSet.add(user.getName());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return userSet;
	}

	@Override
	public TableDTO getTableDTO(int tableID)
	{
		try
		{
			return dataUC.getTable(tableID);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getSelectedTable()
	{
		return selectedTable;
	}

	@Override
	public String[] getUserList()
	{
		Collection<UserDTO> userCollection;
		try
		{
			userCollection = dataUC.getUsers();
			String[] result = new String[userCollection.size()];
			
			int index = 0;
			for(UserDTO user : userCollection)
			{
				result[index++] = user.getName();
			}
			return result;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	

	@Override
	public String[] getTableList()
	{
		Collection<TableDTO> tableCollection;
		try
		{
			tableCollection = dataUC.getTables();
			String[] result = new String[tableCollection.size()];
			int index = 0;
			for(TableDTO table : tableCollection)
			{
				result[index++] = table.getId()+"";
			}
			return result;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isOrderClosed(int orderID)
	{
		try
		{
			return dataUC.getOrder(orderID).isCloesed();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void setSelectedTable(int tableID)
	{
		this.selectedTable = tableID;	
		dialog.updateModel();
	}
	
	@Override
	public void closeOrder(long orderID)
	{
		useCaseController.closeOrder(orderID);
		this.dialog.updateModel();
	}

	@Override
	public void openEditOrder(long orderID)
	{
		IOrderController controller;
		controller = new OrderController(orderID, getSelectedTable(), this);
		OrderDialog dialog = new OrderDialog(controller, (Window)this.dialog);
		controller.setDialog(dialog);
		dialog.updateModel();
		this.dialog.enable(false);
		System.out.println("open productList id:"+orderID);
	}

	@Override
	public void openAddOrder()
	{
		try
		{
			openEditOrder(dataUC.getLastOrderId()+1);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean logout()
	{
		useCaseController.logout();
		new LoginDialog(new LoginController(this.useCaseController));
		return true;
	}

	@Override
	public void changeUser(String username, int tableID)
	{
		useCaseController.changeUser(tableID, username);
		dialog.updateModel();
	}
	
	public void openCalculate()
	{
		IDialog calcDialog = new CalculationDialog(this, (Window)dialog);
	}

	@Override
	public boolean calculate(String startDate, String endDate)
	{
		startDate = startDate + " 00:00";
		endDate = endDate + " 23:59";
		if(useCaseController.validateDate(startDate) && useCaseController.validateDate(endDate))
		{
			useCaseController.calculate(startDate, endDate);
			GraphicFactory.getInstance().showQuestionDialog("text.question.print", "Drucken");
			return true;
		}
		else
		{
			GraphicFactory.getInstance().showErrorDialog("text.error.date");
			return false;
		}
	}

	@Override
	public boolean isCurrentUserManager()
	{
		try
		{
			System.out.println(useCaseController.getCurrentUser());
			return useCaseController.getCurrentUser().getType().getName().equals("manager");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void setDialog(IDialog dialog)
	{
		this.dialog = dialog;
	}

	@Override
	public void enableOrderList()
	{
		dialog.updateModel();
		dialog.enable(true);
	}

	@Override
	public void closeDialog()
	{
		logout();
	}
}
