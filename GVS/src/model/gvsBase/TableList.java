package model.gvsBase;
import java.util.List;


public class TableList {

	List<Table> Tables;
	
	public TableList() {
		
	}
	
	/**
	 * addTable
	 * 
	 * fügt einen Tisch zur Liste hinzu
	 * 
	 * @author Sebastian
	 * @param Table
	 */
	public void addTable(Table Table) {
		Tables.add(Table);
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
	public Table getTable(int id) {
		return Tables.get(id);
	}
}
