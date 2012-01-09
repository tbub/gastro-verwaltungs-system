package gui.order;

import gui.IDialog;
import java.util.Collection;
import dto.ProductDTO;
import dto.ProductTypeDTO;

public interface IAddProductController
{
	public void addProduct(String name);
	public Collection<ProductDTO> getProducts();
	public Collection<ProductTypeDTO> getProductTypes();
	public ProductTypeDTO getSelectedProductType();
	public void setSelectedProductType(ProductTypeDTO productType);
	public void setDialog(IDialog dialog);
	public void closeDialog();
	public String getPrice(String productName);
}
