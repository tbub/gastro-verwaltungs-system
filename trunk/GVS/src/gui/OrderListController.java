package gui;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import dto.TableDTO;
import dto.UserDTO;
import model.IOrderUC;
import model.ITableUC;
import model.IUserUC;

public class OrderListController implements IOrderListController
{
	private ITableUC tableUC;
	private IOrderUC orderUC;		
	private IUserUC userUC;
	private int selectedTable = 0;
	private boolean isTableselected = false;
	private OrderListDialog dialog;
	
	public OrderListController(ITableUC tableUC, IOrderUC orderUC, IUserUC userU)
	{
		this.tableUC = tableUC;
		this.orderUC = orderUC;
		this.userUC = userU;
	}
	
	public void openDialog()
	{
		dialog = new OrderListDialog(this);
	}
	
	public Set<String> getUsers()
	{
		Set<String> userSet = new HashSet<String>();
		for(UserDTO user : userUC.getUsers())
		{
			userSet.add(user.getName());
		}
		return userSet;
	}

	@Override
	public TableDTO getTableDTO(int tableID)
	{
		return tableUC.getTable(tableID);
	}
	
	public boolean isCurrentUser(String username)
	{
		return userUC.isCurrentUser(username);
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
		Collection<UserDTO> userCollection = userUC.getUsers();
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
		Collection<TableDTO> tableCollection = tableUC.getTables();
		
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
		return orderUC.isOrderClosed(orderID);
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
	public void editOrder(int orderID)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrder()
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
	
	public void calculate()
	{
		
	}
}
