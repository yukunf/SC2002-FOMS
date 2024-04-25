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
 * Tool class OrderSystem. Controls the UI of order generation, thus all methods are static.
 */
public class OrderSystem {
    // This static class holds every method needed for creating an order, no instance is needed so please make
    // everything static.


    /*-------------------- Tool Methods -----------------------------*/
    private Order currentOrder; // Holds the order currently making


	/**
	 * Get order by id order.
	 *
	 * @param orderID the order id
	 * @return the order
	 */
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

	/**
	 * Add a certain food to cart of the order. Use before finalize order.
	 *
	 * @param food the food
	 */
	public void addToCart(Food food){

        currentOrder.addOrderItem(new OrderEntry(food));
    }



    /*-------------------- User Methods -----------------------------*/

	/**
	 * Check order status. Method of User Interface
	 */
	public static void checkOrderStatus(){
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
            	System.out.println("======== Order Status ========");
            	System.out.println("Branch: " + o.getBranch());
                System.out.println("Status of Order "+id+" : "+o.getStatus());
                System.out.println("==============================");
                found = true;
                if(o.getStatus().equals(OrderStatus.READY)) {
                	System.out.println("1. Collect Order");
                	int opt = s.nextInt();
                	switch(opt) {
                	case 1:
                		System.out.println("Order collected!");
                		o.setStatus(OrderStatus.COLLECTED);
                		break;
                	default:
                		System.out.println("Invalid option!");
                		break;
                	}
                		
                }
            }
        }
        //need to remove order from orderlist upon collection

    }
    /**
     *	Review the cart. UI method.
     */
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
				   if(oe.isHasCustomization())
					   System.out.println("\t*Customize: "+oe.getCustomization());
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
		    	  if(currentOrder.getTotalCost() == 0) {
		    		  System.out.println("Error: No items in cart!");
		    		  return 0;
		    	  }
		    	  System.out.println("Proceeding to payment");
		    	  return 1;
		      }
		      else {
		    	  System.out.println("Invalid option!");
		      }
		       }
	      
    }

	/**
	 * Create new order.
	 * The UI method handles the whole process of creating an order from user.
	 */
	public void createNewOrder(){
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
        		if(branch.getState()) {
        	    System.out.println((l+1) + " : " + branch.getBranchName());
        	    l++;
        		}
        	}
        	System.out.println(l+1 + ". Back");
    	    try {
    	        opt = sc.nextInt();
    	        if(opt == l+1) {
    	        	return;
    	        }
    	        if (opt < 1 || opt > l+1) {
    	            throw new Exception();
    	        }
    	        validInput = true;
    	    } catch (Exception e) {
    	        System.out.println("Invalid input!");
    	        sc.nextLine(); // Clear the input buffer
    	    } 
    	}
    	int branchIndex = 0;
    	if (validInput) {
    	    // Map user input to index in App.branchList
    	    branchIndex = 0;
    	    for (int i = 0; i < App.branchList.size(); i++) {
    	        if (App.branchList.get(i).getState()) {
    	            branchIndex++;
    	            if (branchIndex == opt) {
    	                branchIndex = i; // Found the correct index
    	                break;
    	            }
    	        }
    	    }
    	}
    	Menu menu = new Menu(App.foodList);
    	Branch branch = App.branchList.get(branchIndex);
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
    		   System.out.println(i + ". Back");
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
    		   System.out.println(i + ". Back");
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
    		   System.out.println(i + ". Back");
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
    		   System.out.println(i + ". Back");
    		   System.out.println();
    		   break;

    	   case 5: // Review Cart
    		   if(currentOrder.getTotalCost() == 0) {
    			   System.out.println("Error: No item in cart!");
    		   }
    		   else {
    		   rc = reviewCart();
    		   if(rc == 0) {
    			   continue;
    		   }
    		   }
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
    		           if (opt2 < 1 || opt2 > catMenu.size()+1) {
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
    		   if(opt2 == catMenu.size() + 1) {
    			   continue;
    		   }
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
			   // clear input buffer
			   sc.nextLine();
/*-----------------------------      Customization Implemented      ---------------------------------*/
			   System.out.println("-------------------\n" +
					   "Enter customization for this item. \nIf no special needs, perform void input" +
					   " by input enter directly. ");
			   String cus = sc.nextLine();
			   if(cus.length() == 0){ // Void Input
				   System.out.println("----------No customization indicated---------");
				   oe.setHasCustomization(false);
			   }else{
				   oe.setHasCustomization(true);
				   oe.setCustomization(cus);
				   System.out.println("Customization of \""+ cus + "\" received");
			   }
    		   currentOrder.addOrderItem(oe);
    	   	}
    	   else if(opt == 5) {
    		   if(rc == 1) {
				   boolean paymentSuccess = Payment.processPayment(currentOrder);
				   if(paymentSuccess) {
					   System.out.println();
					   Receipt.printReceipt(currentOrder);
					   // currentOrder.setStatus(OrderStatus.PREPARING); Done by payment
					   currentOrder.setTime(System.currentTimeMillis()); // Register Time
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
    


