package model;

import model.gvsBase.Bestellung;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public interface IOrderUC
{
	public void addProduct(int orderID, int productID);
	public Bestellung getOrder(int orderID);
	public void closeOrder(int orderID);
}
