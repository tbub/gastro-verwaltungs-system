package database;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import model.gvsBase.Product;
import model.gvsBase.ProductType;
import model.gvsBase.Table;
import model.gvsBase.User;
import model.gvsBase.UserType;

public class RessourceManager implements IRessourceManager
{
	private String userFilename, productFilename, tableFilename;
	private Properties config;
	
	public RessourceManager(String userFilename, String productFilename, String tableFilename) throws IOException
	{
		this.userFilename = userFilename;
		this.productFilename = productFilename;
		this.tableFilename = tableFilename;
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream("properties/config.properties"));
		config.load(stream);
		stream.close();
	}
	
	@Override
	public Collection<User> getUsers() throws IOException
	{
		BufferedReader br = null;
        Collection<User> list = null;
    	list = new ArrayList<User>();
        br = new BufferedReader(new FileReader(userFilename));
        String line = null;
        while((line = br.readLine()) != null) 
        {              
            String[] parts = line.split(",");
            list.add(new User(parts[0], parts[1], (parts[2].equals("k")? UserType.bartender: UserType.manger)));
        }
        br.close();
     
		return list;
	}

	@Override
	public Collection<Product> getProducts() throws IOException
	{
		BufferedReader br = null;
        Collection<Product> list = null;
    	list = new ArrayList<Product>();
        br = new BufferedReader(new FileReader(productFilename));
        String line = null;
        while((line = br.readLine()) != null) 
        {              
            String[] parts = line.split(",");
            list.add(new Product(parts[1], Float.valueOf(parts[2]), (parts[0].equals("m")? ProductType.food: ProductType.drink)));
        }
        br.close();
     
		return list;
	}

	@Override
	public long getLastOrderId()
	{
		return Long.valueOf(config.getProperty("last.order.id"));
	}

	@Override
	public Collection<Table> getTables() throws IOException
	{
		BufferedReader br = null;
        Collection<Table> list = null;
        Collection<User> users = getUsers();
    	list = new ArrayList<Table>();
        br = new BufferedReader(new FileReader(tableFilename));
        String line = null;
        
        while((line = br.readLine()) != null) 
        {              
            String[] parts = line.split(",");
            User tmp;
            for (User user : users)
			{
				if(user.getName().equals(parts[1]))
				{
					tmp = user;
					list.add(new Table(Integer.valueOf(parts[0]), tmp));
					break;
				}
			}
        }
        br.close();
     
		return list;
	}

}
