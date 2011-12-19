package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.gvsBase.Order;
import model.gvsBase.Product;
import model.gvsBase.ProductType;
import model.gvsBase.Table;
import model.gvsBase.User;
import model.gvsBase.UserType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Database implements IDatabase{
	
	private String pathOfUsers_csv, pathOfProducts_csv, pathOfTables_xml, pathOfUsersOnTables_csv;
	DocumentBuilder docBuilder;

	public Database(String pathOfUsers_csv, String pathOfProducts_csv, String pathOfUsersOnTables_csv,
			String pathOfTables_xml) {
		this.pathOfUsers_csv = pathOfUsers_csv;
		this.pathOfProducts_csv = pathOfProducts_csv;
		this.pathOfTables_xml = pathOfTables_xml;
		this.pathOfUsersOnTables_csv = pathOfUsersOnTables_csv;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			this.docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
//		if(!new File(pathOfTables_xml).exists())
			this.initTables_xml();
	}

	@Override
	public ArrayList<User> getUsers() {
        BufferedReader br = null;
        ArrayList<User> list = null;
        try {
        	list = new ArrayList<User>();
            br = new BufferedReader(new FileReader(new File(pathOfUsers_csv)));
            String line = null;
            while((line = br.readLine()) != null) {              
                String[] parts = line.split(",");
                list.add(new User(parts[0], parts[1], (parts[2].equals("k")? UserType.bartender: UserType.manger)));
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
		return list;
	}
	
	@Override
	public ArrayList<Product> getProducts() {
        BufferedReader br = null;
        ArrayList<Product> list = null;
        try {
        	list = new ArrayList<Product>();
            br = new BufferedReader(new FileReader(new File(pathOfProducts_csv)));
            String line = null;
            while((line = br.readLine()) != null) {              
                String[] parts = line.split(",");
                list.add(new Product(parts[0], Float.valueOf(parts[1]), (parts[2].equals("f")? ProductType.food: ProductType.drink)));
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
		return list;
	}

	@Override
	public ArrayList<Table> getTableList(Date date) {
		return null;
	}
	
	@Override
	public void saveNewOrder(Order newOrder, int tableID) {
		try {
			Document doc = docBuilder.parse(pathOfTables_xml);
	 	 
			// Get the staff element by tag name directly
			NodeList xmlTableList = doc.getElementsByTagName("table");
			Node xmlTable = null;
			// get the table with the tableID
			for(int i = 0; i < xmlTableList.getLength(); ++i) {
				xmlTable = xmlTableList.item(i);
				NamedNodeMap attr = xmlTable.getAttributes();
				Node xmlTableId = attr.getNamedItem("id");
				if(xmlTableId.getTextContent().equals(""+tableID))
					break;
			}
			// gets the orderlist node
			NodeList childs = xmlTable.getChildNodes();
			Node xmlOrderList = null;
			for(int i = 0; i < childs.getLength(); ++i) {
				xmlOrderList = childs.item(i);
				if(xmlOrderList.getNodeName().equals("orderlist"))
					break;
			}
					
			// make an order node with the Data from newOrder
			Element xmlOrder = doc.createElement("order");
			xmlOrderList.appendChild(xmlOrder);
			xmlOrder.setAttribute("id", ""+newOrder.getId());
			xmlOrder.setAttribute("open", ""+!newOrder.isClosed());
			xmlOrder.setAttribute("date", ""+newOrder.getDate());
			
			Element xmlProductList = doc.createElement("productlist");
			xmlOrder.appendChild(xmlProductList);
			ArrayList<Product> productList = (ArrayList<Product>) newOrder.getProducts();
			for(int i = 0; i < newOrder.getProducts().size(); ++i) {
				Element xmlProduct = doc.createElement("product");
				xmlProductList.appendChild(xmlProduct);
				Product product = productList.get(i);
				xmlProduct.setAttribute("name", product.getName());
				xmlProduct.setAttribute("price", ""+product.getPrice());
				xmlProduct.setAttribute("type", product.getType() == ProductType.drink? "d": "f");
			}
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			//	add new Lines
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(pathOfTables_xml));
			transformer.transform(source, result);
	 
			System.out.println("Done");
	 
	   	} catch (TransformerException tfe) {
	   		tfe.printStackTrace();
	   	} catch (IOException ioe) {
		   	ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	@Override
	public void updateOrder(Order existingOrder, int tableID) {
		try {
			Document doc = docBuilder.parse(pathOfTables_xml);
	 	 
			// Get the staff element by tag name directly
			NodeList xmlTableList = doc.getElementsByTagName("table");
			Node xmlTable = null;
			// get the table with the tableID
			for(int i = 0; i < xmlTableList.getLength(); ++i) {
				xmlTable = xmlTableList.item(i);
				NamedNodeMap attr = xmlTable.getAttributes();
				Node xmlTableId = attr.getNamedItem("id");
				if(xmlTableId.getTextContent().equals(""+tableID))
					break;
			}
			// gets the orderlist node
			NodeList childs = xmlTable.getChildNodes();
			Node xmlOrderListNode = null;
			for(int i = 0; i < childs.getLength(); ++i) {
				xmlOrderListNode = childs.item(i);
				if(xmlOrderListNode.getNodeName().equals("orderlist"))
					break;
			}
			
			childs = xmlOrderListNode.getChildNodes();
			Node xmlExistingOrder = null;
			for(int i = 0; i < childs.getLength(); ++i) {
				xmlExistingOrder = childs.item(i);
				NamedNodeMap attr = xmlExistingOrder.getAttributes();
				if(attr != null) {
					Node orderAttr = attr.getNamedItem("id");
					if(orderAttr.getTextContent().equals(""+existingOrder.getId()))
						break;
				}
			}
			// make an order node with the Data from newOrder
			xmlExistingOrder.getAttributes().getNamedItem("open").setTextContent(""+!existingOrder.isClosed());
			xmlExistingOrder.getAttributes().getNamedItem("date").setTextContent(""+existingOrder.getDate());
			
			// gets the orderlist node
			childs = xmlExistingOrder.getChildNodes();
			Node oldProductList = null;
			for(int i = 0; i < childs.getLength(); ++i) {
				oldProductList = childs.item(i);
				if(oldProductList.getNodeName().equals("productlist"))
					break;
			}
			
			Element newProductList = doc.createElement("productlist");
			ArrayList<Product> productList = (ArrayList<Product>) existingOrder.getProducts();
			for(int i = 0; i < existingOrder.getProducts().size(); ++i) {
				Element xmlProduct = doc.createElement("product");
				newProductList.appendChild(xmlProduct);
				Product product = productList.get(i);
				xmlProduct.setAttribute("name", product.getName());
				xmlProduct.setAttribute("price", ""+product.getPrice());
				xmlProduct.setAttribute("type", product.getType() == ProductType.drink? "d": "f");
			}
			
			// replace the old list
			xmlExistingOrder.replaceChild(newProductList, oldProductList);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			//	add new Lines
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(pathOfTables_xml));
			transformer.transform(source, result);
	 
			System.out.println("Done");
	 
	   	} catch (TransformerException tfe) {
	   		tfe.printStackTrace();
	   	} catch (IOException ioe) {
		   	ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	@Override
	public ArrayList<Order> getTotalPayroll(Date start, Date end) {
		return null;
	}

	@Override
	public void saveTotalPayroll(ArrayList<Order> orders, String pathOftotalPayroll_csv) {
		
	}
	
	private void initTables_xml() {
		try {		
 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("tablelist");
			doc.appendChild(rootElement);
			
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(new File(pathOfUsersOnTables_csv)));
				String line = null;
				while((line = br.readLine()) != null) {              
					String[] parts = line.split(",");
					// table elements
					Element table = doc.createElement("table");
					rootElement.appendChild(table);
					table.setAttribute("id", parts[0]);
					table.appendChild(doc.createElement("orderlist"));
					
				}
				br.close();
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}						 
 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			//	add new Lines
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StreamResult result = new StreamResult(new File(pathOfTables_xml));
			
			transformer.transform(source, result);
			
			// System.out.println("File saved!");
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}	
	