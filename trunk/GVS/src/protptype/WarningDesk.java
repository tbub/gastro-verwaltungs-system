package protptype;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class WarningDesk
{
	public static void main(String[] args)
	{
		ImageIcon icon = new ImageIcon("icons/warning_icon.png");
		JOptionPane.showConfirmDialog(null,
                "Der neue Tisch dieser Bestellung enspricht dem alten Tisch!\nSie haben somit nichts geändert.",
                "Warnung",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, icon);
	}
}
