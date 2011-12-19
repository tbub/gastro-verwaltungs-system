package gui.order;

import javax.swing.JDialog;
import gui.GraphicFactory;
import gui.IDialog;
import gui.IGraphicFactory;
import gui.Table;
import gui.TableModel;
import java.awt.Color;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import dto.ProductDTO;
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
	
	public OrderDialog(IOrderController controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void initComponents()
	{
		setTitle(graphicalFactory.getProperty("title.order.dialog") + " #" + controller.getOrderId());
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
				
		JLabel label = new JLabel("#6");
		getContentPane().add(label, "4, 2");

		JButton btnEdit = graphicalFactory.createImageButton("edit", true);
		getContentPane().add(btnEdit, "8, 2");		
		
		cnames  = new String[]{ 
				graphicalFactory.getProperty("label.first.col.order"), 
				graphicalFactory.getProperty("label.second.col.order"), 
				graphicalFactory.getProperty("label.third.col.order"), 
				graphicalFactory.getProperty("label.fourth.col.order")};
		
		table = new Table(new TableModel(cnames,data));
		table.setRowMargin(5);
		table.setVisible(true);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setRowHeight(40);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, "2, 4, 7, 1, fill, fill");
		setMinimumSize(new Dimension(400, 550));
	}
	
	public void updateModel()
	{
		int row = 0;
		for(ProductDTO product : controller.getProducts())
		{
			data[row][0] = ""+row+1;
			data[row][1] = product.getName();
			data[row][2] = product.getPrice()+graphicalFactory.getProperty("currency.symbol");
			data[row][3] = graphicalFactory.createImageButton("eat", false);
		}
	}

}
