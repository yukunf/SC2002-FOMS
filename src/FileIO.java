package src;


import java.util.List;
import java.util.ArrayList;


import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import src.branch.Admin;
import src.branch.Branch;
import src.branch.Staff;
import src.branch.Manager;
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
				Food food = new Food(data[0], Double.parseDouble(data[1]), data[2], FoodCategory.valueOf(data[3].toUpperCase()), Boolean.parseBoolean(data[4]));
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
				if(data[2].equals("S")) {
				Branch b= findBranchByName(data[5]);
				Staff staff = new Staff(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), b);
				staffList.add(staff);
				}

			}
		}
		catch(FileNotFoundException f) {
			System.out.println("Invalid file");
		}
		return staffList;
	}

	//This reads the file and returns the list of all managers
	public static List<Manager> readManagerList(){
		List<Manager> ManagerList = new ArrayList<Manager>();
		try {
			File staffCsv = new File("staff_list.csv");
			Scanner sc = new Scanner(staffCsv);
			sc.nextLine(); //skip the header row
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String data[] = line.split(",");
				if(data.length == 0) break;
				if(data[2].equals("M")) {
					Branch b= findBranchByName(data[5]);
					Manager manager = new Manager(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), b);
					ManagerList.add(manager);
				}

			}
		}
		catch(FileNotFoundException f) {
			System.out.println("Invalid file");
		}
		return ManagerList;
	}
	
	//Stores everyone into this list including the admin
	public static List<Staff> readAllEmployees(){
		List<Staff> employeeList = new ArrayList<Staff>();
		try {
			File staffCsv = new File("staff_list.csv");
			Scanner sc = new Scanner(staffCsv);
			sc.nextLine(); //skip the header row
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String data[] = line.split(",");
				if(data.length == 0) break;
				if(data[2].equals("M")) {
					Branch b= findBranchByName(data[5]);
					Staff manager = new Manager(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), b);
					manager.setPassword(data[6]);
					employeeList.add(manager);
				}
				else if(data[2].equals("S")){
					Branch b= findBranchByName(data[5]);
					Staff staff = new Staff(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), b);
					staff.setPassword(data[6]);
					employeeList.add(staff); 
				}
				else {
					Branch b= new Branch("NA","NA");
					Staff admin = new Admin(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), b);
					admin.setPassword(data[6]);
					employeeList.add(admin);
				}

			}
		}
		catch(FileNotFoundException f) {
			System.out.println("Invalid file");
		}
		return employeeList;
	}


	public static Branch findBranchByName(String name){
		for(Branch b: App.branchList){
			if(b.getBranchName().equals(name))return b;
		}
		return null;
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
			Branch branch = new Branch(data[0],data[1]);
			branch.setStaffQuota(Integer.parseInt(data[2]));
			branch.setstate(Boolean.parseBoolean(data[3]));
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
				Branch b=new Branch("na","na");
				Admin admin = new Admin(data[0], data[1], data[3].charAt(0), Integer.parseInt(data[4]), b);
				adminList.add(admin);

			}
		}
	}
	catch(FileNotFoundException f) {
		System.out.println("Invalid file");
	}
	return adminList;

	}



	public static void writeToStaff(String filename, List<Staff> dataStaff){
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(filename));
			out.println("Name,Staff Login,Role,Gender,Age,Branch,Password");
			for(int i = 0; i<dataStaff.size(); i++) {
				String branch = dataStaff.get(i).getBranch().getBranchName();
				if(dataStaff.get(i).getRole() == 'A') branch = ""; 
				out.println(dataStaff.get(i).getStaffName() + "," + dataStaff.get(i).getLoginID() + "," + dataStaff.get(i).getRole() + "," + dataStaff.get(i).getGender() + "," + dataStaff.get(i).getAge() + "," + branch +"," + dataStaff.get(i).getPassword() + ",");
			}
		}
		catch(IOException e) {
			System.out.println("Error writing back to file");
		}
		finally {
			if(out != null)
			out.close();
		}
		
	}
	
	public static void writeToMenu(String filename, List<Food> dataFood) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(filename));
			out.println("Name,Price,Branch,Category,Availability");
			for(int i = 0; i<dataFood.size(); i++) {
				out.println(dataFood.get(i).getName() + "," + String.format("%.2f", dataFood.get(i).getPrice()) + ","
						+ dataFood.get(i).getBranch() + "," +
						dataFood.get(i).getCategory().toString().toLowerCase()+","+dataFood.get(i).isAvailability());
			}
		}
		catch(IOException e) {
			System.out.println("Error writing back to file");
			e.printStackTrace();
		}
		finally {
			if(out != null) out.close();
		}
	}
	public static void writeToBranch(String filename, List<Branch> branchList ){
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(filename));
			out.println("Name,Location,Staff Quota");
			for(Branch b : branchList){
				out.println(b.getBranchName()+","+b.getLocation()+","+b.getStaffQuota()+","+b.getState().toString());
			}
		}
		catch(IOException e) {
			System.out.println("Error writing back to file");
			e.printStackTrace();
		}
		finally {
			if(out != null) out.close();
		}
	}



}
