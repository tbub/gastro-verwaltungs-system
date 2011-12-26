package gui.orderList;

import gui.IDialog;

public interface ICalculationController
{
	public boolean calculate(String startDate, String endDate);
	public void setDialog(IDialog dialog);
}
