package src.order;

import src.menu.Food;

/**
 * @author fengyukun
 * Created at 22/3/24 01:19
 * Email : @author fengyukufyk@sina.com
 * Package : src.order
 * @version 1.00.00
 **/
public class OrderSystem {
    private static Order currentOrder = new Order(); // Holds the order currently making
    public OrderStatus checkOrderStatus(int orderID){
        // #TODO Do we need to ask which branch the order belongs to?
        return currentOrder.getStatus();
    }

    public void addToCart(Food food){
        currentOrder.addOrderItem(food);
    }


    public Order generateOrder(){ // #TODO Send Order to payment or what
        Order temp = currentOrder;
        currentOrder = new Order(); // Reset the order don't care about nulls
        return temp;
    }
}
