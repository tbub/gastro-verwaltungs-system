package gui.login;

import gui.GraphicFactory;
import gui.IDialog;
import gui.IGraphicFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import model.gvsUC.GvsUsecaseController;

public class LoginDialog extends JFrame implements IDialog
{
	private JPasswordField passwordField;
	private JTextField textField;
	private IGraphicFactory graphicFactory;
	private ILoginController controller;
	
	public LoginDialog(ILoginController controller) 
	{
		this.controller = controller;
		graphicFactory = GraphicFactory.getInstance();
		initComponents();
	}
	
	@Override
	public void initComponents()
	{
		setTitle(graphicFactory.getProperty("title.login.dialog"));
		getContentPane().setLayout(null);

		JLabel lblBenutzername = graphicFactory.createLabel("label.user.name");
		lblBenutzername.setBounds(16, 25, 100, 16);
		getContentPane().add(lblBenutzername);
		
		JLabel lblKennwort = graphicFactory.createLabel("label.user.password");
		lblKennwort.setBounds(16, 65, 100, 16);
		getContentPane().add(lblKennwort);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 59, 178, 28);
		getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(128, 19, 178, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAnmelden = GraphicFactory.getInstance().createImageButton("login", true);
		btnAnmelden.setBounds(235, 99, 117, 29);
		getContentPane().add(btnAnmelden);
		setMaximumSize(new Dimension(350, 160));
		setMinimumSize(this.getMaximumSize());
		setResizable(false);
		setVisible(true);
		
		setAlwaysOnTop(true);
		
		btnAnmelden.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(controller.login(textField.getText(), new String(passwordField.getPassword())))
				{
					controller.openOrderListDialog();
					LoginDialog.this.dispose();
				}
				else
				{
					graphicFactory.showErrorDialog("text.error.login");
				}
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		// start der Anwendung
		new LoginDialog(new LoginController(GvsUsecaseController.getInstance()));
	}

	@Override
	public void updateModel(){}
}
