package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.gvsBase.Order;
import model.gvsBase.Product;
import model.gvsBase.ProductType;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

public class OrderPersistenz implements IOrderPersistenz
{
	private String filename;
	private SAXBuilder docBuilder;
	private Document document;
	private final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
	
	public OrderPersistenz(String filename) throws IOException
	{
		this.filename = filename;
		this.docBuilder = new SAXBuilder();
	}
	
	@Override
	public void saveOrders(Collection<Order> orderList) throws IOException
	{
		for (Order order : orderList)
		{
			saveOrder(order);
		}
	}
	
	@Override
	public void saveOrder(Order order) throws IOException
	{
		Element orderElement = getOrder(order);
		
		// neues Element erstellen
		if(orderElement == null)
		{
			orderElement = new Element("bestellung");
			orderElement = generateOrderXML(orderElement, order);
			document.getRootElement().addContent(orderElement);
		}
		else // altes Element updaten
		{
			orderElement.removeContent();
			orderElement = generateOrderXML(orderElement, order);
		}
	}
	
	@Override
	public Set<Order> getOrders(Date start, Date end) throws IOException
	{
		Element root = document.getRootElement();
		
		Set<Order> result = new HashSet<Order>();
		List orders = root.getChildren();
		for(Object o : orders)
		{
			Element orderElement = (Element)o;
			try
			{
				Date elementDate = dateFormat.parse(orderElement.getChild("datum").getText());
				if(elementDate.after(start) && elementDate.before(end))
				{
					result.add(parseOrderElement(orderElement));
				}
			}
			catch(ParseException e)
			{
				throw new IOException("wrong date format!");
			}
		}
		return result;
	}
	
	@Override
	public void endSession() throws IOException
	{
		XMLOutputter out = new XMLOutputter();
		out.setFormat(Format.getPrettyFormat());
		OutputStream outs = new FileOutputStream(filename);
		out.output(document, outs);
	}
	
	@Override
	public void startSession() throws IOException
	{
		try
		{
			if((new File(filename)).exists())
			{
				document = docBuilder.build(filename);
			}
			else
			{
				document = new Document(new Element("bestellungen"));
			}
		}
		catch (JDOMException e)
		{
			throw new IOException(e.getMessage());
		}
	}
	
	
	private Order parseOrderElement(Element element) throws IOException
	{
		Collection<Product> productList = new ArrayList<Product>();
		Element products = element.getChild("produkte");
		for(Object o : products.getChildren())
		{
			Element productElement = (Element)o;
			ProductType type = productElement.getChild("art").getText().equals("Getränk")?ProductType.drink:ProductType.food;
			Product p = new Product(productElement.getChild("name").getText(), Float.valueOf(productElement.getChild("preis").getText()), type);
			productList.add(p);
		}
		
		Order order;
		try
		{
			order = new Order(Long.valueOf(element.getChild("id").getText()), 
					!Boolean.valueOf(element.getChild("offen").getText()), 
					dateFormat.parse(element.getChild("datum").getText()), 
					productList);
		}
		catch (Exception e)
		{
			throw new IOException("date parse error while read from xml file: " + filename);
		}
		
		return order;
	}

	private Element getOrder(Order order)
	{
		Element root = document.getRootElement();
		List orders = root.getChildren();
		for(Object o : orders)
		{
			Element orderElement = (Element)o;
			if(Long.valueOf(orderElement.getChild("id").getText()) == order.getId())
			{
				return orderElement;
			}
		}
		return null;
	}
	
	private Element generateOrderXML(Element orderElement, Order order)
	{
		Element root = orderElement;
		Element id = new Element("id");
		id.addContent(order.getId()+"");
		
		Element closed = new Element("offen");
		closed.addContent(!order.isClosed()+"");
		
		Element date = new Element("datum");
		date.addContent(dateFormat.format(order.getDate()));
		
		Element products = new Element("produkte");
		
		for(Product p : order.getProducts())
		{
			products.addContent(generatePruductXML(p));
		}
		
		root.addContent(id);
		root.addContent(closed);
		root.addContent(date);
		root.addContent(products);
		return root;
	}
	
	
	private Element generatePruductXML(Product product)
	{
		Element root = new Element("produkt");
		
		Element productType = new Element("art");
		productType.addContent((product.getType().equals(ProductType.food))?"Gericht":"Getränk");
		
		Element price = new Element("preis");
		price.addContent(product.getPrice()+"");
		
		Element name = new Element("name");
		name.addContent(product.getName());
		
		root.addContent(productType);
		root.addContent(price);
		root.addContent(name);
		
		return root;
	}
	
		
	// test!
	public static void main(String[] args) throws Exception
	{
		OrderPersistenz persistenz = new OrderPersistenz("test.xml");
		XMLOutputter out = new XMLOutputter();
		out.setFormat(Format.getPrettyFormat());
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
		
		Order order = new Order(12345);
		Product p1 = new Product("Zucker", 3.5f, ProductType.food);
		Product p2 = new Product("Sprite", 1.4f, ProductType.drink);
		order.addProdukt(p1);
		order.addProdukt(p2);
		
		
		Order order3 = new Order(343);
		Product p5 = new Product("Torte", 3.5f, ProductType.food);
		Product p6 = new Product("Wasser", 1.4f, ProductType.drink);
		order3.addProdukt(p5);
		order3.addProdukt(p6);
		
		List<Order> list = new ArrayList<Order>();
		list.add(order);
		list.add(order3);
				
		
		//out.output(persistenz.generateOrderXML(order), outs);
		//outs = System.out;
		persistenz.startSession();
		persistenz.saveOrders(list);

		System.out.println(dateFormat.parse("19.12.2011 19:25"));
		Collection<Order> orderList = persistenz.getOrders(dateFormat.parse("19.12.2011 19:25"), dateFormat.parse("19.12.2011 20:50"));
		for(Order o : orderList)
		{
			System.out.println(o);
		}
		persistenz.endSession();
	}
}
