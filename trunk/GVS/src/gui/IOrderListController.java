package gui;

public interface IOrderListController
{
	public String[][] getOrders(int tableID);
	public int getSelectedTable();
	public boolean isTableSelected();
	public boolean isOrderClosed(int OrderID);
}
