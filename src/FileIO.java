package src;


import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import src.branch.Admin;
import src.branch.Branch;
import src.branch.Staff;
import src.menu.Food;
import src.menu.FoodCategory;
import src.order.Order;

public class FileIO {

	
	//This reads the file and returns the list of all food
	public static List<Food> readFoodList(){
		List<Food> FoodList = new ArrayList<Food>();
		try {
			File MenuCsv = new File("menu_list.csv");
			Scanner sc = new Scanner(MenuCsv);
			sc.nextLine(); //skip the header row
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String data[] = line.split(",");
				if(data.length == 0) break;
				//availability default true
				Food food = new Food(data[0], Double.parseDouble(data[1]), data[2], FoodCategory.valueOf(data[3].toUpperCase()), true);
				FoodList.add(food);
			}
		}
		catch(FileNotFoundException f) {
			System.out.println("Invalid file");
		}
		
		return FoodList;
	}
	
	//This reads the file and returns the list of all staff
	public static List<Staff> readStaffList(){
		List<Staff> staffList = new ArrayList<Staff>();
		try {
			File staffCsv = new File("staff_list.csv");
			Scanner sc = new Scanner(staffCsv);
			sc.nextLine(); //skip the header row
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String data[] = line.split(",");
				if(data.length == 0) break;
				if(data[2].equals("S") || data[2].equals("M")) {
				Staff staff = new Staff(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), data[5]);
				staffList.add(staff);
				}
			}
		}
		catch(FileNotFoundException f) {
			System.out.println("Invalid file");
		}
		return staffList;
	}
	
	//This reads the file and return the list of branches
	public static List<Branch> readBranchList(){
		List<Branch> branchList = new ArrayList<Branch>();
		try {
		File BranchCsv = new File("branch_list.csv");
		Scanner sc = new Scanner(BranchCsv);
		sc.nextLine(); //skip the header row
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String data[] =line.split(",");
			if(data.length == 0) break;
			Branch branch = new Branch(data[0], Integer.parseInt(data[2]));
			branchList.add(branch);	
			}
 		}
		catch(FileNotFoundException f) {
			System.out.println("Invalid file!");
		}
		return branchList;
	}
	
	//reads the file and return the list of admins
	public static List<Admin> readAdminList(){
	List<Admin> adminList = new ArrayList<>();
	try {
		File staffCsv = new File("staff_list.csv");
		Scanner sc = new Scanner(staffCsv);
		sc.nextLine(); //skip the header row
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String data[] = line.split(",");
			if(data.length == 0) break;
			if(data[2].equals("A")) {
			Admin admin = new Admin(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]));
			adminList.add(admin);
			}
		}
	}
	catch(FileNotFoundException f) {
		System.out.println("Invalid file");
	}
	return adminList;

	}


	public static List<Order> readOrderList(){
		//TODO we will have our own csv file ( or serialized object orderList )
		return null;
	}

	public static void writeBackToFile(){
		//TODO Write Everything back to csv.

	}



}
