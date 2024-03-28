package src.order;

import src.App;
import src.branch.Branch;
import src.menu.Food;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author fengyukun
 * Created at 22/3/24 01:19
 * Email : @author fengyukufyk@sina.com
 * Package : src.order
 * @version 1.00.00
 **/
public class OrderSystem {
    // This static class holds every method needed for creating an order, no instance is needed so please make
    // everything static.


    /*-------------------- Tool Methods -----------------------------*/
    private static Order currentOrder = new Order(); // Holds the order currently making


    public static Order getOrderByID(int orderID){
        for(Order o: App.orderList){
            if(o.getOrderID() == orderID){
                return o;
            }
        }
        throw new NoSuchElementException("No such order.");
    }

    private static OrderStatus getOrderStatus(int orderID){

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
        Scanner s = new Scanner(System.in);
        System.out.println("Please input the Order ID:");
        boolean found = false;
        Order o = null;
        int id = s.nextInt();
        while(!found) {
            try {
                o = getOrderByID(id);
            } catch (Exception e) {
                System.out.println("Order Not Found! Please check your input and try again");
            }
            if(o != null){
                System.out.println("Status of Order "+id+" : "+o.getStatus());
                found = true;
            }
        }

    }

    public static void createNewOrder(){
        // TODO: Do everything here if users need to create an order
        // including I/O with command line
        int l = App.branchList.size();
    	System.out.println("Select Branch:");
        for (int i = 0; i < l; i++) {
            System.out.println(l+" : "+ App.branchList.get(l).getBranchName());
        }

    }
}
