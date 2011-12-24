package gui.order;

import java.util.Set;
import model.IDataManagement;
import model.IOrderUC;
import dto.ProductDTO;


public class OrderController implements IOrderController
{
	private IOrderUC orderUC;
	private IDataManagement dataUC;
	private final long orderID;
	
	public OrderController(IDataManagement dataUC, IOrderUC orderUC, long orderID)
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
