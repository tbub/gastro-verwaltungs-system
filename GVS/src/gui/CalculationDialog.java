package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CalculationDialog extends JDialog implements IDialog
{
	private JTextField tStartDate;
	private JTextField tEndDate;
	private JButton bCalculate;
	private ICalculationController controller;
	private IGraphicFactory graphFactory;
	
	public CalculationDialog(ICalculationController controller)
	{
		this.controller = controller;
		this.graphFactory = GraphicFactory.getInstance();
	}
	
	@Override
	public void initComponents()
	{
		setTitle(graphFactory.getProperty("title.calc.dialog"));
		getContentPane().setLayout(null);
		
		JLabel lblStartdatum = graphFactory.createLabel("label.data.start");
		lblStartdatum.setBounds(29, 37, 116, 16);
		getContentPane().add(lblStartdatum);
		
		JLabel lblNewLabel = graphFactory.createLabel("label.date.end");
		lblNewLabel.setBounds(29, 77, 97, 16);
		getContentPane().add(lblNewLabel);
		
		tStartDate = new JTextField();
		tStartDate.setBounds(138, 31, 134, 28);
		getContentPane().add(tStartDate);
		tStartDate.setColumns(10);
		
		tEndDate = new JTextField();
		tEndDate.setBounds(138, 71, 134, 28);
		getContentPane().add(tEndDate);
		tEndDate.setColumns(10);
		
		bCalculate = GraphicFactory.getInstance().createImageButton("login", true);
		bCalculate.setBounds(155, 111, 117, 29);
		getContentPane().add(bCalculate);
		
		bCalculate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.calculate(tStartDate.getText(), tEndDate.getText());
				CalculationDialog.this.dispose();
			}
		});
	}

}
