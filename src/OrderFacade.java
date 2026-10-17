import java.util.HashMap;
import java.util.Map;

public class OrderFacade {

    private final InventoryService inventory;
    private final PaymentProcessor payment;
    private final ShippingService shipping;
    private final NotificationService notification;

    private final Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    public OrderFacade() {
        inventory = new InventoryService();
        payment = new PaymentProcessor();
        shipping = new ShippingService();
        notification = new NotificationService();
    }

    public Order placeOrder(Cart cart, Customer customer) {
        if (cart == null || customer == null) {
            throw new IllegalArgumentException("Cart and customer are required");
        }

        int total = inventory.calculateTotal(cart);

        inventory.reserve(cart);

        int orderId = nextId++;
        boolean paid = false;

        try {
            payment.pay(orderId, total, customer);
            paid = true;

            shipping.schedule(orderId);

            Order order = new Order(orderId, cart, customer, total);
            orders.put(orderId, order);

            notification.send(customer,
                    "Order #" + orderId + " placed. Total: " + total + " KZT");

            return order;

        } catch (RuntimeException error) {
            shipping.cancel(orderId);

            if (paid) {
                payment.refund(orderId);
            }

            inventory.release(cart.getItems());

            throw new IllegalStateException(
                    "Order failed: " + error.getMessage(), error);
        }
    }

    public void cancelOrder(int orderId) {
        Order order = orders.get(orderId);

        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        if (order.getStatus().equals("CANCELLED")) {
            throw new IllegalStateException("Order already cancelled");
        }

        shipping.cancel(orderId);
        payment.refund(orderId);
        inventory.release(order.getCart().getItems());

        order.cancel();

        notification.send(order.getCustomer(),
                "Order #" + orderId + " cancelled");
    }

    public int getAvailableStock(String product) {
        return inventory.getStock(product);
    }
}