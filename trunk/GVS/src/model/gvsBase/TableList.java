package model.gvsBase;
import java.util.Collection;
import java.util.HashSet;

public class TableList 
{

	private Collection<Table> tables = new HashSet<Table>();
	
	public TableList(){}
	
	/**
	 * addTable
	 * 
	 * fügt einen Tisch zur Liste hinzu
	 * 
	 * @author Sebastian
	 * @param Table
	 */
	public void addTable(Table table) 
	{
		tables.add(table);
	}
	
	public void addTables(Collection<Table> tabs)
	{
		tables.addAll(tabs);
		System.out.println(tables.size());
	}
	
	public boolean containsTable(int tableId)
	{
		return getTable(tableId) != null;
	}
	
	public Table getTableByOrderId(long orderId)
	{
		for(Table table : tables)
		{
			if(table.containsOrder(orderId))
			{
				return table;
			}
		}
		return null;
	}
	
	/**
	 * getTable
	 * 
	 * returned einen Tisch aus der Tischliste
	 * 
	 * @author Sebastian
	 * @param id
	 * @return Table
	 */
	public Table getTable(int id) 
	{
		for(Table table : tables)
		{
			if(table.getId() == id)
			{
				return table;
			}
		}
		return null;
	}
}
