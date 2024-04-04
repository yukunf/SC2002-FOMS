package src.branch;


import src.order.Order;
import src.order.OrderStatus;

import java.util.Scanner;

import static src.App.orderList;
import static src.App.searchOrderByID;


public class Staff {
	Scanner sc = new Scanner(System.in);
	
	private String name;
	private String loginID;
	private char gender;
	private int age;
	private String branch;
	private char role;
	private String password;
	private int loginTry=0; 
	
	public Staff(String name, String loginID, char gender, int age, String branch, char role) {
		this.name=name;
		this.loginID=loginID;
		this.gender=gender;
		this.age=age;
		this.branch=branch;
		this.password = "password"; //default password
	}
	
	public void displayOrders() {
		for (Order order : orderList) {	
		  if (order.getStatus()==OrderStatus.UNPAID) {
			  System.out.println(order.getOrderID());  
		  }
	    }
	}
	public void viewDetails() {
		System.out.println("Enter order to view (input orderID): ");
		int orderID = sc.nextInt();
		Order orderObject = searchOrderByID(orderID);
		if(orderObject == null){
			System.out.println("Order doesn't exist.");
			return;
		}

		System.out.println("Order details: ");
		System.out.println("Branch: "+ orderObject.getBranch()+ ", Time: "+ orderObject.getTime()+", Dine-in: "+orderObject.getBranch()+ ", Current status: "+orderObject.getStatus());
		for (int i=0; i < orderObject.getFoodList().size(); i++) {
			String customization;
			if (orderObject.getFoodList().get(i).isHasCustomization()) {
				customization = orderObject.getFoodList().get(i).getCustomization();
			}
			else {
				customization= "None";
			}
			System.out.println((i+1)+". Food item: " +orderObject.getFoodList().get(i).getFood()+ ", Food item: "
					+orderObject.getFoodList().get(i).getQuantity()+ ", Customization: "+customization);
		}
	}


	public void processOrder() {
		System.out.println("Enter orderID: ");
		int currentOrder = sc.nextInt();
		System.out.println("change status to 1: PREPARING, 2: READY, 3: CANCELLED");
		int choice = sc.nextInt();
		switch (choice) { 
			case 1:
				orderList.get(currentOrder).setStatus(OrderStatus.PREPARING);
				break;
			case 2:
				orderList.get(currentOrder).setStatus(OrderStatus.READY);
				break;
			case 3:
				orderList.get(currentOrder).setStatus(OrderStatus.CANCELLED);
				break;
		}
	}

		public String getStaffName () {
			return name;
		}
		public void changeStaffName (Staff a, String newname){
			a.name = newname;
		}
		public void changeStaffID (Staff a, String newID){
			a.loginID = newID;

		}
		public void changeStaffgender (Staff a,char newgender){
			a.gender = newgender;
		}
		public void changeStaffage (Staff a,int newage){
			a.age = newage;
		}
		public void changeStaffbranch (Staff a, String newbranch){
			a.branch = newbranch;
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
			return role;
		}
		public void SetLoginTry () {
			this.loginTry++;
		}
		public int getLoginTry () {
			return loginTry;
		}
		public String getBranch() {
			return branch;
		}

	}
