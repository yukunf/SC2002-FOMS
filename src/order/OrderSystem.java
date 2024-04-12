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

        currentOrder.addOrderItem(new OrderEntry(food));
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
                break;
            }
            if(o != null){
                System.out.println("Status of Order "+id+" : "+o.getStatus());
                found = true;
            }
        }
        //need to remove order from orderlist upon collection

    }
    
    private int reviewCart() {
    	Scanner sc = new Scanner(System.in);
    	int opt = 0;
	       while(true) {
	    	   int i = 1;
	    	   System.out.println("======================= Review cart =======================");
	    	   System.out.printf("Item %-15s Quantity %-4s Unit Price %-2s Net Price%n", "", "", "");
	    	   System.out.println();
	    	   for(OrderEntry oe : currentOrder.getFoodList()) {
	    		   System.out.printf("%d. %-18s Qty: %-9d $%-10s $%-12s%n", i, oe.getFood().getName(), oe.getQuantity(), String.format("%.2f", oe.getFood().getPrice()), String.format("%.2f", oe.getQuantity()*oe.getFood().getPrice()));
		    	   i++;
		       }
	    	   System.out.println();
	    	   System.out.println("Total: " + "$" + String.format("%.2f", currentOrder.getTotalCost()));
	    	   System.out.println("===========================================================");
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
				  for(OrderEntry oe : currentOrder.getFoodList()) {
					  System.out.printf("%d. %-20s $%-10s Qty: %-4d%n", i, oe.getFood().getName(), String.format("%.2f", oe.getFood().getPrice()),oe.getQuantity());
					  i++;
			   }
				   int opt2 = sc.nextInt();
				   while(opt2 < 1 || opt2 > currentOrder.getFoodList().size()) {
					   System.out.println("Invalid input");
					   i = 1;
					   System.out.println("Select items to remove:");
						  for(OrderEntry oe : currentOrder.getFoodList()) {
							  System.out.printf("%d. %-20s $%-10s Qty: %-4d%n", i, oe.getFood().getName(), String.format("%.2f", oe.getFood().getPrice()),oe.getQuantity());
							  i++;
					   }
						   opt2 = sc.nextInt();
				   }
				   
				   if (currentOrder.getFoodList().get(opt2 - 1).getQuantity() >= 1) {
		
					    int quant = 0;
					    boolean validInput = false;
					    while (!validInput) {
					    	System.out.println("Enter the quantity to remove:");
					        try {
					            quant = sc.nextInt();
					            if (quant <= 0 || quant > currentOrder.getFoodList().get(opt2 - 1).getQuantity()) {
					                throw new Exception();
					            }
					            validInput = true;
					        } catch (Exception e) {
					            System.out.println("Invalid input! Please enter a valid quantity.");
					            sc.nextLine(); // Clear the input buffer
					        } 
					    }

					    if (currentOrder.getFoodList().get(opt2 - 1).getQuantity() == quant) {
					        currentOrder.removeOrderItem(opt2 - 1);
					    } else if (currentOrder.getFoodList().get(opt2 - 1).getQuantity() > quant) {
					        currentOrder.getFoodList().get(opt2 - 1).setQuantity(currentOrder.getFoodList().get(opt2 - 1).getQuantity()-quant);
					    }
					}
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

		currentOrder = new Order(); //refresh the order object
    	List<Food> catMenu = null;
    	int l;
    	int rc = -1;
    	Scanner sc = new Scanner(System.in);
    	int opt = 0;
    	boolean validInput = false;
    	while (!validInput) {
    		System.out.println("Select Branch:");
    		l = 0;
        	for (Branch branch : App.branchList) {
        	    System.out.println((l+1) + " : " + branch.getBranchName());
        	    l++;
        	}
    	    try {
    	        opt = sc.nextInt();
    	        if (opt < 1 || opt > App.branchList.size()) {
    	            throw new Exception();
    	        }
    	        validInput = true;
    	    } catch (Exception e) {
    	        System.out.println("Invalid input!");
    	        sc.nextLine(); // Clear the input buffer
    	    } 
    	}
    	Menu menu = new Menu(App.foodList);
    	Branch branch = App.branchList.get(opt-1);
    	currentOrder.setBranch(branch.getBranchName());
    	// Setting the menu according to the branch selected
    	branch.setBranchMenu(menu.getFoodListByBranch(branch.getBranchName()));

    	validInput = false;
    	while (!validInput) {
        	System.out.println("Select Dining status:");
        	System.out.println("1. Takeaway");
        	System.out.println("2. Dine-in");
    	    try {
    	        opt = sc.nextInt();
    	        if (opt != 1 && opt != 2) {
    	            throw new Exception();
    	        }
    	        validInput = true;
    	    } catch (Exception e) {
    	        System.out.println("Invalid input!");
    	        sc.nextLine(); // Clear the input buffer
    	    }
    	}
    	if (opt == 1) {
    	    currentOrder.setDiningStatus(false);
    	} else {
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
    		   int opt2 = 0;
    		   boolean validOption = false;
    		   
    		   while (!validOption) {
    		       try {
    		           System.out.println("Enter the option: ");
    		           opt2 = sc.nextInt();
    		           if (opt2 < 1 || opt2 > catMenu.size()) {
    		               throw new Exception();
    		           }
    		           validOption = true;
    		       } catch (Exception e) {
    		           System.out.println("Invalid input! Please enter a valid number.");
    		           sc.nextLine(); // Clear the input buffer
    		       } 
    		   }

    		   int quant = 0;
    		   boolean validQuantity = false;
    		   while (!validQuantity) {
    		       try {
    		           System.out.println("Enter quantity: ");
    		           quant = sc.nextInt();
    		           if (quant <= 0) {
    		               throw new Exception();
    		           }
    		           validQuantity = true;
    		       } catch (Exception e) {
    		           System.out.println("Invalid input! Please enter a valid number.");
    		           sc.nextLine(); // Clear the input buffer
    		       } 
    		   }

    		   OrderEntry oe = new OrderEntry(catMenu.get(opt2 - 1));
    		   oe.setQuantity(quant);
    		   currentOrder.addOrderItem(oe);
    	   	}
    	   else if(opt == 5) {
    		   if(rc == 1) {
				   boolean paymentSuccess = Payment.processPayment(currentOrder);
				   if(paymentSuccess) {
					   System.out.println();
					   Receipt.printReceipt(currentOrder);
					   // currentOrder.setStatus(OrderStatus.PREPARING); Done by payment
					   App.orderList.add(currentOrder);
				   }
				   else{
					   System.out.println("Payment Failed. Order will be dropped");
				   }
    			   break;
    		   }
    	   }
    		   
    	}
    }
}
    


