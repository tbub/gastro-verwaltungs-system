package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.IOrderUC;
import model.IProductUC;
import model.ITableUC;
import model.IUserUC;
import model.gvsBase.Order;
import model.gvsBase.Product;
import model.gvsBase.User;

public class OrderListController implements IOrderListController, ActionListener
{
	private ITableUC tableUC;			// single
	private IOrderUC orderUC;		
	private IProductUC productUC;
	private IUserUC userUC;
	private int selectedTable = 0;
	private boolean isTableselected = false;
	
	public OrderListController(ITableUC tableUC, IOrderUC orderUC, IProductUC productUC)
	{
		this.tableUC = tableUC;
		this.orderUC = orderUC;
		this.productUC = productUC;
	}
	
	public void openDialog()
	{
		new OrderListDialog(this);
	}
	
	public Set<String> getUsers()
	{
		Set<String> userSet = new HashSet<String>();
		for(User user : userUC.getUsers())
		{
			userSet.add(user.getName());
		}
		return userSet;
	}

	public String[][] getOrders(int tableID)
	{
		List<Order> orders = tableUC.getOrders(tableID);
		String[][] orderList = new String[orders.size()][3];
		for(int row = 0; row < orderList.length; row++)
		{
			String productString = "";
			orderList[row][0] = orders.get(row).getId()+"";
			float price = 0.0f;
			for(Product p : orders.get(row).getProducts())
			{
				productString += p.getName() + ", ";
				price += p.getPrice();		//ToDo: sollte nicht hier berechnet werden!
			}
			productString = productString.substring(0, productString.length()-2);
			orderList[row][1] = productString;
			orderList[row][2] = price+" €";
		}
		return orderList;
	}
	
	public boolean isCurrentUser(String username)
	{
		return userUC.isCurrentUser(username);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
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
}
