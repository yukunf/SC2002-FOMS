package src.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This order class describe every order customers made during ordering process.
 * Which includes items and other essential status.
 *
 * @author fengyukun, Victor  Created at 21/3/24 17:50 Email : @author fengyukufyk@sina.com Package : src.order
 * @version 1.00.00
 */
public class Order implements Serializable {
    private static int orderCount = 0;
    private int orderID;
    private String branch;
    private boolean diningStatus; //True == dining in and false == take away
    private long time; // Time when order is placed
                    // in unix timestamp
    private OrderStatus status;
    private List<OrderEntry> foodList;

    /**
     * Instantiates a new Order.
     */
    public Order() {
    	orderCount++; //everytime a new order is instantiated can increment no need to manually set it.
        orderID = orderCount;
        time = -1;
        status = OrderStatus.UNPAID;
        diningStatus = false;
        foodList = new ArrayList<>();
    }

    /**
     * Instantiates a new Order.
     *
     * @param branch       the branch
     * @param time         the time
     * @param status       the status
     * @param diningStatus the dining status
     * @param orderItem    the order item
     */
    public Order(String branch, int time, OrderStatus status, boolean diningStatus, List<OrderEntry> orderItem) {
    	orderCount++; //everytime a new order is instantiated can increment no need to manually set it.
    	orderID = orderCount;
        this.branch = branch;
        this.time = time;
        this.status = status;
        this.diningStatus = diningStatus;
        this.foodList = orderItem;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Gets total cost.
     *
     * @return the total cost
     */
    public double getTotalCost() {
    	double cost = 0;
    	for(OrderEntry oe : foodList) {
    		cost += oe.getQuantity() * oe.getFood().getPrice();
    	}
    	return cost;
    }

    /**
     * Gets branch.
     *
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets branch.
     *
     * @param branch the branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Gets dining status.
     *
     * @return the dining status
     */
    public boolean getDiningStatus() {
    	return diningStatus;
    }

    /**
     * Sets dining status.
     *
     * @param diningStatus the dining status
     */
    public void setDiningStatus(boolean diningStatus) {
    	this.diningStatus = diningStatus;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Set order id counter.
     *
     * @param c the c
     */
    public static void setOrderIDCounter(int c){
        Order.orderCount = c;
    }

    /**
     * Gets food list.
     *
     * @return the food list
     */
    public List<OrderEntry> getFoodList() {
        return foodList;
    }

    /**
     * Add order item.
     *
     * @param orderItem the order item
     */
    public void addOrderItem(OrderEntry orderItem) {
        this.foodList.add(orderItem);
    }

    /**
     * Remove order item.
     *
     * @param index the index
     */
    public void removeOrderItem(int index) {
    	this.foodList.remove(index);
    }

    /**
     * Clear order item.
     */
    public void clearOrderItem(){
        foodList = new ArrayList<>();}

}

