package protptype;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class BillCalculation extends JDialog
{
	private JTextField textField;
	private JTextField textField_1;
	public BillCalculation() {
		setTitle("Gesamtabrechnung");
		getContentPane().setLayout(null);
		
		JLabel lblStartdatum = new JLabel("Anfangsdatum:");
		lblStartdatum.setBounds(29, 37, 116, 16);
		getContentPane().add(lblStartdatum);
		
		JLabel lblNewLabel = new JLabel("Enddatum:");
		lblNewLabel.setBounds(29, 77, 97, 16);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(138, 31, 134, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(138, 71, 134, 28);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnBerechnen = new JButton("berechnen");
		btnBerechnen.setBounds(155, 111, 117, 29);
		getContentPane().add(btnBerechnen);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new BillCalculation().setVisible(true);

	}
}
