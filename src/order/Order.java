package src.order;

import src.menu.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyukun
 * Created at 21/3/24 17:50
 * Email : @author fengyukufyk@sina.com
 * Package : src.order
 * @version 1.00.00
 **/
public class Order {
    private int orderID;
    private int branchID;

    private int time; // Time when order is placed ( #TODO or when order is ready???)
                    // in unix timestamp
    private OrderStatus status;
    private List<Food> foodList;

    public Order() {
        orderID = 0;
        branchID = 0;
        time = 0;
        status = OrderStatus.PREPARING;
        foodList = new ArrayList<>();
    }

    public Order(int orderID, int branchID, int time, OrderStatus status, List<Food> orderItem) {
        this.orderID = orderID;
        this.branchID = branchID;
        this.time = time;
        this.status = status;
        this.foodList = orderItem;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
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

    public List<Food> getFoodList() {
        return foodList;
    }

    public void addOrderItem(Food orderItem) {
        this.foodList.add(orderItem);
    }

    public void clearOrderItem(){
        foodList = new ArrayList<>();}
}
