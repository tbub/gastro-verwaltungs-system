package gui;

import javax.swing.JFrame;
import gui.GraphicFactory;
import gui.Table;
import gui.TableModel;
import java.awt.Color;
import java.awt.Dimension;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class OrderListDialog extends AbstractDialog
{
	private IOrderListController controller;
	private JTable table;
	private Object[][] data;
	private IGraphicFactory graphicalFactory = GraphicFactory.getInstance();
	
	public OrderListDialog(IOrderListController controller)
	{
		this.controller = controller;
	}
	
	@Override
	protected void initComponents()
	{
		setTitle("Bestellungen");
		getContentPane().setLayout(new FormLayout(
			new ColumnSpec[] {
				ColumnSpec.decode("left:6dlu"),
				ColumnSpec.decode("left:pref"),
				ColumnSpec.decode("left:3dlu"),
				ColumnSpec.decode("left:pref"),
				ColumnSpec.decode("left:3dlu"),
				ColumnSpec.decode("left:pref"),
				ColumnSpec.decode("left:3dlu"),
				ColumnSpec.decode("left:pref:grow"),
				ColumnSpec.decode("right:6dlu"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("pref:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,}));

		JLabel lblTisch = new JLabel("Tisch:");
		getContentPane().add(lblTisch, "2, 2, right, default");

		JComboBox comboBox = new JComboBox(new String[]{"Tisch 1", "Tisch 2"});
		getContentPane().add(comboBox, "4, 2, fill, default");
		
		JButton logout = GraphicFactory.getInstance().createImageButton("logout", true);
		getContentPane().add(logout, "8, 2, right, default");
		
		getContentPane().add(GraphicFactory.getInstance().createImageButton("calculation", true), "6, 2");

		JLabel lblKellner = new JLabel("Kellner:");
		getContentPane().add(lblKellner, "2, 4");

		JLabel lblKellnername = new JLabel("Kellnername");
		getContentPane().add(lblKellnername, "4, 4");

		JButton btnEdit = GraphicFactory.getInstance().createImageButton("edit", true);
		getContentPane().add(btnEdit, "6, 4");
		Icon icon = new ImageIcon("icons/edit.png");
		JButton test = new JButton();
		test.setIcon(icon);
		test.setRolloverIcon(icon);
		test.setPressedIcon(icon);
		test.setBorder(null);
        test.setContentAreaFilled(false);
		test.setFocusPainted(false);
		
		Object[][] data =
		{
				{ "1", "Schnitzel, Weißwurst, Wein", "24,50 €", GraphicFactory.getInstance().createTableButton("edit"), GraphicFactory.getInstance().createTableButton("close")},
				{ "2", "Pommes, Salat", "7,50 €", GraphicFactory.getInstance().createTableButton("edit"), GraphicFactory.getInstance().createTableButton("close")},
				{ "3", "Schweinebraten, Cola, Nudeln, Fisch", "40 €", GraphicFactory.getInstance().createTableButton("edit"), GraphicFactory.getInstance().createTableButton("close")},
				{ "7", "Nudeln", "9,50 €", GraphicFactory.getInstance().createTableButton("edit"), GraphicFactory.getInstance().createTableButton("close")},
				{ "9", "Fisch", "11,50 €", GraphicFactory.getInstance().createTableButton("edit"), GraphicFactory.getInstance().createTableButton("close")},
				{ "15", "Eis", "2,50 €", "", GraphicFactory.getInstance().createTableButton("closed")},
				{ "17", "Bier", "3,50 €", "", GraphicFactory.getInstance().createTableButton("closed")},
				{ "", "", "", "", GraphicFactory.getInstance().createTableButton("add")}
		};
		String[] cnames  = { "#", "Produkte", "Gesamtpreis", "Bearbeiten", "Schließen" };
		
		table = new Table(new TableModel(cnames,data));
		table.setVisible(true);
		//table.setFillsViewportHeight(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(25);
		getContentPane().add(new JScrollPane(table), "2, 6, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
		
	}
	
	private void updateModel()
	{
		if(controller.isTableSelected())
		{
			String[][] data = controller.getOrders(controller.getSelectedTable());
			Object[][] tableData = new Object[data.length][data[0].length+2];		// add buttons!
			for(int row = 0; row < tableData.length; row++)
			{
				for(int col = 0; col < tableData[0].length; col++)
				{
					if(col < data[0].length)
					{
						tableData[row][col] = data[row][col];
					}
					if(controller.isOrderClosed(OrderID))
					if(col == tableData[0].length-2)
					{
						tableData[row][col] = graphicalFactory.createTableButton("edit");
					}
					else if(col == tableData[0].length-1)
					{
						tableData[row][col] = graphicalFactory.createTableButton("close");
					}
				}
			}
		}
	}
	
	
}
