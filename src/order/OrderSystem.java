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
    private Order currentOrder = new Order(); // Holds the order currently making


    public static Order getOrderByID(int orderID){
        for(Order o: App.orderList){
            if(o.getOrderID() == orderID){
                return o;
            }
        }
        throw new NoSuchElementException("No such order.");
    }

    private OrderStatus getOrderStatus(int orderID){

        return getOrderByID(orderID).getStatus();
    }

    public void addToCart(Food food){

        currentOrder.addOrderItem(food);
    }


    public  Order generateOrder(){ // #TODO Send Order to payment or what
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
    
    private int reviewCart() {
    	Scanner sc = new Scanner(System.in);
    	int opt = 0;
	       while(true) {
	    	   int i = 1;
	    	   System.out.println("======== Review cart ========");
	    	   for(Food custFood : currentOrder.getFoodList()) {
		    	   System.out.println(i + ". " + custFood.getName() + " $" + String.format("%.2f", custFood.getPrice()));
		    	   i++;
		       }
	    	   System.out.println();
	    	   System.out.println("Total: " + "$" + String.format("%.2f", currentOrder.getTotalCost()));
	    	   System.out.println("=============================");
	    	   System.out.println("1. Add more items");
	    	   System.out.println("2. Remove items");
	    	   System.out.println("3. Payment");
	    	   opt = sc.nextInt();
	    	   if(opt == 1) {
	    		   return 0;
	      }
	    	   else if(opt == 2) {
	    		   i = 1;
				  System.out.println("Select items to remove:");
				  for(Food custFood : currentOrder.getFoodList()) {
					  System.out.println(i + ". " + custFood.getName() + " $" + String.format("%.2f", custFood.getPrice()));
					  i++;
			   }
				   int opt2 = sc.nextInt();
				   currentOrder.removeOrderItem(opt2-1);
	      }
		      else if(opt == 3) {
		    	  System.out.println("Proceeding to payment");
		    	  return 1;
		      }
		      else {
		    	  System.out.println("Invalid option!");
		      }
		       }
	      
    }

    public void createNewOrder(){
        // TODO: Do everything here if users need to create an order
        // including I/O with command line
    	//left input handling
    	List<Food> catMenu = null;
    	int l = 0;
    	int rc = -1;
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Select Branch:");
        for (Branch branch : App.branchList) {
            System.out.println(l+1+" : "+ App.branchList.get(l).getBranchName());
            l++;
        }
        int opt = sc.nextInt();
        Menu menu = new Menu(App.foodList);
        Branch branch = App.branchList.get(opt-1);
        currentOrder.setBranch(branch.getBranchName());
        //Setting the menu according to the branch selected
        branch.setBranchMenu(menu.getFoodListByBranch(branch.getBranchName()));
        System.out.println("Select Dining status:");
        System.out.println("1. Takeaway");
        System.out.println("2. Dine-in");
        opt = sc.nextInt();
        if(opt == 1) {
        	currentOrder.setDiningStatus(false);
        }
        else if(opt == 2) {
        	currentOrder.setDiningStatus(true);
        }
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
    	
    	   case 5: // Review Cart
    		   rc = reviewCart();
    		   break;
    	   default:
    		   System.out.println("Invalid option");
    		   break;
    	   }
    	   if(opt <= 4) {
    		   int opt2 = sc.nextInt();
    		   currentOrder.addOrderItem(catMenu.get(opt2-1));
    	   	}
    	   else if(opt == 5) {
    		   if(rc == 1) {
    			   Payment payment = new Payment(currentOrder.getTotalCost(), "Credit Card");
    			   payment.processPayment();
    			   System.out.println();
    			   Receipt.printReceipt(currentOrder);
    			   currentOrder.setStatus(OrderStatus.PREPARING);
    			   App.orderList.add(currentOrder);
    			   break;
    		   }
    	   }
    		   
    	}
    }
}
    


