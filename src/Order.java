public class Order {

    private final int id;
    private final Cart cart;
    private final Customer customer;
    private final int total;
    private String status;

    public Order(int id, Cart cart, Customer customer, int total) {
        this.id = id;
        this.cart = new Cart();

        for (var item : cart.getItems().entrySet()) {
            this.cart.addItem(item.getKey(), item.getValue());
        }

        this.customer = customer;
        this.total = total;
        this.status = "PLACED";
    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        Cart copy = new Cart();

        for (var item : cart.getItems().entrySet()) {
            copy.addItem(item.getKey(), item.getValue());
        }

        return copy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        status = "CANCELLED";
    }
}