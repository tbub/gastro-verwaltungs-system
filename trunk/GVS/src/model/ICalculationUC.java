package model;

/**
 * 
 * @author Benedikt Zönnchen
 * Interface welches von der View angesprochen wird
 * 
 * Anwendungsfälle: 
 * - Gesamtabbrechnung erstellen
 *
 */
public interface ICalculationUC
{
	public void calculate(String startDate, String endDate);
}
