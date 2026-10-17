public class Customer {

    private final String name;
    private final String email;

    public Customer(String name, String email) {
        if (name == null || name.isBlank() ||
                email == null || email.isBlank()) {
            throw new IllegalArgumentException("Invalid customer details");
        }

        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}