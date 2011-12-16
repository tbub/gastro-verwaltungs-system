package model;

import dto.TableDTO;

public interface ITableUC
{
	public void editTable(int orderID, int newTableID);
	public TableDTO getTableDTO(int tableID);
	public void changeUser(int tableID, String username);
}
