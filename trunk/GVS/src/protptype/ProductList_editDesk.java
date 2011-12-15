package protptype;
import gui.GraphicFactory;
import gui.Table;
import gui.TableModel;
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

public class ProductList_editDesk extends JFrame
{
	private JTable table;

	public ProductList_editDesk()
	{
		setTitle("Bestellung #1");
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:6dlu"),
				ColumnSpec.decode("left:pref"),
				ColumnSpec.decode("left:3dlu"),
				ColumnSpec.decode("left:pref:grow"),
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
				
				JComboBox comboBox = new JComboBox(new String[]{"Tisch 1", "Tisch 2", "Tisch 3", "Tisch 4"});
				getContentPane().add(comboBox, "4, 2, fill, default");
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
				{ "1", "Schnitzel", "10,50 �", GraphicFactory.getInstance().createImageButton("eat", false)},
				{ "2", "Wei�wurst", "8,50 �", GraphicFactory.getInstance().createImageButton("eat", false)},
				{ "3", "Wein", "13,50 �", GraphicFactory.getInstance().createImageButton("drink", false)},
				{ "4", "Salat", "3,50 �", GraphicFactory.getInstance().createImageButton("eat", false)},
				{ "5", "Pommes", "7,50 �", GraphicFactory.getInstance().createImageButton("drink", false)},
				{ "3", "Schweinebraten", "11,32 �", GraphicFactory.getInstance().createImageButton("eat", false)},
				{ "", "", "", GraphicFactory.getInstance().createTableButton("add")}
		};
		String[] cnames  = { "#", "Produkt", "Einzelpreis", "Typ" };
		
		table = new Table(new TableModel(cnames,data));
		table.setRowMargin(5);
		table.setVisible(true);
		
				JButton btnEdit = GraphicFactory.getInstance().createImageButton("save", false);
				getContentPane().add(btnEdit, "6, 2");
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
		new ProductList_editDesk().setVisible(true);
	}
}
