package src;

import src.branch.Admin;
import src.branch.Branch;

import java.util.Scanner;

import src.branch.Staff;
import src.order.Order;
import src.order.OrderSystem;
import src.menu.Food;

import java.util.List;
import java.util.ArrayList;

/**
 * @author fengyukun
 * Created at 21/3/24 17:11
 * Email : @author fengyukufyk@sina.com
 * Package : PACKAGE_NAME
 * @version 1.00.00
 **/
public class App {
	private static final Scanner sc = new Scanner(System.in);
    public static List<Branch> branchList;  // Stores every branch
    public static List<Order> orderList;  // Stores every order; Keeps orderID ascending
	public static List<Food> foodList;
	public static List<Staff> staffList;
	public static List<Admin> adminList;




	/*
	* Use this function to initialize everything when program starts
	* That is, all I/O functions, including reading Branch, Menu, Staff from .xls or .csv files.
	* */
	public static void initialize(){
		// reading orderList;
		branchList = FileIO.readBranchList();
		foodList = FileIO.readFoodList();
		staffList = FileIO.readStaffList();
		adminList = FileIO.readAdminList();
		// TODO orderList




		//Order.setOrderIDCounter(orderList.get(orderList.size() - 1).getOrderID() + 1);
		// Set the counter 1 more than the biggest existing orderID
	}


	//TODO Actually I don know where to put this in...
	public static Order searchOrderByID(int id){
		for(Order o:
		orderList){
			if(o.getOrderID() == id)return o;
		}
		return null;
	}



	/*
	 * Use this function to do something before program ends
	 * That is, all I/O functions, including saving Branch, Menu, Staff to .xls or .csv files.
	 * */
	public static void deinitialize(){ // TODO

	}



	public static void customerDriver(){
		System.out.println("Select an option:");
		System.out.println("1. Create a new order");
		System.out.println("2. Check the status of an existing order");
		System.out.println("3. Cancel");
		int secondOpt = sc.nextInt();
		while(secondOpt != 3){
			if(secondOpt == 1) {
				//OrderSystem cannot be static because everytime you create a new order, it will just add on the previous order.
				OrderSystem os = new OrderSystem();
				os.createNewOrder();
				break;
			} else if(secondOpt == 2) {
				OrderSystem.checkOrderStatus();
				break;
			} else System.out.println("Invalid Option");
			secondOpt = sc.nextInt();
		}
	}
	
	public static void staffDriver() {  //May want to use a HashMap for constant look up time
		String input;
		Staff loggedInStaff = null;
		do { 	
		System.out.println("LoginID:");
		System.out.println("Enter 'q' to exit");
		input = sc.next();
		if(input.equals("q")) {
			break;
		}
		for(Staff staff : staffList) {
			if(input.equals(staff.getLoginID())){
				loggedInStaff = staff;
				break;
			}
		}
		if(loggedInStaff != null) {
			System.out.println("Password:");
			input = sc.next();
			if(input.equals(loggedInStaff.getPassword())) {
				System.out.println("Login successful, " + loggedInStaff.getStaffName());
				break;
				//Proceed to staff page
			}
			else {
				System.out.println("Wrong password!");
				System.out.println();
			}
		}
		else {
			if(!input.equals("q")) {
			System.out.println("Invalid LoginID!");
			System.out.println();
			}
		}
	}while(!input.equals("q"));
		
	}
    public static void main(String[] args) {


		initialize();

    	int opt;
        do {
        	System.out.println("Select a domain:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Terminate");
        	opt = sc.nextInt();
        	switch(opt) {
        	case 1: // Customer
				customerDriver();
        		break;
        	case 2: //Staff
        		staffDriver();
        		break;
        	case 3:
        		break;
        	default:
        		System.out.println("Invalid option");
        		break;
        	}
        	System.out.println();
        }while(opt != 3);

		deinitialize();
    }
}
