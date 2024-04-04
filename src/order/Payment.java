package src.order;

import java.util.Scanner;

/**
 * @author xinyue
 * Created at 26/3/24 13:24
 * Email: dmsxinyue@gmail.com
 * Package: src.order
 * @version 1.00.00
 */
public class Payment {
    public enum PaymentMethod {CREDIT_CARD,CASH,PAYPAL,PAYNOW}



    /*
    * Returns 1 if payment successful.
    *
    *
    * */
    public static boolean processPayment(Order currentOrder) {
        Scanner sc = new Scanner(System.in);
        if(currentOrder == null){
            System.out.println("Error: Order Not Assigned. Exiting...");
        }

        double amount = calculate(currentOrder);
        System.out.println("Due Amount: S$ "+amount);
        System.out.println("All possible payment method are:");
        for (int i = 0; i < PaymentMethod.values().length; i++) {
            System.out.println((i+1)+" ："+PaymentMethod.values()[i]);
        }
        System.out.println("-----------------------------------\nPlease enter your choice:\n Enter -1 to cancel payment.");
        switch (sc.nextInt()){
            default:
                return false;
            case 1:
                System.out.println("Payment Through Credit Card...Authorizing...Completed");
                break;
            case 2:
                System.out.println("Cash Payment.Please Proceed to Counter for Payment.");
                break;
            case 3:
                System.out.println("Payment Through Paypal...Please login in...Completed");
                break;
            case 4:
                System.out.println("Payment Through Paynow...Please scan the QR Code...Completed");
                break;
        }
        currentOrder.setStatus(OrderStatus.PREPARING);
        return true;
    }


    private static double calculate(Order currentOrder){
        double amount = 0;
        for(OrderEntry oe : currentOrder.getFoodList()){
            amount += oe.getQuantity() * oe.getFood().getPrice();
        }
        return amount;
    }



}
