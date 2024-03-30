package src.order;

import src.menu.Food;
import java.util.List;
public class Receipt {
	private String branch;
	private int orderID;
	private List<Food> currentOrder;
	private double totalCost;
	public Receipt(String branch, int orderID, List<Food> currentOrder, double totalCost ) {
		this.branch = branch;
		this.orderID = orderID;
		this.currentOrder = currentOrder;
		this.totalCost = totalCost;
	}
	public static void printReceipt(String branch, int orderID, List<Food >currentOrder, double totalCost) {
		System.out.println("======== Receipt ========");
		System.out.println("Branch: " + branch);
		System.out.println("OrderID: " + orderID);
		System.out.println();
		for(Food food: currentOrder) {
			System.out.println(food.getName() + " $" + String.format("%.2f", food.getPrice()));
		}
		System.out.println();
		System.out.println("Total: " + "$" + String.format("%.2f", totalCost));
		System.out.println("=========================");
}

}