package gui;

import gui.GraphicFactory;
import gui.Table;
import gui.TableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.List;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import dto.OrderDTO;
import dto.TableDTO;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class OrderListDialog extends JFrame implements IDialog
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
	
	private JLabel lUser;
	private JComboBox cbUsers;
	private JButton bSaveUser;
	private JButton bEditUser;
	private JButton bCalc;
	
	public OrderListDialog(IOrderListController controller)
	{
		this.controller = controller;
		this.initComponents();
	}
	
	@Override
	public void initComponents()
	{
		setTitle(graphicalFactory.getProperty("title.orderList.dialog"));
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
		
		getContentPane().add(graphicalFactory.createLabel("label.table"), "2, 2, right, default");

		JComboBox comboBox = new JComboBox(controller.getTableList());
		getContentPane().add(comboBox, "4, 2, fill, default");
		
		JButton logout = GraphicFactory.getInstance().createImageButton("logout", true);
		getContentPane().add(logout, "8, 2, right, default");
		
		JButton calcButton = GraphicFactory.getInstance().createImageButton("calculation", true);
		getContentPane().add(calcButton, "6, 2");
		getContentPane().add(graphicalFactory.createLabel("label.user"), "2, 4");
		lUser = graphicalFactory.createLabel("label.user.name");
		getContentPane().add(graphicalFactory.createLabel("label.user.name"), "4, 4");
		
		cbUsers = new JComboBox(controller.getUserList());
		getContentPane().add(cbUsers, "4, 4, fill, default");
		cbUsers.setVisible(false);
		
		if(controller.isCurrentUserManager())
		{
			bCalc = GraphicFactory.getInstance().createImageButton("calculation", true);
			getContentPane().add(bCalc, "6, 2");
			bCalc.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					controller.openCalculate();
				}
			});
		}
		

		bEditUser = GraphicFactory.getInstance().createImageButton("edit", true);
		getContentPane().add(bEditUser, "6, 4");
		
		bSaveUser = GraphicFactory.getInstance().createImageButton("save", true);
		getContentPane().add(bSaveUser, "6, 4");

		bEditUser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				setEnableChangeUser(true);
			}
		});
		
		bSaveUser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.changeUser(cbUsers.getSelectedItem().toString(), controller.getSelectedTable());
			}
		});
		
		comboBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				controller.setSelectedTable((Integer)e.getItem());
			}
		});
		
		logout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.logout();
			}
		});
		
		calcButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				controller.openCalculate();			
			}
		});
	
		cnames  = new String[]{ 
				graphicalFactory.getProperty("label.first.col.order.list"), 
				graphicalFactory.getProperty("label.second.col.order.list"), 
				graphicalFactory.getProperty("label.third.col.order.list"), 
				graphicalFactory.getProperty("label.fourth.col.order.list"), 
				graphicalFactory.getProperty("label.fith.col.order.list") };
		
		table = new Table();
		table.setVisible(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(25);
		getContentPane().add(new JScrollPane(table), "2, 6, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
		setEnableChangeUser(false);
	}
	
	private void setEnableChangeUser(boolean enable)
	{
		lUser.setVisible(!enable);
		cbUsers.setVisible(enable);
		cbUsers.setEnabled(enable);
		bEditUser.setVisible(!enable);
		bEditUser.setEnabled(!enable);
		bSaveUser.setVisible(enable);
		bSaveUser.setEnabled(enable);
	}
	
	public void updateModel()
	{
		if(controller.isTableSelected())
		{
			TableDTO tableDTO = controller.getTableDTO(controller.getSelectedTable());
			Collection<OrderDTO> orderList =  tableDTO.getOrders();
			data = new Object[orderList.size()+1][COL_COUNT];
			
			int row = 0;
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
						case COL_PRICE : 
							data[row][col] = orderDTO.getPrice() + " " + graphicalFactory.getProperty("currency.symbol"); break;
						case COL_BUTTON_EDIT : 
						{
							JButton b = graphicalFactory.createTableButton("edit"); 
							b.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									controller.openEditOrder(table.getSelectedRow());	
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
									controller.closeOrder((Integer) table.getModel().getValueAt(table.getSelectedRow(), COL_ID));	
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
					controller.openAddOrder();
				}
			});
			data[data.length-1][data[0].length-1] = b;
			table.setModel(new TableModel(cnames,data));
		}
	}
	
	
}
