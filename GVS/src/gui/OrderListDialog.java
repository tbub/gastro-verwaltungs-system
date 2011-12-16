package gui;

import gui.GraphicFactory;
import gui.Table;
import gui.TableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import dto.OrderDTO;
import dto.TableDTO;

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
	private String[]  cnames;
	private IGraphicFactory graphicalFactory = GraphicFactory.getInstance();
	private static final int COL_COUNT = 5;
	
	private static final int COL_ID = 0;
	private static final int COL_PRODUCTS = 1;
	private static final int COL_PRICE = 2;
	private static final int COL_BUTTON_EDIT = 3;
	private static final int COL_BUTTON_CLOSE = 4;
	
	public OrderListDialog(IOrderListController controller)
	{
		this.controller = controller;
		this.initComponents();
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
	
		cnames  = new String[]{ "#", "Produkte", "Gesamtpreis", "Bearbeiten", "Schließen" };
		
		table = new Table(new TableModel(cnames,data));
		table.setVisible(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(25);
		getContentPane().add(new JScrollPane(table), "2, 6, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
		
	}
	
	public void updateModel()
	{
		if(controller.isTableSelected())
		{
			TableDTO tableDTO = controller.getTableDTO(controller.getSelectedTable());
			data = new Object[tableDTO.getOrderList().size()+1][COL_COUNT];
			
			int row = 0;
			List<OrderDTO> orderList =  tableDTO.getOrderList();
			for(OrderDTO orderDTO : orderList)
			{
				for(int col = 0; col < orderList.size(); col++)
				{
					switch(col)
					{
						case COL_ID: data[row][col] = orderDTO.getId(); break;
						case COL_PRODUCTS : 
						{
							String [] products = orderDTO.getProductNames();
							for(int i = 0; i < products.length-2; i++)
							{
								data[row][col] = data[row][col] + ", " + products[i]+"";
							}
							data[row][col] = data[row][col] + ", " + products[products.length-1];	
						}break;
						case COL_PRICE : data[row][col] = orderDTO.getPrice() + " €"; break;
						case COL_BUTTON_EDIT : 
						{
							JButton b = graphicalFactory.createTableButton("edit"); 
							b.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									controller.editOrder(table.getSelectedRow());	
								}
							});
							data[row][col] = b; 
						}
						break;
						case COL_BUTTON_CLOSE : 
						{
							JButton b = graphicalFactory.createTableButton("close"); 
							b.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									controller.closeOrder(table.getSelectedRow());	
								}
							});
						}
						break;
					}
				}
			}
			JButton b = graphicalFactory.createTableButton("add"); 
			b.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					controller.addOrder();
				}
			});
			data[data.length-1][data[0].length-1] = b;
			table = new Table(new TableModel(cnames,data));
		}
	}
	
	
}
