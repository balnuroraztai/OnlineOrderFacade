public class Main {

    public static void main(String[] args) {

        OrderFacade facade = new OrderFacade();

        Customer customer = new Customer(
                "Aigerim", "aigerim@example.com");

        Cart cart = new Cart();
        cart.addItem("Laptop", 1);
        cart.addItem("Mouse", 1);

        System.out.println("=== SUCCESSFUL ORDER ===");

        Order order = facade.placeOrder(cart, customer);

        System.out.println("Order ID: " + order.getId());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Total: " + order.getTotal() + " KZT");
        System.out.println("Laptops left: "
                + facade.getAvailableStock("Laptop"));

        System.out.println("\n=== CANCEL ORDER ===");

        facade.cancelOrder(order.getId());

        System.out.println("Status: " + order.getStatus());
        System.out.println("Laptops restored: "
                + facade.getAvailableStock("Laptop"));

        System.out.println("\n=== FAILED PAYMENT ===");

        Customer failedCustomer = new Customer(
                "Dana", "declined@example.com");

        Cart failedCart = new Cart();
        failedCart.addItem("Mouse", 2);

        try {
            facade.placeOrder(failedCart, failedCustomer);
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }

        System.out.println("Mice after failed payment: "
                + facade.getAvailableStock("Mouse"));
    }
}