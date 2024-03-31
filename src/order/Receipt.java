package src.order;

import src.order.Order;
import src.menu.Food;
import java.util.List;
public class Receipt {
	private String branch;
	private int orderID;
	private Order currentOrder;
	private double totalCost;
	public Receipt(String branch, int orderID, Order currentOrder, double totalCost ) {
		this.branch = branch;
		this.orderID = orderID;
		this.currentOrder = currentOrder;
		this.totalCost = totalCost;
	}
	public static void printReceipt(Order currentOrder) {
		System.out.println("======== Receipt ========");
		System.out.println("Branch: " + currentOrder.getBranch());
		System.out.println("OrderID: " + currentOrder.getOrderID());
		if(currentOrder.getDiningStatus()) {
			System.out.println("Dining Status: Dine in");
		}
		else {
			System.out.println("Dining Status: Takeaway");
		}
		System.out.println();
		for(Food food: currentOrder.getFoodList()) {
			System.out.println(food.getName() + " $" + String.format("%.2f", food.getPrice()));
		}
		System.out.println();
		System.out.println("Total: " + "$" + String.format("%.2f", currentOrder.getTotalCost()));
		System.out.println("=========================");
}

}