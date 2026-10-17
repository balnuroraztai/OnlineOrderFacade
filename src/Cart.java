import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Map<String, Integer> items = new HashMap<>();

    public void addItem(String product, int quantity) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}