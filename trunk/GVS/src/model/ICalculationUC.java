package model;

import java.util.List;

import model.gvsBase.Bestellung;

/**
 * 
 * @author Benedikt Zönnchen
 *
 */
public interface ICalculationUC
{
	public List<Bestellung> calculate(String startDate, String endDate);
}
