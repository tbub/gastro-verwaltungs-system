package model.gvsUC;

import model.gvsDataManagement.IDataManagement;


public interface IGvsController extends ICalculationUC, IOrderUC, IUserUC, ITableUC
{
	public IDataManagement getDataManagement();
}
