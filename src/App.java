package src;

import src.branch.Branch;
import java.util.Scanner;
import src.order.Order;
import src.order.OrderSystem;

import java.util.List;

/**
 * @author fengyukun
 * Created at 21/3/24 17:11
 * Email : @author fengyukufyk@sina.com
 * Package : PACKAGE_NAME
 * @version 1.00.00
 **/
public class App {
    public static List<Branch> branchList;  // Stores every branch
    public static List<Order> orderList;  // Stores every order;
    public static void main(String[] args) {
    	int opt;
    	Scanner sc = new Scanner(System.in);
        System.out.println("Select a domain:");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Terminate");
        do {
        	opt = sc.nextInt();
        	switch(opt) {
        	case 1:
        		OrderSystem.createNewOrder();
        		break;
        	case 2:
        		break;
        	case 3:
        		break;
        	default:
        		System.out.println("Invalid option");
        		break;
        	}
        	
        }while(opt != 3);
    }
}
