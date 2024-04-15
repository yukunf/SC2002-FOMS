package src.menu;

import java.io.Serializable;

/**
 * @author fengyukun
 * Created at 21/3/24 17:28
 * Email : @author fengyukufyk@sina.com
 * Package : src
 * @version 1.00.00
 **/
public class Food implements Serializable {

    private int ID; //Need ID?
    private String name;
    private FoodCategory category;
    private String branch;
    private double price;

    private boolean availability;

    public Food(String name, double price, String branch, FoodCategory category, boolean availability) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.availability = availability;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getBranch() {
    	return branch;
    }
    
    public double getPrice() {
    	return price;
    }
    
    public void setPrice(double price) {
    	this.price = price;
    }
    
    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
