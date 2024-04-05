package src.branch;

import src.menu.Food;
import src.menu.FoodCategory;

import static src.App.foodList;
import static src.menu.FoodCategory.*;

import java.util.List;

public class Manager extends Staff {

	private char role='M';
  
  
  public void displayStaff() {
	List<Staff> list=this.gettaffbranch().getStaffList();
	for(Staff staff : list){
		System.out.println(staff.getStaffName());

	public Manager() {
		super("NA","NA",'X',-1,"NA",'X');
	}
	public Manager(String name, String loginID, char gender, int age, String branch, char role) {
		super(name, loginID, gender, age, branch, role);
	}
  
	public void displayStaff(String branch) {
		for(Staff staff : staffList){
			if (staff.getBranch() == branch) {
				System.out.println(staff.getStaffName());
			}
		}
	}
	
   public Manager(String name, String loginID, char gender, int age, Branch branch) {
	  super(name, loginID, gender, age, branch);
  }
    
    
  public void editMenu() {
	  System.out.println("Select action: 1. Add 2. Edit 3. Remove");
	  int choice = sc.nextInt();
	  switch (choice) {
	  case 1: System.out.println("Select new item category: 1. Side 2. Set Meal 3. Burger 4. Drink");
		int answer = sc.nextInt();
		FoodCategory category = null;
		switch (answer) {
			case 1: category = SIDE;
				break;
			case 2: category = SET_MEAL;
				break;
			case 3: category = BURGER;
				break;
			case 4: category = DRINK;
				break;
		}
		System.out.println("Enter new item name: ");
	    	String name = sc.nextLine();
		System.out.println("Enter new price: $");
	  	double price = sc.nextDouble();
		System.out.println("Enter branch (NTU/ JP/ JE): ");
	  	String branch = sc.nextLine();
		Food newItem = new Food(name, price, branch, category, true);
		foodList.add(newItem);
	  	break;
	  case 2: System.out.println("Enter item name: ");
	      String item = sc.nextLine();
	      int x;
	      for (x=0; x<foodList.size(); x++) {
	  		  if (foodList.get(x).getName() == item) {
	  			  break;
	  		  }
	      }
	      String availability;
	      if (foodList.get(x).isAvailability()) {
	    	  availability="Yes";
	      }
	      else {
	    	  availability="No";
	      }
	      System.out.println(foodList.get(x).getName() + "- Price: $" +foodList.get(x).getPrice()+ ", Available: " +availability);
	      System.out.println("Enter new price: ");
	      double newPrice = sc.nextDouble();
	      foodList.get(x).setPrice(newPrice);
	      System.out.println("Still available (true/false): ");
	      boolean newAvail = sc.nextBoolean();
	      foodList.get(x).setAvailability(newAvail);
	      if (foodList.get(x).isAvailability()) {
	    	  availability="Yes";
	      }
	      else {
	    	  availability="No";
	      }
	      System.out.println("Updated to:");
	      System.out.println(foodList.get(x).getName() + "- Price: $" +foodList.get(x).getPrice()+ ", Available: "+availability);
		  break;
	  case 3: System.out.println("Enter item name: ");
	  	  String toRemove = sc.nextLine();
	  	  for (int y=0; y<foodList.size(); y++) {
	  		  if (foodList.get(y).getName() != toRemove) {
	  			foodList.remove(y);
	  			System.out.println("Removed item.");
	  			break;
	  		  }
	  	  }	  	  
		  break;
	  }
  }
}  
