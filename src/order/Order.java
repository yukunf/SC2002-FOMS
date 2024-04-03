package src.order;

import src.menu.Food;

import java.beans.JavaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyukun
 * Created at 21/3/24 17:50
 * Email : @author fengyukufyk@sina.com
 * Package : src.order
 * @version 1.00.00
 **/
public class Order implements Serializable {
    private static int orderCount = 0;
    private int orderID;
    private String branch;
    private boolean diningStatus; //True == dining in and false == take away
    private int time; // Time when order is placed ( #TODO or when order is ready???)
                    // in unix timestamp
    private OrderStatus status;
    private List<OrderEntry> foodList;
    //TODO Maybe need a subclass to store customize options and quantity;

    public Order() {
    	orderCount++; //everytime a new order is instantiated can increment no need to manually set it.
        orderID = orderCount;
        time = 0;
        status = OrderStatus.UNPAID;
        diningStatus = false;
        foodList = new ArrayList<>();
    }

    public Order(String branch, int time, OrderStatus status, boolean diningStatus, List<OrderEntry> orderItem) {
    	orderCount++; //everytime a new order is instantiated can increment no need to manually set it.
    	orderID = orderCount;
        this.branch = branch;
        this.time = time;
        this.status = status;
        this.diningStatus = diningStatus;
        this.foodList = orderItem;
    }

    public int getOrderID() {
        return orderID;
    }

    public double getTotalCost() {
    	double cost = 0;
    	for(OrderEntry oe : foodList) {
    		cost += oe.getQuantity() * oe.getFood().getPrice();
    	}
    	return cost;
    }
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public boolean getDiningStatus() {
    	return diningStatus;
    }
    
    public void setDiningStatus(boolean diningStatus) {
    	this.diningStatus = diningStatus;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static void setOrderIDCounter(int c){
        Order.orderCount = c;
    }

    public List<OrderEntry> getFoodList() {
        return foodList;
    }

    public void addOrderItem(OrderEntry orderItem) {
        this.foodList.add(orderItem);
    }
    public void removeOrderItem(int index) {
    	this.foodList.remove(index);
    }

    public void clearOrderItem(){
        foodList = new ArrayList<>();}

    public String getCustomization (int i) {
		return foodList.get(i).getCustomization();
	}
    public Food getFood (int i) {
		return foodList.get(i).getFood();
	}
    public boolean getHasCustomization (int i) {
		return foodList.get(i).isHasCustomization();
	}
    public int getQuantity (int i) {
		return foodList.get(i).getQuantity();
	}
}

@JavaBean
class OrderEntry{ // Class used to represent a line of order
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
