package model;


public interface IGvsController extends ICalculationUC, IOrderUC, IUserUC, ITableUC
{
	public IDataManagement getDataManagement();
}
