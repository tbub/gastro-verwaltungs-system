package model;

import java.util.List;

import model.gvsBase.Order;

public interface ITableUC
{
	public void editTable(int orderID, int newTableID);
	public List<Order> getOrders(int tableID);
	public void changeUser(int tableID, String username);
}
