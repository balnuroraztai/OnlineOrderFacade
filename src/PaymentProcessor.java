import java.util.HashMap;
import java.util.Map;

public class PaymentProcessor {

    private final Map<Integer, Integer> payments = new HashMap<>();

    public void pay(int orderId, int amount, Customer customer) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }

        if (customer.getEmail().equals("declined@example.com")) {
            throw new IllegalStateException("Payment declined");
        }

        if (payments.containsKey(orderId)) {
            throw new IllegalStateException("Order already paid");
        }

        payments.put(orderId, amount);

        System.out.println("Payment successful: " + amount + " KZT");
    }

    public void refund(int orderId) {
        Integer amount = payments.remove(orderId);

        if (amount != null) {
            System.out.println("Refund completed: " + amount + " KZT");
        }
    }
}