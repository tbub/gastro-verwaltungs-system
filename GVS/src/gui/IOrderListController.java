package gui;


import dto.TableDTO;

public interface IOrderListController
{
	public int getSelectedTable();
	public void setSelectedTable(int tableID);
	public boolean isTableSelected();
	public boolean isOrderClosed(int orderID);
	public TableDTO getTableDTO(int tableID);
	public void closeOrder(int orderID);
	public void logout();
	public void changeUser(String username, int tableID);
	public boolean isCurrentUserManager();
	public String[] getUserList();
	public String[] getTableList();
	
	public void openDialog();
	public void openEditOrder(int orderID);
	public void openAddOrder();
	public void openCalculate();
	
}
