
package src.branch;


import src.order.Order;
import src.order.OrderStatus;

import java.util.Scanner;

import static src.App.orderList;


public class Staff {
	private char role = 'S';
	Scanner sc = new Scanner(System.in);
	
	private String name;
	private String loginID;
	private char gender;
	private int age;
	private Branch branch;
	private String password;
	private int loginTry=1; 
	
	public Staff(String name, String loginID, char gender, int age, Branch branch) {
		this.name=name;
		this.loginID=loginID;
		this.gender=gender;
		this.age=age;
		this.branch=branch;
		this.password = "password"; //default password
	}
	//TODO Actually I don know where to put this in...
	public Order searchOrderByID(int id){
		for(Order o : orderList){
			if(o.getOrderID() == id && o.getStatus().equals(OrderStatus.PREPARING) && o.getBranch().equals(branch.getBranchName()))return o;
		}
		return null;
	}
	
	public void displayOrders() {
		System.out.println("The list of orders:");
		for (Order order : orderList) {	
		  if (order.getBranch().equals(branch.getBranchName()) && order.getStatus()==OrderStatus.PREPARING) {
			  System.out.println("OrderID: " + order.getOrderID());  
		  }
	    }
	}
	public void viewDetails() {
		displayOrders();
		System.out.println("Enter OrderID to view order: ");
		int orderID = sc.nextInt();
		Order orderObject = searchOrderByID(orderID);
		if(orderObject == null){
			System.out.println("Order doesn't exist.");
			return;
		}
		String diningStatus;
		System.out.println("======== Order details ========");
		if(orderObject.getDiningStatus()) diningStatus = "Dining in";
		else diningStatus = "Take-away";
		System.out.println("OrderID: " + orderObject.getOrderID());
		System.out.println("Branch: "+ orderObject.getBranch() + " \nTime: "+ orderObject.getTime()+" \nDining Status: "+ diningStatus + " \nCurrent status: "+orderObject.getStatus());
		for (int i=0; i < orderObject.getFoodList().size(); i++) {
			String customization;
			if (orderObject.getFoodList().get(i).isHasCustomization()) {
				customization = orderObject.getFoodList().get(i).getCustomization();
			}
			else {
				customization= "None";
			}
			System.out.println();
			System.out.println((i+1)+". " +orderObject.getFoodList().get(i).getFood().getName()+ ", Quantity: "
					+orderObject.getFoodList().get(i).getQuantity()+ ", Customization: "+customization);
		}
		System.out.println("===============================");
		System.out.println();
	}


	public void processOrder() {
		displayOrders();
		System.out.println("Enter orderID to process: ");
		int currentOrder = sc.nextInt();
		int currentID = -1;
		int i = 0;
		for(Order order : orderList) {
			if(order.getOrderID() == currentOrder && order.getBranch().equals(branch.getBranchName()) && order.getStatus().equals(OrderStatus.PREPARING)) {
				currentID = i;
				break;
			}
			i++;
		}
		if(currentID == -1) {
			System.out.println("Order does not exist!");
			System.out.println();
			return;
		}
		System.out.println("Change status to:");
		System.out.println("1: PREPARING");
		System.out.println("2: READY");
		System.out.println("3: CANCELLED");
		int choice=0;
		do {
			choice = sc.nextInt();
			switch (choice) { 
				case 1:
					orderList.get(currentID).setStatus(OrderStatus.PREPARING);
					break;
				case 2:
					orderList.get(currentID).setStatus(OrderStatus.READY);
					break;
				case 3:
					orderList.get(currentID).setStatus(OrderStatus.CANCELLED);
					break;
				default: System.out.println("Invalid! Re-enter choice: ");
					break;
			}
		} while (choice!=1 && choice!=2  && choice!=3);
	}

		public String getStaffName () {
			return name;
		}
		public void changeStaffName (String newname){
			name = newname;
		}
		public void changeStaffID (String newID){
			loginID = newID;

		}
		public void changeStaffgender (char newgender){
			this.gender = newgender;
		}
		public char getGender() {
			return gender;
		}
		public int getAge() {
			return age;
		}
		public void changeStaffage (int newage){
			age = newage;
		}
		public void changeStaffbranch (Branch newbranch){
			branch = newbranch;
		}
		public String getLoginID () {
			return loginID;
		}
		public String getPassword () {
			return password;
		}
		public void setPassword (String password){  //For staff to change password
			this.password = password;
		}
		public void setRole(char role) {
			this.role=role;
		}
		public char getRole() {
			 if (this instanceof Manager) {
			        return 'M'; // Representing the role of a Manager
			    } else if (this instanceof Admin) {
			        return 'A'; // Representing the role of an Admin
			    } else {
			        return 'S'; // Default role for Staff
			    }
		}
		public void SetLoginTry () {
			this.loginTry++;
		}
		public int getLoginTry () {
			return loginTry;
		}
		public Branch getBranch() {
			return branch;
		}
		
		public void loadHomePage() {
			int choice=0;
			do {
				System.out.println();
				System.out.println("Select action:");
				System.out.println("1. View order details");
				System.out.println("2. Process order");
				System.out.println("3. Exit");
				choice = sc.nextInt();
				switch (choice) {
					case 1: this.viewDetails();
						break;
					case 2: this.processOrder();
						break;
					case 3: break;
					default: System.out.println("Invalid! Re-enter choice: ");
						break;
				}
			}while(choice!=3);
		}

	}
