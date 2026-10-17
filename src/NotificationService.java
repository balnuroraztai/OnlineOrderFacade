public class NotificationService {

    public void send(Customer customer, String message) {
        System.out.println("Notification to " + customer.getEmail());
        System.out.println(message);
    }
}