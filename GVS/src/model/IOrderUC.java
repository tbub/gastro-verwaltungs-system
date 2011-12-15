package model;

import model.gvsBase.Order;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public interface IOrderUC
{
	public void addProduct(int orderID, int productID);
	public Order getOrder(int orderID);
	public void closeOrder(int orderID);
	public boolean isOrderClosed(int orderID);
}
