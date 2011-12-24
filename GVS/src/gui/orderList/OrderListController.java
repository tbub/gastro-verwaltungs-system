package gui.orderList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import dto.TableDTO;
import dto.UserDTO;
import model.ICalculationUC;
import model.IDataManagement;
import model.IOrderUC;
import model.ITableUC;
import model.IUserUC;

public class OrderListController implements IOrderListController, ICalculationController
{
	private ITableUC tableUC;	
	private IUserUC userUC;
	private IDataManagement dataUC;
	private ICalculationUC calcUC;
	private int selectedTable = 0;
	private boolean isTableselected = false;
	private OrderListDialog dialog;
	
	public OrderListController(ITableUC tableUC, IDataManagement dataUC, IUserUC userUC, ICalculationUC calcUC)
	{
		this.tableUC = tableUC;
		this.dataUC = dataUC;
		this.userUC = userUC;
	}
	
	public void openDialog()
	{
		dialog = new OrderListDialog(this);
	}
	
	public Set<String> getUsers()
	{
		Set<String> userSet = new HashSet<String>();
		for(UserDTO user : dataUC.getUsers())
		{
			userSet.add(user.getName());
		}
		return userSet;
	}

	@Override
	public TableDTO getTableDTO(int tableID)
	{
		return dataUC.getTable(tableID);
	}
	
	@Override
	public boolean isTableSelected()
	{
		return isTableselected;
	}

	@Override
	public int getSelectedTable()
	{
		return selectedTable;
	}

	@Override
	public String[] getUserList()
	{
		Collection<UserDTO> userCollection = dataUC.getUsers();
		String[] result = new String[userCollection.size()];
		
		int index = 0;
		for(UserDTO user : userCollection)
		{
			result[index++] = user.getName();
		}
		return result;
	}
	

	@Override
	public String[] getTableList()
	{
		Collection<TableDTO> tableCollection = dataUC.getTables();
		
		String[] result = new String[tableCollection.size()];
		int index = 0;
		for(TableDTO table : tableCollection)
		{
			result[index++] = table.getId()+"";
		}
		return result;
	}
	
	@Override
	public boolean isOrderClosed(int orderID)
	{
		return dataUC.getOrder(orderID).isCloesed();
	}


	@Override
	public void setSelectedTable(int tableID)
	{
		this.selectedTable = tableID;	
	}
	
	@Override
	public void closeOrder(int orderID)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openEditOrder(int orderID)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openAddOrder()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout()
	{
		userUC.logout();
		// login Dialog öffnen
	}

	@Override
	public void changeUser(String username, int tableID)
	{
		tableUC.changeUser(tableID, username);
	}
	
	public void openCalculate()
	{
		
	}

	@Override
	public void calculate(String startDate, String endDate)
	{
		calcUC.calculate(startDate, endDate);
	}

	@Override
	public boolean isCurrentUserManager()
	{
		return dataUC.getCurrentUser().getType().getName().equals("manager");
	}
}
