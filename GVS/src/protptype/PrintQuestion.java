package protptype;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class PrintQuestion
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ImageIcon icon = new ImageIcon("icons/question_icon.png");
		JOptionPane.showConfirmDialog(null,
                "Mšchten Sie die Gesamtabrechnung drucken?",
                "Drucken",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

	}

}
