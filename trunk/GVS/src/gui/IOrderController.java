package gui;

import java.util.Set;

import dto.ProductDTO;

public interface IOrderController
{
	public long getOrderId();
	public Set<ProductDTO> getProducts();
}
