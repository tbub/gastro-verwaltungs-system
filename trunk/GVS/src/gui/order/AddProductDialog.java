package gui.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gui.GraphicFactory;
import gui.IDialog;
import gui.IGraphicFactory;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import dto.ProductDTO;

public class AddProductDialog extends JDialog implements IDialog
{
	private IGraphicFactory graphicFactory;
	private JLabel lPrice;
	private JButton bAdd;
	private JComboBox cbProductNames;
	private JComboBox cbProductTypes;
	private IAddProductController controller;
	
	public AddProductDialog(IAddProductController controller)
	{
		this.controller = controller;
		graphicFactory = GraphicFactory.getInstance();
	}
	
	
	@Override
	public void initComponents()
	{
		setTitle(graphicFactory.getProperty("title.add.product.dialog"));
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
			}
		});
	}
	
	public void updateModel()
	{
		for(ProductDTO p : controller.getProducts())
		{
			cbProductNames.addItem(p.getName());
		}
	}

}
