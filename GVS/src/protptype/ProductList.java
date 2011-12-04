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

public class ProductList extends JFrame
{
	private JTable table;

	public ProductList()
	{
		setTitle("Bestellung #1");
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
				ColumnSpec.decode("left:6dlu"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("pref:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,}));

		JLabel lblTisch = new JLabel("Tisch:");
		getContentPane().add(lblTisch, "2, 2, right, default");
				
				JLabel label = new JLabel("#6");
				getContentPane().add(label, "4, 2");
		
				JButton btnEdit = GVSGraphicFactory.createImageButton("edit", true);
				getContentPane().add(btnEdit, "8, 2");
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
				{ "1", "Schnitzel", "10,50 Û", GVSGraphicFactory.createImageButton("eat", false)},
				{ "2", "Wei§wurst", "8,50 Û", GVSGraphicFactory.createImageButton("eat", false)},
				{ "3", "Wein", "13,50 Û", GVSGraphicFactory.createImageButton("drink", false)},
				{ "4", "Salat", "3,50 Û", GVSGraphicFactory.createImageButton("eat", false)},
				{ "5", "Pommes", "7,50 Û", GVSGraphicFactory.createImageButton("drink", false)},
				{ "3", "Schweinebraten", "11,32 Û", GVSGraphicFactory.createImageButton("eat", false)},
				{ "", "", "", GVSGraphicFactory.createTableButton("add")}
		};
		String[] cnames  = { "#", "Produkt", "Einzelpreis", "Typ" };
		
		table = new GVSTable(new GVSTableModel(cnames,data));
		table.setRowMargin(5);
		table.setVisible(true);
		//table.setFillsViewportHeight(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(40);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, "2, 4, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
	}
	
	public static void main(String[] args)
	{
		new ProductList().setVisible(true);
	}
}
