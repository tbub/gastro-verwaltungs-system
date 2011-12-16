package model;

import dto.OrderDTO;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public interface IOrderUC
{
	public void addProduct(int orderID, int productID);
	public OrderDTO getOrder(int orderID);
	public void closeOrder(int orderID);
	public boolean isOrderClosed(int orderID);
}
