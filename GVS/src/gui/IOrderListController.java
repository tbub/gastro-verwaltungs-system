package gui;

import dto.TableDTO;

public interface IOrderListController
{
	public int getSelectedTable();
	public boolean isTableSelected();
	public boolean isOrderClosed(int OrderID);
	public TableDTO getTableDTO(int tableID);
	public void closeOrder(int orderID);
	public void editOrder(int orderID);
	public void addOrder();
}
