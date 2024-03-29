package gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Table extends JTable implements MouseListener, MouseMotionListener
{
	private TableButton rolloverButton = null;
	
	public Table()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public Table(TableModel model)
	{
		this();
		setModel(model);
	}
	
	@Override
	public void setModel(TableModel dataModel)
	{
		super.setModel(dataModel);
		TableCellRenderer renderer = new JTableButtonRenderer();
		for(int i = 0; i < dataModel.getColumnCount(); i++)
		{
			getColumn(dataModel.getColumnName(i)).setCellRenderer(renderer);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e){}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e)
	{
		//System.out.println(e);
		int column = getColumnModel().getColumnIndexAtX(e.getX());
		int row    = e.getY()/getRowHeight(); 

		if (row < getRowCount() && row >= 0 
				&& column < getColumnCount() && column >= 0)
		{
		    Object value = getValueAt(row, column);
		    if (value instanceof JButton)
		    {
		    	((JButton)value).doClick();
		    //	System.out.println("do Click" + value);
		    }
		}
	}

	@Override
	public void mouseReleased(MouseEvent e){}
	
	private class JTableButtonRenderer implements TableCellRenderer 
	{		
		@Override 
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if(value instanceof JButton)
			{
				
				JButton button = (JButton)value;
				return button;	
			}
			return new JLabel(value.toString());
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0){}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		int column = getColumnModel().getColumnIndexAtX(e.getX());
		int row    = e.getY()/getRowHeight(); 

		if (row < getRowCount() && row >= 0 && column < getColumnCount() && column >= 0)
		{			
		    Object value = getValueAt(row, column);
		    
		    if(rolloverButton != null && !value.equals(rolloverButton))
	    	{
	    		rolloverButton.setRollover(false);
	    	}
		    
		    if (value instanceof TableButton) {
		    	TableButton button = (TableButton)value;
		    	button.setRollover(true);
				rolloverButton = button;
		    }
		    this.repaint();
		}
	}
}
