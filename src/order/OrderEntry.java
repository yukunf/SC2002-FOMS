package src.order;

import src.menu.Food;

import java.beans.JavaBean;
import java.io.Serializable;

/**
 * Class of OrderEntry. It's a layer of Food object which stores information needed for a order but not in Food.
 * Like the quantity and Customization string.
 */
@JavaBean
public class OrderEntry implements Serializable { // Class used to represent a line of order
    private Food food;
    private int quantity;

    private boolean hasCustomization;
    private String customization;

    /**
     * Instantiates a new Order entry.
     *
     * @param food             the food
     * @param quantity         the quantity
     * @param hasCustomization the has customization
     * @param customization    the customization
     */
    public OrderEntry(Food food, int quantity,boolean hasCustomization, String customization) {
        this.food = food;
        this.quantity = quantity;
        this.customization = customization;
        this.hasCustomization = hasCustomization;
    }

    /**
     * Instantiates a new Order entry.
     *
     * @param food the food
     */
    public OrderEntry(Food food) {
        this.food = food;
        this.quantity = 1;
        this.customization = "";
        this.hasCustomization = false;
    }

    /**
     * Gets food.
     *
     * @return the food
     */
    public Food getFood() {
        return food;
    }

    /**
     * Sets food.
     *
     * @param food the food
     */
    public void setFood(Food food) {
        this.food = food;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets customization string
     *
     * @return the customization string
     */
    public String getCustomization() {
        return customization;
    }

    /**
     * Sets customization string
     *
     * @param customization the customization
     */
    public void setCustomization(String customization) {
        this.customization = customization;
        this.hasCustomization = true;
    }

    /**
     * Is this food has customizations.
     *
     * @return the boolean
     */
    public boolean isHasCustomization() {
        return hasCustomization;
    }

    /**
     * Sets customization status for the food.
     *
     * @param hasCustomization the has customization
     */
    public void setHasCustomization(boolean hasCustomization) {
        this.hasCustomization = hasCustomization;
    }
}
