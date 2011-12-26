package model.gvsUC;

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
	public boolean validateDate(String date);
	public boolean calculate(String startDate, String endDate);
}
