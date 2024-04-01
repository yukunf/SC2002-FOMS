package src.order;

/**
 * @author xinyue
 * Created at 26/3/24 13:24
 * Email: dmsxinyue@gmail.com
 * Package: src.order
 * @version 1.00.00
 */
public class Payment {
    public enum PaymentMethod {CREDIT_CARD,CASH,PAYPAL,PAYNOW}
    private static double amount;
    private Order currentOrder;

//    public Payment(double amount, String paymentMethod) {
//        this.amount = amount;
//        this.paymentMethod = paymentMethod;
//    }

    
    //assuming all payments are successful
    public static void processPayment() {
        //System.out.println("Payment of " + "$" + String.format("%.2f", amount) + " via " + paymentMethod + " has been processed successfully.");
    }

    //getters and setters
    public static double getAmount() {
        return amount;
    }

    public static void calculate(){
        // TODO calculate amount by order
    }


//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }

    //for testing

}
