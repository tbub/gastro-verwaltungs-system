package gui.order;

import javax.swing.JDialog;
import gui.GraphicFactory;
import gui.IDialog;
import gui.IGraphicFactory;
import gui.Table;
import gui.TableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Set;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import dto.ProductDTO;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class OrderDialog extends JDialog implements IDialog
{
	private Object[][] data;
	private String[] cnames;
	private IGraphicFactory graphicalFactory = GraphicFactory.getInstance();
	private IOrderController controller;
	private JTable table;
	private JButton bAddProduct;
	private JButton bEditTable;
	private JLabel lTableId;
	private JComboBox cbTalbeIds;
	private JButton bSaveTable;
	
	public OrderDialog(IOrderController controller, Window owner)
	{
		super(owner);
		this.controller = controller;
		this.initComponents();
	}
	
	@Override
	public void initComponents()
	{
		getContentPane().removeAll();
		setTitle(graphicalFactory.getProperty("title.order.dialog") + " #" + controller.getOrderId());
		setVisible(true);
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

		getContentPane().add(graphicalFactory.createLabel("label.table"), "2, 2, right, default");
				
		lTableId = new JLabel("#"+controller.getTableId());
		getContentPane().add(lTableId, "4, 2");
		
		cbTalbeIds = new JComboBox(controller.getTableIds());
		getContentPane().add(cbTalbeIds, "4, 2");

		bEditTable = graphicalFactory.createImageButton("edit", true);
		getContentPane().add(bEditTable, "8, 2");
		bEditTable.setVisible(false);
		
		bSaveTable = graphicalFactory.createImageButton("save", true);
		getContentPane().add(bSaveTable, "8, 2");
		
		cnames  = new String[]{ 
				graphicalFactory.getProperty("label.first.col.order"), 
				graphicalFactory.getProperty("label.second.col.order"), 
				graphicalFactory.getProperty("label.third.col.order"), 
				graphicalFactory.getProperty("label.fourth.col.order")};
		
		table = new Table();
		table.setRowMargin(5);
		table.setVisible(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(40);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, "2, 4, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
		setEnableChangeTable(false);
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				controller.closeDialog();
			}
		});
		
		bAddProduct = graphicalFactory.createTableButton("add");
		bAddProduct.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.openAddProductDialog();				
			}
		});
		
		bEditTable.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				setEnableChangeTable(true);
			}
		});
		
		bSaveTable.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.setTable(Integer.valueOf(cbTalbeIds.getSelectedItem().toString()));
				setEnableChangeTable(false);
			}
		});
	}
	
	private void setEnableChangeTable(boolean enable)
	{
		lTableId.setVisible(!enable);
		cbTalbeIds.setVisible(enable);
		boolean tmp = !controller.getProducts().isEmpty();
		bEditTable.setVisible(!enable && tmp);
		bSaveTable.setVisible(enable); 
				
		
		lTableId.setEnabled(!enable);
		cbTalbeIds.setEnabled(enable);
		bEditTable.setEnabled(!enable && tmp);
		bSaveTable.setEnabled(enable);
	}
	
	@Override
	public void enable(boolean b)
	{
		bAddProduct.setEnabled(b);
	}
	
	public void updateModel()
	{
		int row = 0;
		Collection<ProductDTO> products = controller.getProducts();
		data = new Object[products.size()+1][4];
		for(ProductDTO product : products)
		{
			data[row][0] = (row+1)+"";
			data[row][1] = product.getName();
			data[row][2] = product.getPrice()+graphicalFactory.getProperty("currency.symbol");
			data[row][3] = graphicalFactory.createImageButton(product.getType().getName(), false);
			row++;
		}
		data[products.size()][3] = bAddProduct;
		table.setModel(new TableModel(cnames, data));
		lTableId.setText(controller.getTableId()+"");
		if(!products.isEmpty())
		{
			bEditTable.setVisible(true);
			bEditTable.setEnabled(true);
		}
	}
}
