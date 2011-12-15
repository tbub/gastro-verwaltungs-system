package model;

import model.gvsBase.Tisch;

public interface ITableUC
{
	public void editTable(int orderID, int newTableID);
	public Tisch getTable(int tableID);
	public void changeUser(int tableID, String username);
}
