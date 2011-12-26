package gui.order;

import gui.IDialog;

import java.util.Collection;
import java.util.Set;

import dto.ProductDTO;

public interface IOrderController
{
	public long getOrderId();
	public Collection<ProductDTO> getProducts();
	public void closeDialog();
	public int getTableId();
	public void openAddProductDialog();
	public void enableDialog();
	public void setDialog(IDialog dialog);
	public void setTable(int tableId);
	public String[] getTableIds();
}
