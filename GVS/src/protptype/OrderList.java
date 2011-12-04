package protptype;
import gui.GVSGraphicFactory;
import gui.GVSTable;
import gui.GVSTableModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
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

public class OrderList extends JFrame
{
	private JTable table;

	public OrderList()
	{
		setTitle("Bestellungen");
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
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
		
		JButton logout = GVSGraphicFactory.createImageButton("logout", true);
		getContentPane().add(logout, "8, 2, right, default");
		
		getContentPane().add(GVSGraphicFactory.createImageButton("calculation", true), "6, 2");

		JLabel lblKellner = new JLabel("Kellner:");
		getContentPane().add(lblKellner, "2, 4");

		JLabel lblKellnername = new JLabel("Kellnername");
		getContentPane().add(lblKellnername, "4, 4");

		JButton btnEdit = GVSGraphicFactory.createImageButton("edit", true);
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
				{ "1", "Schnitzel, Wei§wurst, Wein", "24,50 Û", GVSGraphicFactory.createTableButton("edit"), GVSGraphicFactory.createTableButton("close")},
				{ "2", "Pommes, Salat", "7,50 Û", GVSGraphicFactory.createTableButton("edit"), GVSGraphicFactory.createTableButton("close")},
				{ "3", "Schweinebraten, Cola, Nudeln, Fisch", "40 Û", GVSGraphicFactory.createTableButton("edit"), GVSGraphicFactory.createTableButton("close")},
				{ "7", "Nudeln", "9,50 Û", GVSGraphicFactory.createTableButton("edit"), GVSGraphicFactory.createTableButton("close")},
				{ "9", "Fisch", "11,50 Û", GVSGraphicFactory.createTableButton("edit"), GVSGraphicFactory.createTableButton("close")},
				{ "15", "Eis", "2,50 Û", "", GVSGraphicFactory.createTableButton("closed")},
				{ "17", "Bier", "3,50 Û", "", GVSGraphicFactory.createTableButton("closed")},
				{ "", "", "", "", GVSGraphicFactory.createTableButton("add")}
		};
		String[] cnames  = { "#", "Produkte", "Gesamtpreis", "Bearbeiten", "Schlie§en" };
		
		table = new GVSTable(new GVSTableModel(cnames,data));
		table.setVisible(true);
		//table.setFillsViewportHeight(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(25);
		getContentPane().add(new JScrollPane(table), "2, 6, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
	}
	
	public static void main(String[] args)
	{
		new OrderList().setVisible(true);
	}
}
