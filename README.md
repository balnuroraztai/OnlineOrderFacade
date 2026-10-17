# Online Order Processing — Facade Pattern

## Project Description

This project demonstrates the Facade Design Pattern in Java using an online order processing system.

The system allows customers to place and cancel orders. The Facade simplifies the ordering process by coordinating inventory, payment, shipping, and notification services.

## Design Pattern

**Facade Pattern** is a structural design pattern that provides a simple interface to a complex subsystem.

In this project, `OrderFacade` provides two main methods:

- `placeOrder(cart, customer)` — places a new order.
- `cancelOrder(orderId)` — cancels an existing order.

The client does not need to interact with the subsystem services directly.

## Project Structure

```text
OnlineOrderFacade/
├── src/
│   ├── Main.java
│   ├── Cart.java
│   ├── Customer.java
│   ├── Order.java
│   ├── OrderFacade.java
│   ├── InventoryService.java
│   ├── PaymentProcessor.java
│   ├── ShippingService.java
│   └── NotificationService.java
└── README.md
```

## Main Components

- **Main** — client that uses the Facade.
- **OrderFacade** — coordinates the order processing steps.
- **InventoryService** — checks, reserves, and releases stock.
- **PaymentProcessor** — processes payments and refunds.
- **ShippingService** — schedules and cancels shipping.
- **NotificationService** — displays notifications.
- **Cart** — stores products and quantities.
- **Customer** — stores customer information.
- **Order** — stores order details and status.

## How It Works

When a customer places an order:

1. The system calculates the total price.
2. InventoryService reserves the products.
3. PaymentProcessor processes the payment.
4. ShippingService schedules delivery.
5. NotificationService displays a confirmation message.

If a step fails, the Facade performs rollback operations to undo completed steps, such as releasing reserved stock and refunding a successful payment.

When an order is cancelled, the system cancels shipping, refunds the payment, and restores the stock.

## Features

- Place an order
- Cancel an order
- Calculate the total price
- Check available stock
- Process simulated payments
- Schedule simulated shipping
- Display customer notifications
- Handle failed payments with rollback

## Technologies

- Java 17
- IntelliJ IDEA
- Facade Design Pattern

## How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure Java 17 is selected.
3. Open `Main.java` in the `src` folder.
4. Run the `main()` method.
5. Check the console output.

## Example Scenarios

**Successful Order:** The customer orders a laptop and a mouse. Payment succeeds, shipping is scheduled, and the order is created.

**Order Cancellation:** The order is cancelled, the payment is refunded, and the reserved products are returned to stock.

**Failed Payment:** Payment is declined, and the reserved products are released using rollback.

## Clean Code Principles

The project follows these principles:

1. **Meaningful Names** — methods and classes have clear names.
2. **Single Responsibility** — each service has one main responsibility.
3. **Simplified Interface** — the client interacts with the Facade instead of individual services.
4. **Small Classes** — responsibilities are divided between separate classes.
5. **Error Handling** — exceptions and rollback operations handle failures.

## Conclusion

The Facade Pattern makes the online order processing system easier to use and maintain.

The `OrderFacade` hides the coordination of inventory, payment, shipping, and notification services. The subsystem classes remain independent and can also be used directly when needed.
