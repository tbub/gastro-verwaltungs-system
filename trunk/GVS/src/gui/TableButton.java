package gui;

import javax.swing.Icon;
import javax.swing.JButton;

public class TableButton extends JButton
{
	private boolean rollover = false;
	private Icon rolloverIcon;
	private Icon icon;

	public TableButton(Icon icon, Icon rolloverIcon)
	{
		this.rolloverIcon = rolloverIcon;
		this.icon = icon;
		setIcon(icon);
	}
	
	public void setRollover(boolean rollover)
	{
		this.rollover = rollover;
		setIcon((rollover?rolloverIcon:icon));
    	setBorder(null);
		setContentAreaFilled(false);
	}
	
	public boolean isRollover()
	{
		return rollover;
	}	
}
