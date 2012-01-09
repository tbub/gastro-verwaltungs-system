package model.gvsUC;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.naming.ldap.SortControl;

import model.gvsBase.Order;
import model.gvsBase.Product;
import model.gvsBase.Table;
import model.gvsBase.TableList;
import model.gvsBase.User;
import model.gvsDataManagement.DataManagement;
import model.gvsDataManagement.IDataManagement;

import database.IOrderPersistenz;
import database.IRessourceManager;
import database.OrderPersistenz;
import database.RessourceManager;
import dto.UserDTO;

public class GvsUsecaseController implements IGvsController
{
	private IDataManagement dataManagement;
	private User currentUser;
	private final TableList tableList;
	private Collection<Product> products = new HashSet<Product>();
	private IOrderPersistenz persistence;
	private IRessourceManager ressourceManager;
	private static IGvsController instance;
	private Collection<User> users = new HashSet<User>();
	private final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
	
	public static IGvsController getInstance()
	{
		if(instance == null)
		{
			try
			{
				instance = new GvsUsecaseController();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private GvsUsecaseController() throws IOException
	{
		tableList = new TableList();
		try
		{
			this.ressourceManager = RessourceManager.getInstance();
			users.addAll(ressourceManager.getUsers());
			tableList.addTables(ressourceManager.getTables());
			products.addAll(ressourceManager.getProducts());
			this.dataManagement = new DataManagement(tableList);
			this.persistence = new OrderPersistenz(ressourceManager.getProperty("orders.filename"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addProduct(int tableId, long orderId, String productname)
	{
		Table table = tableList.getTable(tableId);
		Order order;
		if(!table.containsOrder(orderId))
		{
			order = new Order(orderId);
		}
		else
		{
			order = table.getOrder(orderId);
		}
		
		for(Product product : products)
		{
			if(product.getName().equals(productname))
			{
				order.addProdukt(new Product(product));
				System.out.println("add Product " + product.toString() + " to " + order.toString());
				break;
			}
		}
		table.addOrder(order);
		try
		{
			ressourceManager.saveLastOrderId(orderId);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void closeOrder(long orderId)
	{
		Order order = getOrderById(orderId);
		order.close();
		
		try
		{
			persistence.startSession();
			persistence.saveOrder(order);
			ressourceManager.saveLastOrderId(order.getId());
			persistence.endSession();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private Order getOrderById(long orderId)
	{
		Table table = tableList.getTableByOrderId(orderId);
		return table.getOrder(orderId);
	}

	@Override
	public void changeOrder(long orderId, int tableId)
	{
		Table sourceTable = tableList.getTableByOrderId(orderId);
		Table targetTable = tableList.getTable(tableId);
		targetTable.addOrder(sourceTable.removeOrder(orderId));		
	}

	@Override
	public boolean login(String username, String password) throws IOException
	{
		Collection<User> col = ressourceManager.getUsers();
		for(User user : col)
		{
			if(user.getName().equals(username) && user.getPassword().equals(password))
			{
				currentUser = user;			
				return true;
			}
		}
		return false;
	}

	@Override
	public void logout()
	{
		//TODO
	}


	@Override
	public boolean calculate(String startDate, String endDate)
	{
		String filename = startDate + "_until_" + endDate + ".xml";
		try
		{
			// hol die bestellungen
			persistence.startSession();
			Set<Order> orders = persistence.getOrders(dateFormat.parse(startDate), dateFormat.parse(endDate));
			persistence.endSession();
			
			// und speicher sie dann
			IOrderPersistenz tmpPersistence = new OrderPersistenz(filename);
			tmpPersistence.startSession();
			tmpPersistence.saveOrders(orders);
			tmpPersistence.endSession();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// im fehler fall lösche die erstellte datei
			File file = new File(filename);
			if(file.exists() && file.isFile())
			{
				file.delete();
			}
			return false;
		}
		return true;
	}

	@Override
	public void changeUser(int tableID, String username)
	{
		Table table = tableList.getTable(tableID);
		for(User user : users)
		{
			if(user.getName().equals(username))
			{
				table.setUser(user);
				break;
			}
		}
	}

	@Override
	public IDataManagement getDataManagement()
	{
		return dataManagement;
	}

	@Override
	public UserDTO getCurrentUser() throws IOException
	{
		return new UserDTO(currentUser);
	}
	
	@Override
	public int getTableId(long orderId)
	{
		Table table = tableList.getTableByOrderId(orderId);
		return table==null?-1:table.getId();
	}

	@Override
	public boolean validateDate(String date)
	{
		try
		{
			dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			return false;
		}
		return true;
	}
	
}
