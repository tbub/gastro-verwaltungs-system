package protptype;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class LoginError
{
	public static void main(String[] args)
	{
		ImageIcon icon = new ImageIcon("icons/error_icon.png");
		JOptionPane.showConfirmDialog(null,
                "Entweder existiert dieser Benutzer nicht\noder Sie haben Ihr Passwort falsch eingegeben",
                "Fehler",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon);
	}
}
