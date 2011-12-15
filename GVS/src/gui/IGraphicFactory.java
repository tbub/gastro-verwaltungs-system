package gui;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface IGraphicFactory
{
	public JButton createImageButton(String name, String fileType, boolean rollover);
	public TableButton createTableButton(String name, String fileType);
	public JButton createTableButton(String name);
	public JButton createImageButton(String name, boolean rollover);
	public JLabel createLabel(String name);
	public String getProperty(String key);
	public int showWarningDialog(String message);
	public int showQuestionDialog(String message, String title);
	public int showErrorDialog(String message);
}
