package model.gvsBase;

/**
 * Basiselement eines Produktes
 * 
 * @author Sebastian
 *
 */

public class Product {

	private String name;
	private float price;
	private ProductType type;

	public Product(String Name, float price, ProductType type) {
		this.price = price;
		this.name = Name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public boolean isFood() {
		return type.equals(ProductType.food);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setType(ProductType type) {
		this.type = type;
	}

	public ProductType getType()
	{
		return type;
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
		return name;
	}
}
