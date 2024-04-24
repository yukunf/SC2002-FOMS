
package src.branch;


import src.order.Order;
import src.order.OrderStatus;

import java.util.Scanner;


import static src.App.orderList;


/**
 * The Staff Type
 */
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

	/**
	 * Instantiates a new Staff.
	 *
	 * @param name    the name
	 * @param loginID the login id
	 * @param gender  the gender
	 * @param age     the age
	 * @param branch  the branch
	 */
	public Staff(String name, String loginID, char gender, int age, Branch branch) {
		this.name=name;
		this.loginID=loginID;
		this.gender=gender;
		this.age=age;
		this.branch=branch;
		this.password = "password"; //default password
	}

	/**
	 * Search order by id order.
	 *
	 * @param id the id
	 * @return the order
	 */
	public Order searchOrderByID(int id){
		for(Order o : orderList){
			if(o.getOrderID() == id && o.getStatus().equals(OrderStatus.PREPARING) && o.getBranch().equals(branch.getBranchName()))return o;
		}
		return null;
	}

	/**
	 * Display orders.
	 */
	public void displayOrders() {
		System.out.println("The list of orders:");
		for (Order order : orderList) {	
		  if (order.getBranch().equals(branch.getBranchName()) && order.getStatus()==OrderStatus.PREPARING) {
			  System.out.println("OrderID: " + order.getOrderID());  
		  }
	    }
	}

	/**
	 * View order details, showing all information including the customization
	 */
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
		System.out.println("Branch: "+ orderObject.getBranch() + " \nDining Status: "+ diningStatus + " \nCurrent status: "+orderObject.getStatus());
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


	/**
	 * Process order. Handles the staff user interface to process a order.
	 */
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
					System.out.println("Order set to PREPARING successful!");
					break;
				case 2:
					orderList.get(currentID).setStatus(OrderStatus.READY);
					orderList.get(currentID).setTime(System.currentTimeMillis()); // Register Time
					System.out.println("Order set to READY successful!");
					break;
				case 3:
					orderList.get(currentID).setStatus(OrderStatus.CANCELLED);
					System.out.println("Order set to CANCELLED successful!");
					break;
				default: System.out.println("Invalid! Re-enter choice: ");
					break;
			}
		} while (choice!=1 && choice!=2  && choice!=3);
	}

	/**
	 * Gets staff name.
	 *
	 * @return the staff name
	 */
	public String getStaffName () {
			return name;
		}

	/**
	 * Change staff name.
	 *
	 * @param newname the newname
	 */
	public void changeStaffName (String newname){
			name = newname;
		}

	/**
	 * Change staff id.
	 *
	 * @param newID the new id
	 */
	public void changeStaffID (String newID){
			loginID = newID;

		}

	/**
	 * Change staffgender.
	 *
	 * @param newgender the newgender
	 */
	public void changeStaffgender (char newgender){
			this.gender = newgender;
		}

	/**
	 * Gets gender.
	 *
	 * @return the gender
	 */
	public char getGender() {
			return gender;
		}

	/**
	 * Gets age.
	 *
	 * @return the age
	 */
	public int getAge() {
			return age;
		}

	/**
	 * Change staffage.
	 *
	 * @param newage the newage
	 */
	public void changeStaffage (int newage){
			age = newage;
		}

	/**
	 * Change staffbranch.
	 *
	 * @param newbranch the newbranch
	 */
	public void changeStaffbranch (Branch newbranch){
			branch = newbranch;
		}

	/**
	 * Gets login id.
	 *
	 * @return the login id
	 */
	public String getLoginID () {
			return loginID;
		}

	/**
	 * Check password boolean.
	 *
	 * @param answer the answer
	 * @return the boolean
	 */
	public boolean checkPassword (String answer) {
			if (answer.equals(password)) {
				return true;
			}
			return false; 
		}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
			return password;
		}

	/**
	 * Set password.
	 *
	 * @param password the password
	 */
	public void setPassword (String password){  //For staff to change password
			this.password = password;
		}

	/**
	 * Sets role.
	 *
	 * @param role the role
	 */
	public void setRole(char role) {
			this.role=role;
		}

	/**
	 * Gets role.
	 *
	 * @return the role
	 */
	public char getRole() {
			 if (this instanceof Manager) {
			        return 'M'; // Representing the role of a Manager
			    } else if (this instanceof Admin) {
			        return 'A'; // Representing the role of an Admin
			    } else {
			        return 'S'; // Default role for Staff
			    }
		}

	/**
	 * Set login try counter.
	 */
	public void SetLoginTry () {
			this.loginTry++;
		}

	/**
	 * Gets login try counter.
	 *
	 * @return the login try
	 */
	public int getLoginTry () {
			return loginTry;
		}

	/**
	 * Gets branch the staff belongs to
	 *
	 * @return the branch
	 */
	public Branch getBranch() {
			return branch;
		}

	/**
	 * Load home page. The staff User Interface handler.
	 */
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
