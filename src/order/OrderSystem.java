package src.order;

import src.App;
import src.branch.Branch;
import src.menu.Food;

import java.util.NoSuchElementException;

/**
 * @author fengyukun
 * Created at 22/3/24 01:19
 * Email : @author fengyukufyk@sina.com
 * Package : src.order
 * @version 1.00.00
 **/
public class OrderSystem {
    // This static class holds every method needed for creating a order, no instance is needed so please make
    // everything static.


    /*-------------------- Tool Methods -----------------------------*/
    private static Order currentOrder = new Order(); // Holds the order currently making


    public static Order getOrderByID(int orderID) throws NoSuchElementException{
        for(Order o: App.orderList){
            if(o.getOrderID() == orderID){
                return o;
            }
        }
        throw new NoSuchElementException("No Such branch.");
    }

    private static OrderStatus getOrderStatus(int orderID) throws NoSuchElementException{
        return getOrderByID(orderID).getStatus();
    }

    public static void addToCart(Food food){
        currentOrder.addOrderItem(food);
    }


    public static  Order generateOrder(){ // #TODO Send Order to payment or what
        Order temp = currentOrder;
        currentOrder = new Order(); // Reset the order don't care about nulls
        return temp;
    }


    /*-------------------- User Methods -----------------------------*/

    public static void checkOrderStatus(){
        // TODO: Do everything here if users need to check the order status
        // including I/O with command line
    }

    public static void createNewOrder(){
        // TODO: Do everything here if users need to create an order
        // including I/O with command line
    	System.out.println("Select Branch:");
    	System.out.println("1. NTU");
    	System.out.println("2. JP");
    	System.out.println("3. JE");
    }
}
