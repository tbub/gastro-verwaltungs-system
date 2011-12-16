package gui;


import dto.TableDTO;

public interface IOrderListController
{
	public void openDialog();
	public int getSelectedTable();
	public void setSelectedTable(int tableID);
	public boolean isTableSelected();
	public boolean isOrderClosed(int orderID);
	public TableDTO getTableDTO(int tableID);
	public void closeOrder(int orderID);
	public void editOrder(int orderID);
	public void addOrder();
	public void logout();
	public void changeUser(String username, int tableID);
	public void calculate();
	public String[] getUserList();
	public String[] getTableList();
}
