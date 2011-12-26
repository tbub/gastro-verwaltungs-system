package gui.orderList;


import gui.IDialog;
import dto.TableDTO;

public interface IOrderListController
{
	public int getSelectedTable();
	public void setSelectedTable(int tableID);
	public boolean isOrderClosed(int orderID);
	public TableDTO getTableDTO(int tableID);
	public void closeOrder(long orderID);
	public boolean logout();
	public void changeUser(String username, int tableID);
	public boolean isCurrentUserManager();
	public String[] getUserList();
	public String[] getTableList();
	public void enableOrderList();
	
	//public void openDialog();
	public void openEditOrder(long orderID);
	public void openAddOrder();
	public void openCalculate();
	public void setDialog(IDialog dialog);
	public void closeDialog();
	
}
