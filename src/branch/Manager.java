package src.branch;

public class Manager extends Staff {
  public Manager() {
      super("NA","NA",'M',-1,"NA");
  }
  
  public void displayStaff() {
	int StaffQuota=getStaffQuota();
    for (int i=0; i<StaffQuota; i++) {
      System.out.println(staffList[i]);
    }
  }
  public void editMenu() {
	  System.out.println("Select action: 1. Add 2. Edit 3. Remove");
	  switch (choice) {
	  case 1: System.out.println("Select new item category: 1. Side 2. Set Meal 3. Burger 4. Drink");
		int category = sc.nextInt();
		switch (category) {
			case 1: FoodCategory category = SIDE;
				break;
			case 2: FoodCategory category = SET_MEAL;
				break;
			case 3: FoodCategory category = BURGER;
				break;
			case 4: FoodCategory category = DRINK;
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
	      int x=0;
	      while (foodList[x].name != item) {
	    	  x++;
	      }
	      System.out.println(foodList[x].name + "- Price: $" +foodList[x].price+ ", Available: "foodList[x].availability);
	      System.out.println("Enter new price: ");
	      double newPrice = sc.nextDouble();
	      System.out.println("Still available (true/false): ");
	      boolean newAvail = sc.nextBoolean();
	      foodList[x].price = newPrice;
	      foodList[x].availability = newAvail;
	      System.out.println("Updated to:");
	      System.out.println(foodList[x].name + "- Price: $" +foodList[x].price+ ", Available: "foodList[x].availability);
		  break;
	  case 3: System.out.println("Enter item name: ");
	  	  String toRemove = sc.nextLine();
	  	  while (foodList[x] != null) {
	    	  if (foodList[x].name != toRemove) {
	    		  x++;
	    	  }
	  		  if (foodList[x].name == toRemove) {
	  			  int y=x+1;
	  			  while (foodList[y] != null) {
	  				  foodList[x] = foodList[y];
	  				  x++;
	  				  y++;
	  			  }
	  			  System.out.println("Removed item.");
	  			  break;
	  		  }
	      }
		  break;
	  }
  }
}  
