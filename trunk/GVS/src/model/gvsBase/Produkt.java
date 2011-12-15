package model.gvsBase;

/**
 * Basiselement eines Produktes
 * 
 * @author ifw10142
 *
 */

public class Produkt {

	private String name;
	private boolean FoodFlag;	// true wenn Speise, False wenn Getränk
		
	public Produkt(String name,boolean Flag) {
		this.name = name;
		this.FoodFlag = Flag;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isFoodFlag() {
		return FoodFlag;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setFoodFlag(boolean foodFlag) {
		FoodFlag = foodFlag;
	}

	
	/**
	 * equals 
	 * 
	 * @param p
	 * @return 
	 */
	public boolean equals(Produkt p) {
		if( p != null ) {
			if(this.getName() == p.getName()) {
				return true;
			}
			else { return false; }
		}
		else { return false; }
	}
	
	
	/**
	 * toString
	 */
	public String toString() {
		return name;
	}
}
