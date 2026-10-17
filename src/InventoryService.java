import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Integer> prices = new HashMap<>();

    public InventoryService() {
        stock.put("Laptop", 5);
        stock.put("Mouse", 10);
        stock.put("Keyboard", 8);

        prices.put("Laptop", 300000);
        prices.put("Mouse", 10000);
        prices.put("Keyboard", 25000);
    }

    public int calculateTotal(Cart cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        int total = 0;

        for (Map.Entry<String, Integer> item : cart.getItems().entrySet()) {
            String product = item.getKey();
            int quantity = item.getValue();

            if (!prices.containsKey(product)) {
                throw new IllegalArgumentException("Unknown product: " + product);
            }

            total += prices.get(product) * quantity;
        }

        return total;
    }

    public void reserve(Cart cart) {
        Map<String, Integer> items = cart.getItems();

        for (Map.Entry<String, Integer> item : items.entrySet()) {
            String product = item.getKey();
            int quantity = item.getValue();

            if (stock.getOrDefault(product, 0) < quantity) {
                throw new IllegalStateException("Not enough stock: " + product);
            }
        }

        for (Map.Entry<String, Integer> item : items.entrySet()) {
            String product = item.getKey();
            int quantity = item.getValue();

            stock.put(product, stock.get(product) - quantity);
        }
    }

    public void release(Map<String, Integer> items) {
        for (Map.Entry<String, Integer> item : items.entrySet()) {
            String product = item.getKey();
            int quantity = item.getValue();

            stock.put(product, stock.getOrDefault(product, 0) + quantity);
        }
    }

    public int getStock(String product) {
        return stock.getOrDefault(product, 0);
    }
}