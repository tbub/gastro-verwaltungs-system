package protptype;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class BillCalculationError
{
	public static void main(String[] args)
	{
		ImageIcon icon = new ImageIcon("icons/error_icon.png");
		JOptionPane.showConfirmDialog(null,
                "Sie haben einen ungültigen Zeitraum eingegeben!",
                "Fehler",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon);
	}
}
