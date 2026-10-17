import java.util.HashSet;
import java.util.Set;

public class ShippingService {

    private final Set<Integer> shipments = new HashSet<>();

    public void schedule(int orderId) {
        if (!shipments.add(orderId)) {
            throw new IllegalStateException("Shipping already scheduled");
        }

        System.out.println("Shipping scheduled for order #" + orderId);
    }

    public void cancel(int orderId) {
        if (shipments.remove(orderId)) {
            System.out.println("Shipping cancelled for order #" + orderId);
        }
    }
}