package src.branch;

import src.menu.Food;
import src.menu.FoodCategory;
import src.menu.Menu;
import src.App;
import src.FileIO;
import static src.menu.FoodCategory.*;

import java.util.List;

public class Manager extends Staff {

	private char role = 'M';
  
  public Manager(String name, String loginID, char gender, int age, Branch branch) {
	  super(name, loginID, gender, age, branch);
  }
  
  public void displayStaff() {
	 int i = 1;
	 System.out.println("======== Staff List ========");
	 System.out.printf("%-21s %-4s %s\n", "       Staff Name", "  Gender", "      Age");
	 for (Staff staff : App.staffList) {
	     if (staff.getBranch().getBranchName().equals(this.getBranch().getBranchName())) {
	         System.out.printf("%-4d %-20s %-10s %d\n", i, staff.getStaffName(), staff.getGender(), staff.getAge());
	         i++;
	     }
	 }
	System.out.println();
  }
	
  public void editMenu() {
	  System.out.println("Select action: ");
	  System.out.println("1. Add food items");
	  System.out.println("2. Edit food items");
	  System.out.println("3. Remove food items");
	  int choice = sc.nextInt();
	  switch (choice) {
	  case 1:
	    FoodCategory category = null;
		System.out.println("Select item category to add:");
		System.out.println("1. Side");
		System.out.println("2. Set Meal");
		System.out.println("3. Burger");
		System.out.println("4. Drink");
		do {
			int answer = sc.nextInt();
			switch (answer) {
				case 1: category = SIDE;
					break;
				case 2: category = SET_MEAL;
					break;
				case 3: category = BURGER;
					break;
				case 4: category = DRINK;
					break;
				default: System.out.println("Invalid! Re-enter choice: ");
					break;
			}
		} while (category==null);
		
		System.out.println("Enter new item name: ");
		String name = sc.nextLine(); // Consume the newline character
		name = sc.nextLine();
		System.out.println("Enter new price: $");
	  	double price = sc.nextDouble();
		Food newItem = new Food(name, price, this.getBranch().getBranchName(), category, true);
		App.foodList.add(newItem);
		FileIO.writeToMenu("menu_list.csv", App.foodList);
		System.out.println("Item added!");
	  	break;
	  case 2: 
		  String availability;
		  int k = 0;
		  int count = 0;
		  int selectedIndex = -1;
		  System.out.println("Choose food item to edit:");
	      for(Food food : App.foodList) {
	    	  if (food.getBranch().equals(this.getBranch().getBranchName())) {
	    		  if(food.isAvailability()) availability = "Yes";
	    		  else availability = "No";
	    		  System.out.println(k+1 + ". " + food.getName() + " $" + String.format("%.2f", food.getPrice()) + " Available: " + availability);
	    		  k++;
	    	  }
	    	 
	      }
	      int x = sc.nextInt();
	      for(int j = 0; j<App.foodList.size();j++) {
	    	  Food food = App.foodList.get(j);
	    	  if (food.getBranch().equals(this.getBranch().getBranchName())) {
	    		  count++;
	    		  if(count == x) {
	    			  selectedIndex = j;
	    			  break;
	    		  }
	    	  }
	      }
	      if(selectedIndex != -1) {
	    	  System.out.println("1. Edit price");
	    	  System.out.println("2. Edit availability");
	    	  x = sc.nextInt();
	    	  switch(x) {
	    	  case 1:
	    		  System.out.println("Enter new price:"); 
	    		  double newPrice = sc.nextDouble();
	    		  App.foodList.get(selectedIndex).setPrice(newPrice);
	    		  FileIO.writeToMenu("menu_list.csv", App.foodList);
	    		  System.out.println("Price changed!");
	    		  break;
	    	  case 2:
				  int opt=0;
	    		  System.out.println("Enter food availability:");
	    		  System.out.println("1. Availablable");
	    		  System.out.println("2. Unavailable");
				  do {
					opt = sc.nextInt();
	    		  	if(opt == 1) {
						App.foodList.get(x).setAvailability(true);
						break;
					}
	    		  	if(opt == 2) {
						App.foodList.get(x).setAvailability(false);
						break;
					}
					System.out.println("Invalid! Re-enter choice: ");
				  } while (!(opt == 1 || opt == 2));
	    		  System.out.println("Update Success!");
	    		  break;
	    	  }
	      }
		  break;
		  
	  case 3: 
		  int z = 0;
		  count = 0;
		  selectedIndex = -1;
		  System.out.println("Choose food item to remove from menu:");
		  for(Food food : App.foodList) {
	    	  if (food.getBranch().equals(this.getBranch().getBranchName())) {
	    		  System.out.println(z+1 + ". " + food.getName() + " $" + String.format("%.2f", food.getPrice()));
	    		  z++;
	    	  }
	   	  }
		  x = sc.nextInt();
	      for(int j = 0; j<App.foodList.size();j++) {
	    	  Food food = App.foodList.get(j);
	    	  if (food.getBranch().equals(this.getBranch().getBranchName())) {
	    		  count++;
	    		  if(count == x) {
	    			  selectedIndex = j;
	    			  break;
	    		  }
	    	  }
	      }
	      if(selectedIndex != -1) {
	    	  App.foodList.remove(selectedIndex);
	    	  System.out.println("Removed successfully!");
	    	  FileIO.writeToMenu("menu_list.csv", App.foodList);
	      }
		  break;
	
	  default: System.out.println("Invalid choice!");
		  break;
	  }
  }
  public void loadHomePage() {
	    int answer=0;
		do {
			System.out.println("Select action:");
			System.out.println("1. View order details");
			System.out.println("2. Process order");
			System.out.println("3. Display staff list");
			System.out.println("4. Edit Menu");
			System.out.println("5. Exit");
			answer = sc.nextInt();
			switch (answer) {
				case 1: this.viewDetails();
					break;
				case 2: this.processOrder();
					break;
				case 3: this.displayStaff();;
					break;
				case 4: this.editMenu();
					break;
				case 5: break;
				default: System.out.println("Invalid! Re-enter choice.");
					break;
			}
		} while(answer != 5);
	}
} 

