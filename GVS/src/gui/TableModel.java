package gui;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel
{
	private final String[] COLUMN_NAMES;
	private final Class<?>[] COLUMN_TYPES;
	private Object[][] data;
	
	public TableModel(String[] columnNames, Object[][] data)
	{
		this.data = data;
		COLUMN_NAMES = columnNames;
		COLUMN_TYPES = new Class<?>[getColumnCount()];
		
		for(int row = 0; row < getRowCount(); row++)
		{
			for(int col = 0; col < getColumnCount(); col++)
			{
				if(data[row][col] == null)
				{
					data[row][col] = "";
				}
				
				if(row == 0)
				{
					COLUMN_TYPES[row] = data[row][col].getClass();
				}
			}
		}
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return COLUMN_TYPES[columnIndex];
	}
	
	@Override
	public int getColumnCount()
	{
		return data.length==0?0:data[0].length;
	}

	@Override
	public int getRowCount()
	{
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}
}
