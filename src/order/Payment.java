package src.order;

/**
 * @author xinyue
 * Created at 26/3/24 13:24
 * Email: dmsxinyue@gmail.com
 * Package: src.order
 * @version 1.00.00
 */
public class Payment {

    private double amount;
    private String paymentMethod; //eg. credit card, PayPal


    public Payment(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    
    //assuming all payments are successful
    public void processPayment() {
        System.out.println("Payment of " + amount + " via " + paymentMethod + " has been processed successfully.");
    }

    //getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    //for testing
    public static void main(String[] args) {
        Payment payment = new Payment(100, "Credit Card");
        payment.processPayment(); 
    }
}
