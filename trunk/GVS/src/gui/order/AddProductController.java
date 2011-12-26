package gui.order;

import gui.IDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import dto.ProductDTO;
import dto.ProductTypeDTO;
import model.gvsDataManagement.IDataManagement;
import model.gvsUC.IGvsController;
import model.gvsUC.IOrderUC;

public class AddProductController implements IAddProductController
{
	private IOrderUC useCaseController;
	private IDataManagement dataUC;
	private long orderID;
	private ProductTypeDTO selectedProductType;
	private IDialog dialog;
	private IOrderController orderController;
	
	public AddProductController(IGvsController useCaseController, IOrderController orderController, long orderID)
	{
		this.useCaseController = useCaseController;
		this.orderID = orderID;
		this.dataUC = useCaseController.getDataManagement();
		this.orderController = orderController;
	}

	@Override
	public void addProduct(String name)
	{
		useCaseController.addProduct(orderController.getTableId(), orderID, name);
	}

	@Override
	public Collection<ProductDTO> getProducts()
	{
		Collection<ProductDTO> list;
		Collection<ProductDTO> result = new ArrayList<ProductDTO>();
		try
		{
			list = dataUC.getProducts();
			for(ProductDTO product : list)
			{
				if(selectedProductType == null ||
						product.getType().getName().equals(selectedProductType.getName()))
				{
					result.add(product);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
		return result;
	}
	
	@Override
	public Collection<ProductTypeDTO> getProductTypes()
	{
		try
		{
			return dataUC.getProductTypes();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductTypeDTO getSelectedProductType()
	{
		return selectedProductType;
	}

	@Override
	public void setSelectedProductType(ProductTypeDTO productType)
	{
		selectedProductType = productType;
		dialog.updateModel();
	}

	@Override
	public void setDialog(IDialog dialog)
	{
		this.dialog = dialog;
	}

	@Override
	public void closeDialog()
	{
		orderController.enableDialog();
	}
}
