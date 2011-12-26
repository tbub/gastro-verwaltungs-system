package gui.order;

import gui.IDialog;
import gui.orderList.IOrderListController;

import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.GvsUsecaseController;
import model.IDataManagement;
import model.IGvsController;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.TableDTO;


public class OrderController implements IOrderController
{
	private IGvsController useCaseController;
	private IOrderListController orderListController;
	private IDataManagement dataUC;
	private final long orderID;
	private int initialTableId;
	private IDialog dialog;
	
	public OrderController(long orderID, int tableId, IOrderListController orderListController)
	{
		this.useCaseController = GvsUsecaseController.getInstance();
		this.orderID = orderID;
		this.dataUC = useCaseController.getDataManagement();
		this.orderListController = orderListController;
		this.initialTableId = tableId;
	}
	
	public Collection<ProductDTO> getProducts()
	{
		Collection<ProductDTO> products = new ArrayList<ProductDTO>();
		try
		{
			OrderDTO order = dataUC.getOrder(orderID);
			if(order != null)
			{
				products.addAll(order.getProducts());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return products;
	}
	
	@Override
	public long getOrderId()
	{
		return orderID;
	}

	@Override
	public void closeDialog()
	{
		orderListController.enableOrderList();
		((Window)dialog).dispose();
	}

	@Override
	public int getTableId()
	{
		int id = useCaseController.getTableId(orderID);
		return id==-1?initialTableId:id;
	}

	@Override
	public void openAddProductDialog()
	{
		IAddProductController controller = new AddProductController(useCaseController, this, orderID);
		IDialog addProductDialog = new AddProductDialog(controller, (Window)this.dialog);
		controller.setDialog(addProductDialog);
		addProductDialog.updateModel();		
		dialog.enable(false);
	}

	@Override
	public void enableDialog()
	{
		dialog.enable(true);
		dialog.updateModel();
	}

	@Override
	public void setDialog(IDialog dialog)
	{
		this.dialog = dialog;		
	}

	@Override
	public void setTable(int tableId)
	{
		useCaseController.changeOrder(getTableId(), tableId);
		dialog.updateModel();
	}

	@Override
	public String[] getTableIds()
	{
		Collection<TableDTO> col;
		String[] result = null;
		try
		{
			col = dataUC.getTables();
			result = new String[col.size()];
			int index = 0;
			for(TableDTO table : col)
			{
				result[index++] = table.getId()+"";
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
