package model.gvsBase;

/**
 * Basiselement eines Produktes
 * 
 * @author Sebastian
 *
 */

public class Product implements Comparable<Product>
{
	private String name;
	private float price;
	private ProductType type;

	public Product(String Name, float price, ProductType type) {
		this.price = price;
		this.name = Name;
		this.type = type;
	}
	
	public Product(Product product)
	{
		this.price = product.getPrice();
		this.name = product.getName();
		this.type = product.getType();
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
	 * toString
	 */
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Product o)
	{
		return this.name.compareTo(o.getName());
	}
}
