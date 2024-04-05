package src;

import src.branch.Admin;
import src.branch.Branch;
import src.branch.Manager;

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
	public static List<Manager> ManagerList;
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
		ManagerList=FileIO.readManagerList();
		// TODO orderList
		orderList = new ArrayList<Order>();



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

	public static String findtherole(String str){
		for(Staff s:staffList){
			if(s.getLoginID()==str) return "S";
		}

		for(Manager m: ManagerList){
			if(m.getLoginID()==str) return "M";
		}
		for(Admin a: adminList){
			if(a.getLoginID()==str) return "A";
		}
		return "z";
	}

	public static int checkpassword(String correctpassword, String staffname){
		System.out.println("Password:(if quite enter 'q')");
            String input = sc.next();
			if (input=="q") return 2;
            if(input.equals(correctpassword)) {
                System.out.println("Login successful, " + staffname);
				return 1;
			}
			System.out.println("Wrong password, try again.");          
			return 0;
	}

	public static void Staffpage(Staff loggedInStaff){
		System.out.println("Select action: 1. Display new orders 2. View order details 3. Process order");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1: loggedInStaff.displayOrders();
                                break;
                            case 2: loggedInStaff.viewDetails();
                                break;
                            case 3: loggedInStaff.processOrder();
						}
                    
	}

	public static void Managerpage(Manager loggedInManager){
		System.out.println("Select action: 1. Display new orders 2. View order details 3. Process order 4. Display staff list 5. Edit Menu");
		int answer = sc.nextInt();
		switch (answer) {
			case 1: loggedInManager.displayOrders();
				break;
			case 2: loggedInManager.viewDetails();
				break;
			case 3: loggedInManager.processOrder();
				break;
			case 4: loggedInManager.displayStaff();;
				break;
			case 5: loggedInManager.editMenu();
				break;
		}

		
	}
	public static void AdminPage(Admin loggedInAdmin){
		System.out.println("Select action: 1.Add/Edit/Remove Staff 2. Display staff  3. Assign managers to a branch 4.Promote a staff to a Branch manager 5.Transfer a staff/manger among branches 6. Add/Remove paymeny method 7.Open.Close Branch.");
		int answer = sc.nextInt();
		switch (answer) {
			case 1: 
				System.out.println("1.Add 2.Edit 3. Remove");
				int option=sc.nextInt();
				//Add
				if(option==1){
					System.out.println("Enter the branch the staff is adding to:");
					for(int i=0;i<branchList.size();i++){
						System.out.println((i+1)+branchList.get(i).getBranchName());
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
						System.out.println((i+1)+branchList.get(i).getBranchName());
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
						System.out.println((i+1)+branchList.get(i).getBranchName());
					}
					int index;
					index=sc.nextInt();
					Branch branch=branchList.get(index-1);

					System.out.println("Enter the name of the satff to remove:");
					String name;
					name=sc.next();
					Staff s;
					for(Staff staff:branch.getStaffList()){
						if(name==staff.getStaffName()) {
							s=staff;
							loggedInAdmin.RemoveStaff(s, branch);
							break;
						}
					}
				}
				break;
			case 2: 
				System.out.println("Enter the branch to display the stafflist");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+branchList.get(i).getBranchName());
				}
				int index;
				index=sc.nextInt();
				Branch branch=branchList.get(index-1);
				loggedInAdmin.DisplayStaff(branch);
				break;
			case 3: 
				System.out.println("Enter the branch");

				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				loggedInAdmin.AssignManager(branch);
				break;
			case 4: 
				System.out.println("Enter the branch");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+branchList.get(i).getBranchName());
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
							Manager m=new Manager(s.getStaffName(),s.getLoginID(),s.getstaffgender(),s.getstaffage(),s.gettaffbranch());
							loggedInAdmin.RemoveStaff(s, branch);
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
				System.out.println((i+1)+branchList.get(i).getBranchName());
			}
			index=sc.nextInt();
			Branch oribranch=branchList.get(index-1);
			System.out.println("Enter the new branch");
			for(int i=0;i<branchList.size();i++){
				System.out.println((i+1)+branchList.get(i).getBranchName());
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
			System.out.println("Do you want to 1.add 2. remove payment");
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
			System.out.println("Do you want to 1.open 2.close branch");
			index=sc.nextInt();
			if(index==1){
				System.out.println("Enter the branch to open");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				loggedInAdmin.open(branch);
			}
			if(index==2){
				System.out.println("Enter the branch to close");
				for(int i=0;i<branchList.size();i++){
					System.out.println((i+1)+branchList.get(i).getBranchName());
				}
				index=sc.nextInt();
				branch=branchList.get(index-1);
				loggedInAdmin.close(branch);
			}

		}
		
	}



	
	public static void staffDriver() {  //May want to use a HashMap for constant look up time
		String input;
		int option;
		Staff loggedInStaff = null;
		Manager loggedInManager=null;
		Admin loggedInAdmin=null;


		//Getting the Id
		System.out.println("LoginID:");
		System.out.println("Enter 'q' to exit");
		input = sc.next();
		String role=findtherole(input);
		if (input=="q") return;
		while (role=="z"){
			System.out.println("Invalid Id, enter again.");
			System.out.println("LoginID:");
			System.out.println("Enter 'q' to exit");
			input = sc.next();
			if(input=="q") return;
			role=findtherole(input);
		}

		//Check for password
		if(role=="S"){
			for(Staff s : staffList) {
				if(input.equals(s.getLoginID())){
					loggedInStaff=s;
					break;
				}
		}
		option=checkpassword(loggedInStaff.getPassword(), loggedInStaff.getStaffName());
		while(option==1) checkpassword(loggedInStaff.getPassword(), loggedInStaff.getStaffName());
		if(option==0) Staffpage(loggedInStaff);
	}


	else if(role=="M"){
		for(Manager m:ManagerList) {
			if(input.equals(m.getLoginID())){
				loggedInManager=m;
				break;
			}
	}
		option=checkpassword(loggedInManager.getPassword(), loggedInManager.getStaffName());
		while(option==1) checkpassword(loggedInManager.getPassword(), loggedInManager.getStaffName());
		if(option==0) Managerpage(loggedInManager);
	}


	else{
		for(Admin a:adminList) {
			if(input.equals(a.getLoginID())){
				loggedInAdmin=a;
				break;
			}
	}
		option=checkpassword(loggedInAdmin.getPassword(), loggedInAdmin.getStaffName());
		while(option==1) checkpassword(loggedInAdmin.getPassword(), loggedInAdmin.getStaffName());
		if(option==0) AdminPage(loggedInAdmin);
	}

	if(option==2) return;
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