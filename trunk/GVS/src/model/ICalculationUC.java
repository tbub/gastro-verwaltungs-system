package model;

import java.util.List;

import model.gvsBase.Order;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public interface ICalculationUC
{
	public List<Order> calculate(String startDate, String endDate);
}
