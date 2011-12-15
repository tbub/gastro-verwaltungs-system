package model.gvsBase;

/**
 * Basiselement eines Produktes
 * 
 * @author Sebastian
 *
 */

public class Product {

	private String Name;
	private float Price;
	private boolean FoodFlag;	// true wenn Speise, False wenn Getränk
		
	public Product(String Name,boolean Flag) {
		this.Name = Name;
		this.FoodFlag = Flag;
	}
	
	public String getName() {
		return Name;
	}
	
	public float getPrice() {
		return Price;
	}
	
	public boolean isFoodFlag() {
		return FoodFlag;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
	public void setPrice(float price) {
		Price = price;
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
	public boolean equals(Product p) {
		if( p != null ) {
			if(this.getName() == p.getName()) {
				return true;
			}
			else { return false; }
		}
		else { return false; }
	}
	
	
	/**
	 * clone
	 * 
	 * @return p
	 */
	public Product clone() {
		return null;
		
	}
	
	
	/**
	 * toString
	 */
	public String toString() {
		return Name;
	}
}
