/**
 * @author xinyue
 * Created at 26/3/24 13:24
 * Last Modified at 24/4/24 02:26
 * Email: dmsxinyue@gmail.com
 * Package: src.order
 * @version 1.10.00 
 * Description: updated to allow dynamic manipulation of payment methods (from enum to list)
 */

package src.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Payment Class. Still, works the UI to payment, so methods are static.
 *
 * @author xinyue
 * Created at 26/3/24 13:24
 * Email: dmsxinyue@gmail.com
 * Package: src.order
 * @version 1.00.00
 */
public class Payment {
    private static List<String> paymentMethodsList = new ArrayList<>();

    static {
        //default payment methods
        paymentMethodsList.add("CREDIT_CARD");
        paymentMethodsList.add("CASH");
        paymentMethodsList.add("PAYPAL");
        paymentMethodsList.add("PAYNOW");
    }

    /**
     * Handles the process of payment. Return the payment status
     *
     * @param currentOrder the current order
     * @return true if payment is successful.
     */
    public static boolean processPayment(Order currentOrder) {
        Scanner sc = new Scanner(System.in);
        if (currentOrder == null) {
            System.out.println("Error: Order Not Assigned. Exiting...");
            return false;
        }

        double amount = calculate(currentOrder);
        System.out.println("Due Amount: S$ " + String.format("%.2f", amount));
        System.out.println("All possible payment method are:");
        for (int i = 0; i < paymentMethodsList.size(); i++) {
            System.out.println((i + 1) + " ：" + paymentMethodsList.get(i));
        }
        System.out.println("-----------------------------------\nPlease enter your choice:\n Enter -1 to cancel payment.");

        int choice = sc.nextInt();
        if (choice == -1) {
            System.out.println("Payment Cancelled.");
            return false;
        } else if (choice >= 1 && choice <= paymentMethodsList.size()) {
            System.out.println("Payment Through " + paymentMethodsList.get(choice - 1) + "...Processing...Completed");
        } else {
            System.out.println("Invalid Payment Method Selected.");
            return false;
        }

        currentOrder.setStatus(OrderStatus.PREPARING);
        return true;
    }

    /**
     * Gets All Payment methods.
     *
     * @return the payment methods
     */
    public static List<String> getPaymentMethods() {
        return new ArrayList<>(paymentMethodsList);
    }

    /**
     * Set new payment methods by a kust
     *
     * @param paymentMethods the payment methods
     */
    public static void setPaymentMethods(List<String> paymentMethods) {
        paymentMethodsList = new ArrayList<>(paymentMethods);
    }

    /**
     * Add new payment method.
     *
     * @param paymentMethod the payment method
     */
    public static void addPaymentMethod(String paymentMethod) {
        paymentMethodsList.add(paymentMethod);
    }

    /**
     * Remove a certain payment method.
     *
     * @param paymentMethod the payment method
     */
    public static void removePaymentMethod(String paymentMethod) {
        paymentMethodsList.remove(paymentMethod);
    }

    private static double calculate(Order currentOrder) {
        double amount = 0;
        for (OrderEntry oe : currentOrder.getFoodList()) {
            amount += oe.getQuantity() * oe.getFood().getPrice();
        }
        return amount;
    }

}

