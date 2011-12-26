package model;

import java.io.IOException;
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
public interface IDataManagement
{
	public Collection<ProductDTO> getProducts() throws IOException;
	public Collection<ProductTypeDTO> getProductTypes() throws IOException;
	public OrderDTO getOrder(long orderID) throws IOException;
	public TableDTO getTable(int tableID) throws IOException;
	public Collection<TableDTO> getTables() throws IOException;
	public Collection<UserDTO> getUsers() throws IOException;
	public long getLastOrderId() throws IOException;
}
