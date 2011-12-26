package gui.order;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import gui.GraphicFactory;
import gui.IDialog;
import gui.IGraphicFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import model.gvsBase.ProductType;
import dto.ProductDTO;
import dto.ProductTypeDTO;

public class AddProductDialog extends JDialog implements IDialog
{
	private IGraphicFactory graphicFactory;
	private JLabel lPrice;
	private JButton bAdd;
	private JComboBox cbProductNames;
	private JComboBox cbProductTypes;
	private IAddProductController controller;
	
	public AddProductDialog(IAddProductController controller, Window owner)
	{
		super(owner);
		this.controller = controller;
		graphicFactory = GraphicFactory.getInstance();
		this.initComponents();
	}
	
	
	@Override
	public void initComponents()
	{
		setTitle(graphicFactory.getProperty("title.add.product.dialog"));
		setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel lblTyp = graphicFactory.createLabel("label.typ");
		lblTyp.setBounds(25, 37, 61, 16);
		getContentPane().add(lblTyp);
		
		JLabel lblProdukt = graphicFactory.createLabel("label.product");
		lblProdukt.setBounds(25, 70, 61, 16);
		getContentPane().add(lblProdukt);
		
		cbProductNames = new JComboBox();
		cbProductNames.setBounds(98, 66, 162, 27);
		getContentPane().add(cbProductNames);
		
		cbProductTypes = new JComboBox(new String[]{graphicFactory.getProperty("text.food"), graphicFactory.getProperty("text.drink")});
		cbProductTypes.setBounds(98, 33, 162, 27);
		getContentPane().add(cbProductTypes);
		
		JLabel lblPreis = graphicFactory.createLabel("label.price");
		lblPreis.setBounds(25, 104, 61, 16);
		getContentPane().add(lblPreis);
		
		lPrice = new JLabel("0,00" + graphicFactory.getProperty("currency.symbol"));
		lPrice.setBounds(98, 104, 61, 16);
		getContentPane().add(lPrice);
		
		bAdd = graphicFactory.createImageButton("save", true);
		bAdd.setBounds(185, 102, 117, 29);
		getContentPane().add(bAdd);
		
		bAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.addProduct(cbProductNames.getSelectedItem().toString());
				controller.closeDialog();
				AddProductDialog.this.dispose();
			}
		});
		
		/*cbProductNames.addItemListener(new ItemListener()
		{	
			@Override
			public void itemStateChanged(ItemEvent event)
			{
				if(((ProductDTO)event.getItem()).getType().toString().equals("drink"))
				{
					controller.setSelectedProductType(new ProductTypeDTO(ProductType.drink));
				}
				else
				{
					controller.setSelectedProductType(new ProductTypeDTO(ProductType.food));
				}
			}
		});*/
		
		cbProductTypes.addItemListener(new ItemListener()
		{	
			@Override
			public void itemStateChanged(ItemEvent event)
			{
				System.out.println(event.getItem().toString());
				if(event.getItem().toString().equals(graphicFactory.getProperty("text.drink")))
				{
					controller.setSelectedProductType(new ProductTypeDTO(ProductType.drink));
				}
				else
				{
					controller.setSelectedProductType(new ProductTypeDTO(ProductType.food));
				}
			}
		});
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				controller.closeDialog();
				AddProductDialog.this.dispose();
			}
		});
		setMinimumSize(new Dimension(300, 400));
	}
	
	public void updateModel()
	{
		cbProductNames.removeAllItems();
		for(ProductDTO p : controller.getProducts())
		{
			cbProductNames.addItem(p.getName());
		}
	}
}
