package gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class GraphicFactory implements IGraphicFactory, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8480667810422448995L;
	private static final String PATH = "images/";
	private static final String STANDARD_IMAGE_DATA_TYPE = "png";
	private Properties prop;
	private static IGraphicFactory instance = new GraphicFactory();;

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
	 /**
	  *TODO
	  * @param x
	  */
	public final void writeObject(OutputStream x)
	{
		throw new IllegalArgumentException("schnaps");
	}
	
	public static IGraphicFactory getInstance()
	{
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
	
	public JLabel createLabel(String key)
	{
		return new JLabel(getProperty(key));
	}
	
	public int showErrorDialog(String key)
	{
		ImageIcon icon = new ImageIcon("icons/error_icon.png");
		return JOptionPane.showConfirmDialog(null, getProperty(key), "Fehler", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon);
	}
	
	public int showQuestionDialog(String key, String title)
	{
		ImageIcon icon = new ImageIcon("icons/question_icon.png");
		return JOptionPane.showConfirmDialog(null, getProperty(key), title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
	}
	
	public int showWarningDialog(String key)
	{
		ImageIcon icon = new ImageIcon("icons/warning_icon.png");
		return JOptionPane.showConfirmDialog(null, getProperty(key), "Warnung",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, icon);
	}
	
	public String getProperty(String key)
	{
		return prop.getProperty(key);
	} 
	public static void main(String[] args) throws ClassNotFoundException
	{
		
		try
		{
			FileOutputStream pos = new FileOutputStream("xy.dat");
			ObjectOutputStream oos = new ObjectOutputStream(pos);
			IGraphicFactory fac = GraphicFactory.getInstance();
			oos.writeObject(fac);
			oos.flush();
			oos.close();
			
			FileInputStream pis = new FileInputStream("xy.dat");
			ObjectInputStream ois = new ObjectInputStream(pis);
			
			GraphicFactory gf = (GraphicFactory)ois.readObject();
			oos.close();
			System.out.println(GraphicFactory.getInstance());
			System.out.println(gf);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
