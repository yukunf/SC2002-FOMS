package src;


import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import src.branch.Admin;
import src.branch.Branch;
import src.branch.Staff;
import src.order.Order;
import src.menu.Food;
import src.menu.FoodCategory;

public class Filereader {
	public static List<Branch> branchList = new ArrayList<Branch>();  // Stores every branch
    public static List<Food> FoodList = new ArrayList<Food>();  
	public static List<Staff> staffList = new ArrayList<Staff>();
	public static List<Admin> adminList;
	
	//This reads the file and returns the list of food
	public static List<Food> readFoodList(){
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
	
	//This reads the file and returns the list of staff
	public static List<Staff> readStaffList(){
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
}
