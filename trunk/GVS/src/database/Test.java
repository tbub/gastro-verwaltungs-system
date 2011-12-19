package database;

import model.gvsBase.Order;
import model.gvsBase.Product;
import model.gvsBase.ProductType;

public class Test {

	public static void main(String[] args) {
		IDatabase db = new Database("users.csv", "products.csv", "tables.csv", "tables.xml");
		Order order = new Order();
		order.addProdukt(new Product("Schnitzel", (float)11.5, ProductType.food));
		order.addProdukt(new Product("Hamburger", (float)11.5, ProductType.food));
		order.addProdukt(new Product("Steak", (float)11.5, ProductType.food));
		order.addProdukt(new Product("Cola", (float)11.5, ProductType.drink));
		order.addProdukt(new Product("Wasser", (float)11.5, ProductType.drink));
		order.addProdukt(new Product("Kaffee", (float)11.5, ProductType.drink));
		db.saveNewOrder(order, 1);
		db.saveNewOrder(order, 2);
		db.saveNewOrder(order, 3);
		db.saveNewOrder(order, 4);
		order = new Order();
		order.addProdukt(new Product("Schnitzel", (float)11.5, ProductType.food));
		order.addProdukt(new Product("Hamburger", (float)11.5, ProductType.food));
		order.addProdukt(new Product("Steak", (float)11.5, ProductType.food));
		order.addProdukt(new Product("Cola", (float)11.5, ProductType.drink));
		order.addProdukt(new Product("Wasser", (float)11.5, ProductType.drink));
		order.addProdukt(new Product("Kaffee", (float)11.5, ProductType.drink));
		db.saveNewOrder(order, 5);
		db.saveNewOrder(order, 6);
		db.saveNewOrder(order, 7);
		db.saveNewOrder(order, 7);
		order = new Order();
		order.addProdukt(new Product("nur noch eines", (float)11.5, ProductType.food));
		db.updateOrder(order, 5);
		db.updateOrder(order, 6);
		db.updateOrder(order, 7);
		db.updateOrder(order, 7);
	}

}
