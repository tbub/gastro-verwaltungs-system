package protptype;
import gui.GraphicFactory;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class AddProduct extends JDialog
{
	public AddProduct() {
		setTitle("Produkt hinzuf\u00FCgen");
		getContentPane().setLayout(null);
		
		JLabel lblTyp = new JLabel("Typ:");
		lblTyp.setBounds(25, 37, 61, 16);
		getContentPane().add(lblTyp);
		
		JLabel lblProdukt = new JLabel("Produkt:");
		lblProdukt.setBounds(25, 70, 61, 16);
		getContentPane().add(lblProdukt);
		
		JComboBox comboBox = new JComboBox(new String[]{"Schnitzel", "Pommes", "Schweinebraten", "Salat", "Nudelgericht", "Fisch"});
		comboBox.setBounds(98, 66, 162, 27);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(new String[]{"Gericht", "Getr�nk"});
		comboBox_1.setBounds(98, 33, 162, 27);
		getContentPane().add(comboBox_1);
		
		JLabel lblPreis = new JLabel("Preis:");
		lblPreis.setBounds(25, 104, 61, 16);
		getContentPane().add(lblPreis);
		
		JLabel label = new JLabel("10,50 \u20AC");
		label.setBounds(98, 104, 61, 16);
		getContentPane().add(label);
		
		JButton btnHinzufgen = GraphicFactory.getInstance().createImageButton("save", true);
		btnHinzufgen.setBounds(185, 102, 117, 29);
		getContentPane().add(btnHinzufgen);
	}
	
	public static void main(String[] args)
	{
		new AddProduct().setVisible(true);
	}
}
