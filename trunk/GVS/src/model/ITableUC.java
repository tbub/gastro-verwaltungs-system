package model;


import java.util.Collection;

import dto.TableDTO;

public interface ITableUC
{
	public void editTable(int orderID, int newTableID);
	public TableDTO getTable(int tableID);
	public void changeUser(int tableID, String username);
	public Collection<TableDTO> getTables();
}
