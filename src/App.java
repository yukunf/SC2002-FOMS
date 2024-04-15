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
import java.util.InputMismatchException;

import static java.lang.Thread.sleep;

/**
 * This Main Class holds global variables used across different domains of our program.
 * It also has a main method to run user interactions.
 * Two methods to initialize those variables with IO functions are also included.
 *
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
	private static Thread orderTimeTracker;

    private static boolean programIsTerminating = false;
    public static final int ORDER_EXPIRE_SECOND = 60 * 5; // 5 minutes. Can change this when testing.


    /*
     * Use this function to initialize everything when program starts
     * That is, all I/O functions, including reading Branch, Menu, Staff from .xls or .csv files.
     * It also runs a separate thread to monitor order time, setting them as cancelled once expired.
     * */
    public static void initialize() {
        // reading orderList;
        branchList = FileIO.readBranchList();
        foodList = FileIO.readFoodList();
        staffList = FileIO.readStaffList();
        adminList = FileIO.readAdminList();
        ManagerList = FileIO.readManagerList();
        allEmployeesList = FileIO.readAllEmployees();


        orderList = deserializeOrderList();

        List<Staff> branchStaff;
        List<Staff> branchManager;
        for (Branch branch : branchList) {
            branchStaff = new ArrayList<>();
            branchManager = new ArrayList<>();
            for (Staff employee : allEmployeesList) {
                if (branch.getBranchName().equals(employee.getBranch().getBranchName())) {
                    if (employee.getRole() == 'S') {
                        branchStaff.add(employee);
                    } else if (employee.getRole() == 'M') {
                        branchManager.add(employee);
                    }
                }
            }
            branch.setStaffList(branchStaff);
            branch.setmanagerlist(branchManager);
        }

        // This thread automatically check every order's expiry time.
        orderTimeTracker = new Thread(() -> {
            while (!programIsTerminating) {
                try {
                    sleep(5 * 1000); // Check every 5 seconds
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Order o : orderList) {
                    if (System.currentTimeMillis() - o.getTime() > ORDER_EXPIRE_SECOND * 1000
                            && o.getStatus() == OrderStatus.READY) {
                        o.setStatus(OrderStatus.CANCELLED);
                    }
                }
            }
        });
        orderTimeTracker.start();

        //Order.setOrderIDCounter(orderList.get(orderList.size() - 1).getOrderID() + 1);
        // Set the counter 1 more than the biggest existing orderID
    }

    /*
     * This function serialize OrderList to .ser files
     *
     * @serialData the OrderList
     * */
    private static void serializeOrderList() {

        try {
            FileOutputStream fos = new FileOutputStream(orderFilePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(orderList);
            outputStream.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        Order.setOrderIDCounter(orderList.size() + 1);
    }

    /*
     * This function deserialize .ser files to  OrderList
     *
     * @serialData the OrderList
     * */
    private static List<Order> deserializeOrderList() {
        List<Order> ol = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(orderFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            Object o = inputStream.readObject();
            if (o == null) {
                inputStream.close();
                return ol;
            } else {
                ol = (ArrayList<Order>) o;
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            return ol;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return ol;
    }

    /*
     * Use this function to do something before program ends
     * That is, all I/O functions, including saving Branch, Menu, Staff to .xls or .csv files.
     * */
    public static void deinitialize() {
        serializeOrderList();
        programIsTerminating = true;// This will stop another thread.
    }


    public static void customerDriver() {
        System.out.println("Select an option:");
        System.out.println("1. Create a new order");
        System.out.println("2. Check the status of an existing order");
        System.out.println("3. Cancel");
        int secondOpt = sc.nextInt();
        while (secondOpt != 3) {
            if (secondOpt == 1) {
                //OrderSystem cannot be static because everytime you create a new order, it will just add on the previous order.
                OrderSystem os = new OrderSystem();
                os.createNewOrder();
                break;
            } else if (secondOpt == 2) {
                OrderSystem.checkOrderStatus();
                break;
            } else System.out.println("Invalid Option");
            secondOpt = sc.nextInt();
        }
    }

    public static void staffDriver() {  //May want to use a HashMap for constant look up time
        String input;

        int option;
        Staff loggedInStaff = null;

        do {
            loggedInStaff = null;
            System.out.println("LoginID:");
            System.out.println("Enter 'q' to exit");
            input = sc.next();
            if (input.equals("q")) {
                break;
            }
            for (Staff staff : allEmployeesList) {
                if (input.equals(staff.getLoginID())) {
                    loggedInStaff = staff;
                    break;
                }
            }
            if (loggedInStaff != null) {
                System.out.println("Password:");
                input = sc.next();
                if (input.equals(loggedInStaff.getPassword())) {
                    System.out.println();
                    System.out.println("Login successful, " + loggedInStaff.getStaffName());

                    // reset password if first successful login
                    if (loggedInStaff.getLoginTry() == 1 && loggedInStaff.getPassword().equals("password")) {
                        System.out.println("Input new password: ");
                        String newPassword = sc.next();
                        loggedInStaff.setPassword(newPassword);
                        System.out.println("Password updated succesfully.");
                        System.out.println();
                        loggedInStaff.SetLoginTry();
                    }

                    //Proceed to staff page
                    char role = loggedInStaff.getRole();
                    switch (role) {
                        case 'S':
                            loggedInStaff.loadHomePage();
                            break;
                        case 'M':
                            loggedInStaff.loadHomePage();
                            break;
                        case 'A':
                            loggedInStaff.loadHomePage();
                            break;
                    }
                } else {
                    System.out.println("Wrong password!");
                    System.out.println();
                }
            } else {
                if (!input.equals("q")) {
                    System.out.println("Invalid LoginID!");
                    System.out.println();
                }
            }
        } while (!input.equals("q"));

    }


    public static void main(String[] args) {


        initialize();
        for (Order o: orderList){
            System.out.println(o.getOrderID());
        }
        int opt = 0;
        do {
            System.out.println("Select a domain:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Terminate");
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.nextLine(); //clear input buffer
                continue;
            }
            switch (opt) {
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
        } while (opt != 3);

        deinitialize();
    }
}