package model;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import database.IRessourceManager;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.ProductTypeDTO;
import dto.TableDTO;
import dto.UserDTO;
import model.gvsBase.Product;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public class DataManagement implements IDataManagement
{
	private IRessourceManager ressourceManager;
	
	
	public DataManagement(IRessourceManager ressourceManager)
	{
		this.ressourceManager = ressourceManager;
	}
	
	@Override
	public Collection<ProductDTO> getProducts() throws IOException
	{
		Collection<Product> col = ressourceManager.getProducts();
		Collection<ProductDTO> result = null;
		for(Product p : col)
		{
			result.add(new ProductDTO(p));
		}
		return result;
	}

	@Override
	public Collection<ProductTypeDTO> getProductTypes() throws IOException
	{
		return dataManager.getProductTypes();
	}

	@Override
	public OrderDTO getOrder(long orderID) throws IOException
	{
		return dataManager.getOrder(orderID);
	}

	@Override
	public TableDTO getTable(int tableID) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TableDTO> getTables() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserDTO> getUsers() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getCurrentUser() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
