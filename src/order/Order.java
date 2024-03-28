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
    private static int orderCount = 0;
    private int orderID;
    private int branchID;

    private int time; // Time when order is placed ( #TODO or when order is ready???)
                    // in unix timestamp
    private OrderStatus status;
    private List<Food> foodList;

    public Order() {
        orderID = orderCount;
        orderCount++; //everytime a new order is instantiated can increment no need to manually set it.
        branchID = 0;
        time = 0;
        status = OrderStatus.PREPARING;
        foodList = new ArrayList<>();
    }

    public Order(int branchID, int time, OrderStatus status, List<Food> orderItem) {
        orderID = orderCount;
        orderCount++; //everytime a new order is instantiated can increment no need to manually set it.

        this.branchID = branchID;
        this.time = time;
        this.status = status;
        this.foodList = orderItem;
    }

    public int getOrderID() {
        return orderID;
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

    public static void setOrderIDCounter(int c){
        Order.orderCount = c;
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
