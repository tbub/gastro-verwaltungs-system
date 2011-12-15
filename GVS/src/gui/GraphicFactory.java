package gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GraphicFactory implements IGraphicFactory
{
	private static final String PATH = "images/";
	private static final String STANDARD_IMAGE_DATA_TYPE = "png";
	private Properties prop;
	private static IGraphicFactory instance;

	private GraphicFactory()
	{
		prop = new Properties();
		try
		{
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("properties/gui.properties"));
			prop.load(stream);
			stream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static IGraphicFactory getInstance()
	{
		if(instance == null)
		{
			instance = new GraphicFactory();
		}
		return instance;
		
	}
	
	public JButton createImageButton(String name, String fileType, boolean rollover)
	{
		Icon icon = new ImageIcon(PATH+name+"."+fileType);
		JButton button = new JButton();
		button.setToolTipText(prop.getProperty("tooltip."+name));
		button.setIcon(icon);
		if(rollover)
		{
			Icon lightIcon = new ImageIcon(PATH + name + "_light."+fileType);
			button.setRolloverIcon(lightIcon);
		}
		button.setOpaque(true);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		return button;
	}
	
	public TableButton createTableButton(String name, String fileType)
	{
		Icon icon = new ImageIcon(PATH+name+"."+fileType);
		Icon lightIcon = new ImageIcon(PATH + name + "_light."+fileType);
		TableButton button = new TableButton(icon, lightIcon);
		button.setToolTipText(prop.getProperty("tooltip."+name));
		button.setOpaque(true);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		return button;
	}
	
	public JButton createTableButton(String name)
	{
		return createTableButton(name, STANDARD_IMAGE_DATA_TYPE);
	}
	
	public JButton createImageButton(String name, boolean rollover)
	{
		return createImageButton(name, STANDARD_IMAGE_DATA_TYPE, rollover);
	}
	
	public JLabel createLabel(String name)
	{
		return new JLabel(name);
	}
	
	public int showErrorDialog(String message)
	{
		ImageIcon icon = new ImageIcon("icons/error_icon.png");
		return JOptionPane.showConfirmDialog(null, message, "Fehler", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon);
	}
	
	public int showQuestionDialog(String message, String title)
	{
		ImageIcon icon = new ImageIcon("icons/question_icon.png");
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
	}
	
	public int showWarningDialog(String message)
	{
		ImageIcon icon = new ImageIcon("icons/warning_icon.png");
		return JOptionPane.showConfirmDialog(null, message, "Warnung",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, icon);
	}
	
	public String getProperty(String key)
	{
		return prop.getProperty(key);
	}

}
