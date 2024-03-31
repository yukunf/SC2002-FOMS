package src.branch;


import src.order.Order;
import java.util.Scanner;


public class Staff {
	Scanner sc = new Scanner(System.in);
	
	private String name;
	private String loginID;
	private char gender;
	private int age;
	private String branch;
	private String password; 
	
	public Staff(String name, String loginID, char gender, int age, String branch) {
		this.name=name;
		this.loginID=loginID;
		this.gender=gender;
		this.age=age;
		this.branch=branch;
		this.password = "password"; //default password
	}
	
	public void displayOrders() {
	    int orderCount;
	    for (int i=0; i<orderCount; i++) {		// need to make orderCount public?
	      System.out.println(orderList[i]);		
	    }
	}
	public void viewDetails(Order order) {
		System.out.println("Enter order to view (input orderID): ");
		int currentOrder = sc.nextInt();
		System.out.println("Order details: ");
		System.out.println("Branch: "+orderList[currentOrder].branch+ ", Time: "+orderList[currentOrder].time+", Dine-in: "+orderList[currentOrder].diningStatus+ ", Current status: "+orderList[currentOrder].status);
//		int i=0;
//		while (orderList[currentOrder].foodList[i] != null) {
//			how to access quantity and food item bought?
//		}
		
		
	}
	public void processOrder() {
		System.out.println("Enter orderID: ");
		int currentOrder = sc.nextInt();
		System.out.println("change status to 1: PREPARING, 2: READY, 3: CANCELLED");
		int choice = sc.nextInt();
		switch (choice) {
			case 1: orderList[currentOrder].status = OrderStatus.PREPARING;
				break;
			case 2: orderList[currentOrder].status = OrderStatus.READY;
				break;
			case 3:	orderList[currentOrder].status = OrderStatus.CANCELLED;
				break;
	}
		
	public String getStaffName() {
		return name;
	}
	public void changeStaffName(Staff a, String newname) {
		a.name=newname;
	}
	public void changeStaffID(Staff a, String newID) {
		a.loginID=newID;
		
	}
	public void changeStaffgender(Staff a, char newgender) {
		a.gender=newgender;
	}
	public void changeStaffage(Staff a, int newage) {
		a.age=newage;
	}
	public void changeStaffbranch(Staff a, String newbranch) {
		a.branch=newbranch;
	}
	public String getLoginID() {
		return loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {  //For staff to change password
		this.password = password;
	}
	}
