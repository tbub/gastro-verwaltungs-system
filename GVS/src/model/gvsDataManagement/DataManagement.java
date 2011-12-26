package model.gvsDataManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import database.IOrderPersistenz;
import database.IRessourceManager;
import database.OrderPersistenz;
import database.RessourceManager;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.ProductTypeDTO;
import dto.TableDTO;
import dto.UserDTO;
import model.gvsBase.Product;
import model.gvsBase.ProductType;
import model.gvsBase.Table;
import model.gvsBase.TableList;
import model.gvsBase.User;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public class DataManagement implements IDataManagement
{
	private final IRessourceManager ressourceManager;
	private final IOrderPersistenz orderPersistence;
	private Collection<ProductDTO> products = new ArrayList<ProductDTO>();
	private Collection<ProductTypeDTO> productTypes = new ArrayList<ProductTypeDTO>();
	private Collection<OrderDTO> orders = new ArrayList<OrderDTO>();
	private final TableList tableList;
	
	public DataManagement(TableList tableList) throws IOException
	{
		this.ressourceManager = RessourceManager.getInstance();
		this.orderPersistence = new OrderPersistenz(ressourceManager.getProperty("orders.filename"));
		this.tableList = tableList;
	}
	
	@Override
	public Collection<ProductDTO> getProducts() throws IOException
	{
		if(products.isEmpty())
		{
			Collection<Product> col = ressourceManager.getProducts();
			for(Product p : col)
			{
				products.add(new ProductDTO(p));
			}
		}
		return products;	
	}

	@Override
	public Collection<ProductTypeDTO> getProductTypes() throws IOException
	{
		if(productTypes.isEmpty())
		{
			productTypes.add(new ProductTypeDTO(ProductType.drink));
			productTypes.add(new ProductTypeDTO(ProductType.food));
		}
		return productTypes;
	}

	@Override
	public OrderDTO getOrder(long orderId) throws IOException
	{
		Table table = tableList.getTableByOrderId(orderId);
		if(table != null)
		{
			return new OrderDTO(table.getOrder(orderId));
		}
		return null;
	}

	@Override
	public TableDTO getTable(int tableID) throws IOException
	{
		return new TableDTO(tableList.getTable(tableID));
	}

	@Override
	public Collection<TableDTO> getTables() throws IOException
	{
		Collection<Table> col = ressourceManager.getTables();
		Collection<TableDTO> result = new ArrayList<TableDTO>();
		
		for(Table table : col)
		{
			result.add(new TableDTO(table));
		}
		return result;
	}

	@Override
	public Collection<UserDTO> getUsers() throws IOException
	{
		Collection<User> col = ressourceManager.getUsers();
		Collection<UserDTO> result = new ArrayList<UserDTO>();
		
		for(User user : col)
		{
			result.add(new UserDTO(user));
		}
		return result;
	}

	@Override
	public long getLastOrderId() throws IOException
	{
		return ressourceManager.getLastOrderId();
	}

}
