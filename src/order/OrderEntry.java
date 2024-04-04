package src.order;

import src.menu.Food;

import java.beans.JavaBean;

@JavaBean
public class OrderEntry{ // Class used to represent a line of order
    private Food food;
    private int quantity;

    private boolean hasCustomization;
    private String customization;

    public OrderEntry(Food food, int quantity,boolean hasCustomization, String customization) {
        this.food = food;
        this.quantity = quantity;
        this.customization = customization;
        this.hasCustomization = hasCustomization;
    }
    public OrderEntry(Food food) {
        this.food = food;
        this.quantity = 1;
        this.customization = "";
        this.hasCustomization = false;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
        this.hasCustomization = true;
    }

    public boolean isHasCustomization() {
        return hasCustomization;
    }

    public void setHasCustomization(boolean hasCustomization) {
        this.hasCustomization = hasCustomization;
    }
}
