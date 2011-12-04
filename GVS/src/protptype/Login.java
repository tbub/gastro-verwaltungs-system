package protptype;
import gui.GVSGraphicFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Login extends JFrame
{
	private JPasswordField passwordField;
	private JTextField textField;
	public Login() {
		setTitle("GVS Anmeldung");
		getContentPane().setLayout(null);
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setBounds(16, 25, 100, 16);
		getContentPane().add(lblBenutzername);
		
		JLabel lblKennwort = new JLabel("Kennwort:");
		lblKennwort.setBounds(16, 65, 100, 16);
		getContentPane().add(lblKennwort);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 59, 178, 28);
		getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(128, 19, 178, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAnmelden = GVSGraphicFactory.createImageButton("login", true);
		btnAnmelden.setBounds(235, 99, 117, 29);
		getContentPane().add(btnAnmelden);
	}
	
	public static void main(String[] args)
	{
		new Login().setVisible(true);
	}
}
