package model;

import java.util.Collection;

import dto.OrderDTO;
import dto.ProductDTO;
import dto.ProductTypeDTO;
import dto.TableDTO;
import dto.UserDTO;

/**
 * 
 * @author Benedikt Zönnchen
 * Interface welches von der View angesprochen wird. Dient dem Datenzugriff der
 * Präsentationsschicht über die Anwendungsschicht hin zur Datenhaltungsschicht. 
 * Es ermöglicht den Zugriff auf die im System gehaltenen Objecte. Dabei werden
 * nur DataTransferObjects (DTO's) zurück gegeben, die nicht veränderbar sind.
 * 
 */
public interface IDataUC
{
	public Collection<ProductDTO> getProducts();
	public Collection<ProductTypeDTO> getProductTypes();
	public OrderDTO getOrder(int orderID);
	public TableDTO getTable(int tableID);
	public Collection<TableDTO> getTables();
	public Collection<UserDTO> getUsers();
	public UserDTO getCurrentUser();
}
