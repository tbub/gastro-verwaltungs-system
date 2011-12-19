package gui;

import java.util.Collection;
import java.util.Set;

import model.IDataUC;
import model.IOrderUC;
import dto.OrderDTO;
import dto.ProductDTO;


public class OrderController implements IOrderController
{
	private IOrderUC orderUC;
	private IDataUC dataUC;
	private final long orderID;
	
	public OrderController(IDataUC dataUC, IOrderUC orderUC, long orderID)
	{
		this.dataUC = dataUC;
		this.orderID = orderID;
	}
	
	public Set<ProductDTO> getProducts()
	{
		return dataUC.getOrder(orderID).getProducts();
	}
	
	@Override
	public long getOrderId()
	{
		return orderID;
	}
}
