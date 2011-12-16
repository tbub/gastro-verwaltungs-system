package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import dto.TableDTO;
import dto.UserDTO;
import model.IOrderUC;
import model.ITableUC;
import model.IUserUC;

public class OrderListController implements IOrderListController, ActionListener
{
	private ITableUC tableUC;			// single
	private IOrderUC orderUC;		
	private IUserUC userUC;
	private int selectedTable = 0;
	private boolean isTableselected = false;
	
	public OrderListController(ITableUC tableUC, IOrderUC orderUC)
	{
		this.tableUC = tableUC;
		this.orderUC = orderUC;
	}
	
	public void openDialog()
	{
		new OrderListDialog(this);
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
		return tableUC.getTableDTO(tableID);
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
	public boolean isOrderClosed(int orderID)
	{
		return orderUC.isOrderClosed(orderID);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
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
}
