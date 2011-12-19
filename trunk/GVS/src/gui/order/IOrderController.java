package gui.order;

import java.util.Set;

import dto.ProductDTO;

public interface IOrderController
{
	public long getOrderId();
	public Set<ProductDTO> getProducts();
}
