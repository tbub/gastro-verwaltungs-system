package gui.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dto.ProductDTO;
import dto.ProductTypeDTO;
import model.IDataUC;
import model.IOrderUC;
import model.gvsBase.ProductType;

public class AddProductController implements IAddProductController
{
	private IOrderUC orderUC;
	private IDataUC dataUC;
	private int orderID;
	
	public AddProductController(IOrderUC orderUC, IDataUC dataUC, int orderID)
	{
		this.orderUC = orderUC;
		this.orderID = orderID;
	}

	@Override
	public void addProduct(String name)
	{
		orderUC.addProduct(orderID, name);
	}

	@Override
	public Collection<ProductDTO> getProducts(ProductTypeDTO type)
	{
		Collection<ProductDTO> list = dataUC.getProducts();
		Collection<ProductDTO> result = new ArrayList<ProductDTO>();
		
		for(ProductDTO product : list)
		{
			if(product.getType().getName().equals(type.getName()))
			{
				result.add(product);
			}
		}
		return result;
	}
	
	@Override
	public Collection<ProductTypeDTO> getProductTypes()
	{
		return dataUC.getProductTypes();
	}

	@Override
	public Collection<ProductDTO> getProducts()
	{
		return dataUC.getProducts();
	}
}
