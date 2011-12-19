package gui.order;

import java.util.Collection;
import dto.ProductDTO;
import dto.ProductTypeDTO;

public interface IAddProductController
{
	public void addProduct(String name);
	public Collection<ProductDTO> getProducts(ProductTypeDTO type);
	public Collection<ProductDTO> getProducts();
	public Collection<ProductTypeDTO> getProductTypes();
}
