package src;

import src.branch.Admin;
import src.branch.Branch;
import src.branch.Manager;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

import src.branch.Staff;
import src.order.Order;
import src.order.OrderStatus;
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
	private static String orderFilePath = "orders.ser";
	public static List<Food> foodList;
	public static List<Staff> staffList;
	public static List<Manager> ManagerList;
	public static List<Admin> adminList;
	public static List<Staff> allEmployeesList;




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
		ManagerList=FileIO.readManagerList();
		allEmployeesList = FileIO.readAllEmployees();
		// TODO orderList
		orderList = new ArrayList<Order>();

		orderList = deserializeOrderList();


		//Order.setOrderIDCounter(orderList.get(orderList.size() - 1).getOrderID() + 1);
		// Set the counter 1 more than the biggest existing orderID
	}

	private static void serializeOrderList(){

		try {
			FileOutputStream fos = new FileOutputStream(orderFilePath);
			ObjectOutputStream outputStream = new ObjectOutputStream(fos);
			outputStream.writeObject(orderList);
			outputStream.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	private static List<Order> deserializeOrderList(){
		List<Order> ol = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(orderFilePath);
			ObjectInputStream inputStream = new ObjectInputStream(fis);
			Object o = inputStream.readObject();
			if(o == null){
				inputStream.close();
				return ol;
			}else{
				ol = (ArrayList<Order>)o;
			}
			inputStream.close();
		} catch (FileNotFoundException ex) {
			return ol;
		}
		catch (IOException | ClassNotFoundException e){
			System.out.println(e);
		}
		return ol;
	}

	/*
	 * Use this function to do something before program ends
	 * That is, all I/O functions, including saving Branch, Menu, Staff to .xls or .csv files.
	 * */
	public static void deinitialize(){ // TODO
		serializeOrderList();
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

	public static void Staffpage(Staff loggedInStaff){
		int choice;
		do {
		System.out.println();
		System.out.println("Select action:");
		System.out.println("1. View order details");
		System.out.println("2. Process order");
		System.out.println("3. Exit");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1: loggedInStaff.viewDetails();
                                break;
                            case 2: loggedInStaff.processOrder();
						}
		}while(choice < 3);       
	}

	public static void Managerpage(Manager loggedInManager){
		int answer;
		do {
		System.out.println("Select action:");
		System.out.println("1. View order details");
		System.out.println("2. Process order");
		System.out.println("3. Display staff list");
		System.out.println("4. Edit Menu");
		System.out.println("5. Exit");
		answer = sc.nextInt();
		switch (answer) {
			case 1: loggedInManager.viewDetails();
				break;
			case 2: loggedInManager.processOrder();
				break;
			case 3: loggedInManager.displayStaff();;
				break;
			case 4: loggedInManager.editMenu();
				break;
		}
		}while(answer < 5);
		
	}
	public static void AdminPage(Admin loggedInAdmin){
		int answer;
		do {
		System.out.println("Select action:");
		System.out.println("1. Add/Edit/Remove Staff");
		System.out.println("2. Display staff");
		System.out.println("3. Assign managers to a branch");
		System.out.println("4. Promote a staff to a Branch manager");
		System.out.println("5. Transfer a staff/manager");
		System.out.println("6. Add/Remove payment method");
		System.out.println("7. Open/Close Branch");
		System.out.println("8. Quit");
		answer = sc.nextInt();
		switch (answer) {
			case 1: 
				System.out.println("Enter option");
				System.out.println("1. Add");
				System.out.println("2. Edit");
				System.out.println("3. Remove");
				int option=sc.nextInt();
				//Add
				if(option==1){
					System.out.println("Enter the branch the staff is adding to:");
					for(int i=0;i<branchList.size();i++){
						System.out.println((i+1)+". "+branchList.get(i).getBranchName());
					}
					int index;
					index=sc.nextInt();
					Branch branch=branchList.get(index-1);
					loggedInAdmin.AddStaff(branch);
				}
				//Edit
				if(option==2){
					System.out.println("Enter the branch the staff is in:");
					for(int i=0;i<branchList.size();i++){
						System.out.println((i+1) + ". " + branchList.get(i).getBranchName());
					}
					int index;
					index=sc.nextInt();
					Branch branch=branchList.get(index-1);

					System.out.println("Enter the name of the satff to edit:");
					String name;
					name=sc.next();
					Staff s;
					for(Staff staff:branch.getStaffList()){
						if(name==staff.getStaffName()) {
							s=staff;
							loggedInAdmin.EditStaff(s, branch);
							break;
						}
					}
					
				}
				if (option==3){
					System.out.println("Enter the branch the staff is in:");
					for(int i=0;i<branchList.size();i++){
						System.out.println((i+1) + ". " + branchList.get(i).getBranchName());
					}
					int index;
					index=sc.nextInt();
					Branch branch=branchList.get(index-1);
					loggedInAdmin.RemoveStaff(branch);
				}
				break;
				
			case 2: 
				System.out.println("Enter the branch to display the stafflist");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+ ". " + branchList.get(i).getBranchName());
				}
				int index;
				index=sc.nextInt();
				Branch branch=branchList.get(index-1);
				loggedInAdmin.DisplayStaff(branch);
				break;
			case 3: 
				System.out.println("Enter the branch");

				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1) + ". "+branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				loggedInAdmin.AssignManager(branch);
				break;
			case 4: 
				System.out.println("Enter the branch");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1) + 	". " + branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				System.out.println("Enter the name of the staff to promote to Manager");
				String name;
					name=sc.next();
					Staff s;
					for(Staff staff:branch.getStaffList()){
						if(name==staff.getStaffName()) {
							s=staff;
							Manager m=new Manager(s.getStaffName(),s.getLoginID(),s.getGender(),s.getAge(),s.getBranch());
							loggedInAdmin.RemoveStaff(branch);
							loggedInAdmin.AssignManager(branch, m);
							break;
						}
					}
				break;
			case 5: 
			System.out.println("Transfer () to a new branch 1.Manager	2.Staff");
			index=sc.nextInt();
			System.out.println("Enter the original branch the staff was in:");
			for(int i=0;i<branchList.size();i++){
				System.out.println((i+1)+ ". "+ branchList.get(i).getBranchName());
			}
			index=sc.nextInt();
			Branch oribranch=branchList.get(index-1);
			System.out.println("Enter the new branch");
			for(int i=0;i<branchList.size();i++){
				System.out.println((i+1) + ". " + branchList.get(i).getBranchName());
			}
			index=sc.nextInt();
			Branch newbranch=branchList.get(index-1);
			if (index==1){
				System.out.println("Enter the name of the Manger:");
				name=sc.next();
				for(Manager m:oribranch.getmanagerlist()){
					if(name==m.getStaffName()) {
						loggedInAdmin.TransferManager(newbranch, oribranch, m);;
						break;
					}
				}
			}
			if (index==2){
				System.out.println("Enter the name of the Staff:");
				name=sc.next();
				
				for(Staff staff:oribranch.getStaffList()){
					if(name==staff.getStaffName()) {
						loggedInAdmin.TransferStaff(newbranch, oribranch, staff);;
						break;
					}
				}
			}
			break;

			case 6:
			System.out.println("Do you want to");
			System.out.println("1. Add Payment");
			System.out.println("2. Remove Payment");
			index=sc.nextInt();
			if(index==1){
				System.out.println("Enter the new payment method:");
				String newmethod=sc.next();
				loggedInAdmin.addpaymentmethod(newmethod);
			}
			if(index==2){
				System.out.println("Enter the new payment to remove:");
				String removingmethod=sc.next();
				loggedInAdmin.removepaymentmethod(removingmethod);
			}
			break;

			case 7:			
			System.out.println("Do you want to");
			System.out.println("1. Open Branch");
			System.out.println("2. Close Branch");
			index=sc.nextInt();
			if(index==1){
				System.out.println("Enter the branch to open");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1) + ". " + branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				loggedInAdmin.open(branch);
			}
			if(index==2){
				System.out.println("Enter the branch to close");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+ ". " + branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				loggedInAdmin.close(branch);
			}

		}
	}while(answer < 8);
		
	}



	
	public static void staffDriver() {  //May want to use a HashMap for constant look up time
		String input;

		int option;
		Staff loggedInStaff = null;
		Manager loggedInManager=null;
		Admin loggedInAdmin=null;


		do {
		loggedInStaff = null;
		System.out.println("LoginID:");
		System.out.println("Enter 'q' to exit");
		input = sc.next();
		if(input.equals("q")) {
			break;
		}
		for(Staff staff : allEmployeesList) {
			if(input.equals(staff.getLoginID())){
				loggedInStaff = staff;
				break;
			}
		}
		if(loggedInStaff != null) {
			System.out.println("Password:");
			input = sc.next();
			if(input.equals(loggedInStaff.getPassword())) {
				System.out.println();
				System.out.println("Login successful, " + loggedInStaff.getStaffName());

				// reset password if first successful login
				if (loggedInStaff.getLoginTry()==1) {
					System.out.println("Input new password: ");
					String newPassword = sc.next();
					loggedInStaff.setPassword(newPassword);
					System.out.println("Password updated succesfully.");
					System.out.println();
					loggedInStaff.SetLoginTry();
				}

				//Proceed to staff page
				char role=loggedInStaff.getRole(); 
				switch (role) {
					case 'S': 
						Staffpage(loggedInStaff);
						break;
					case 'M': 
						Managerpage((Manager)loggedInStaff);
						break;
					case 'A':
						AdminPage((Admin)loggedInStaff);
						break;
				}
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