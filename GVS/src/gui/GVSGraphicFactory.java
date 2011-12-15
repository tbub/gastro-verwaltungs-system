package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GVSGraphicFactory
{
	public static final String PATH = "images/";
	public static final String STANDARD_IMAGE_DATA_TYPE = "png";
	public static Properties prop;
	
	static
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
	
	public static JButton createImageButton(String name, String fileType, boolean rollover)
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
	
	public static GVSTableButton createTableButton(String name, String fileType)
	{
		Icon icon = new ImageIcon(PATH+name+"."+fileType);
		Icon lightIcon = new ImageIcon(PATH + name + "_light."+fileType);
		GVSTableButton button = new GVSTableButton(icon, lightIcon);
		button.setToolTipText(prop.getProperty("tooltip."+name));
		button.setOpaque(true);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		return button;
	}
	
	public static JButton createTableButton(String name)
	{
		return createTableButton(name, STANDARD_IMAGE_DATA_TYPE);
	}
	
	public static JButton createImageButton(String name, boolean rollover)
	{
		return createImageButton(name, STANDARD_IMAGE_DATA_TYPE, rollover);
	}
	public static void meep() {}
	
	public void test(){};
}
