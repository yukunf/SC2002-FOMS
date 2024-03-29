package src.order;

import src.App;
import src.branch.Branch;
import src.menu.Food;
import src.menu.Menu;
import src.menu.FoodCategory;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;

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
    	//Receipt, input handling
    	//Edit cart (adding/removing from cart)
    	//Payment page
    	List<Food> catMenu = null;
    	int l = 0;
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Select Branch:");
        for (Branch branch : App.branchList) {
            System.out.println(l+1+" : "+ App.branchList.get(l).getBranchName());
            l++;
        }
        int opt = sc.nextInt();
        Menu menu = new Menu(App.foodList);
        Branch branch = App.branchList.get(opt-1);
        branch.setBranchMenu(menu.getFoodListByBranch(branch.getBranchName()));
       while(true) {
    	   int i = 1;
    	   System.out.println("Select category:");
    	   for(FoodCategory fc : FoodCategory.values()) {
    		   System.out.println(i + ". " + fc);
    		   i++;
        	}
    	   System.out.println(i + ". " + "Review cart");
    	   i = 1;
    	   opt = sc.nextInt();
    	   if(opt == 5) {
    		   break;
    	   }
    	   switch(opt) {
    	   case 1:
    		   catMenu = menu.getFoodListByCategory(branch.getBranchMenu(), FoodCategory.BURGER);
    		   if(catMenu.isEmpty()) {
    			   System.out.println("This branch has no burgers.");
    			   System.out.println();
    			   continue;
    		   }
    		   for(Food cateFood : catMenu) {
    			   System.out.println(i + ". " + cateFood.getName() + " $" + String.format("%.2f", cateFood.getPrice()));
    			   i++;
    		   }
    		   System.out.println();
    		   break;
    	   case 2:
    		   catMenu = menu.getFoodListByCategory(branch.getBranchMenu(), FoodCategory.SIDE);
    		   if(catMenu.isEmpty()) {
    			   System.out.println("This branch has no sides.");
    			   System.out.println();
    			   continue;
    		   }
    		   for(Food cateFood : catMenu) {
    			   System.out.println(i + ". " + cateFood.getName() + " $" + String.format("%.2f", cateFood.getPrice()));
    			   i++;
    		   }
    		   System.out.println();
    		   break;
    	   case 3:
    		   catMenu = menu.getFoodListByCategory(branch.getBranchMenu(), FoodCategory.DRINK);
    		   if(catMenu.isEmpty()) {
    			   System.out.println("This branch has no drinks.");
    			   System.out.println();
    			   continue;
    		   }
    		   for(Food cateFood : catMenu) {
    			   System.out.println(i + ". " + cateFood.getName() + " $" + String.format("%.2f", cateFood.getPrice()));
    			   i++;
    		   }
    		   System.out.println();
    		   break;
    	   case 4:
    		   catMenu = menu.getFoodListByCategory(branch.getBranchMenu(), FoodCategory.SET_MEAL);
    		   if(catMenu.isEmpty()) {
    			   System.out.println("This branch has no set meals.");
    			   System.out.println();
    			   continue;
    		   }
    		   for(Food cateFood : catMenu) {
    			   System.out.println(i + ". " + cateFood.getName() + " $" + String.format("%.2f", cateFood.getPrice()));
    			   i++;
    			 
    		   }
    		   System.out.println();
    		   break;
    	   default:
    		   System.out.println("Invalid option");
    		   break;
    	   }
    	   opt = sc.nextInt();
    	   currentOrder.addOrderItem(catMenu.get(opt-1));
    	   }
       //Review cart
       int i = 1;
       for(Food custFood : currentOrder.getFoodList()) {
    	   System.out.println(i + ". " + custFood.getName() + " $" + String.format("%.2f", custFood.getPrice()));
    	   i++;
       }
       System.out.println("Total: " + "$" + String.format("%.2f", currentOrder.getTotalCost()));
        }
    
    }

