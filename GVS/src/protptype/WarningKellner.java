package protptype;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class WarningKellner
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ImageIcon icon = new ImageIcon("icons/warning_icon.png");
		JOptionPane.showConfirmDialog(null,
                "Der neue Kellner der für diesen Tisch nun eingetragen ist,\nist genau der gleiche wie der, der zuvor eingetragen war.",
                "Warnung",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, icon);
	}

}
