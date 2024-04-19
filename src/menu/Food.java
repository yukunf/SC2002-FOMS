package src.menu;

import java.io.Serializable;

/**
 * The Food Type. Represents a menu entry.
 *
 * @author fengyukun  Created at 21/3/24 17:28 Email : @author fengyukufyk@sina.com Package : src
 * @version 1.00.00
 */
public class Food implements Serializable {

    private int ID; //Need ID?
    private String name;
    private FoodCategory category;
    private String branch;
    private double price;

    private boolean availability;

    /**
     * Instantiates a new Food.
     *
     * @param name         the name
     * @param price        the price
     * @param branch       the branch
     * @param category     the category
     * @param availability the availability
     */
    public Food(String name, double price, String branch, FoodCategory category, boolean availability) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.availability = availability;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets branch it belongs to
     *
     * @return the branch
     */
    public String getBranch() {
    	return branch;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
    	return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
    	this.price = price;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public FoodCategory getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    /**
     * Get the boolean indicates is it available
     * is
     *
     * @return the boolean
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * Sets availability.
     *
     * @param availability the availability
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
