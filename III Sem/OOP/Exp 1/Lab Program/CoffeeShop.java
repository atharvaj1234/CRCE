// CoffeeShop.java
import java.util.Scanner;
import java.util.ArrayList; // Using ArrayList for dynamic order items
import java.util.Arrays;    // For fixed menu array

public class CoffeeShop {

    // Fixed Menu Items (using an array)
    private MenuItem[] menu;
    private double taxRate;
    private double discountPercentage; // Global discount for simplicity

    public CoffeeShop(double taxRate, double discountPercentage) {
        this.taxRate = taxRate;
        this.discountPercentage = discountPercentage;
        initializeMenu();
    }

    private void initializeMenu() {
        menu = new MenuItem[] {
            new MenuItem("Espresso", 2.50),
            new MenuItem("Latte", 4.00),
            new MenuItem("Cappuccino", 3.75),
            new MenuItem("Americano", 3.00),
            new MenuItem("Muffin", 2.75),
            new MenuItem("Croissant", 3.25),
            new MenuItem("Orange Juice", 2.00)
        };
    }

    public void displayMenu() {
        System.out.println("\n--- Our Menu ---");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].toString());
        }
        System.out.println("----------------\n");
    }

    // Helper to find a MenuItem by its name (case-insensitive)
    public MenuItem findMenuItem(String itemName) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Not found
    }

    // Helper to find a MenuItem by its index (from displayed menu)
    public MenuItem getMenuItemByIndex(int index) {
        if (index >= 0 && index < menu.length) {
            return menu[index];
        }
        return null;
    }

    public void processOrder() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<OrderItem> customerOrder = new ArrayList<>(); // Using ArrayList for order, more flexible than fixed array
        double subtotal = 0;

        System.out.println("Welcome to the Coffee Shop!");
        displayMenu();

        System.out.println("Enter your order (type item number and quantity, e.g., '1 2' for 2 Espressos).");
        System.out.println("Type 'done' when finished.");

        while (true) {
            System.out.print("Order item (number quantity or 'done'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    int itemNumber = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);

                    if (quantity <= 0) {
                        System.out.println("Quantity must be positive. Please try again.");
                        continue;
                    }

                    MenuItem selectedItem = getMenuItemByIndex(itemNumber - 1); // -1 because menu is 0-indexed
                    if (selectedItem != null) {
                        OrderItem orderItem = new OrderItem(selectedItem, quantity);
                        customerOrder.add(orderItem);
                        subtotal += orderItem.getTotalCost();
                        System.out.println("Added: " + orderItem.toString());
                    } else {
                        System.out.println("Invalid item number. Please refer to the menu.");
                    }
                } else {
                    System.out.println("Invalid input format. Please enter 'item_number quantity'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid numbers for item and quantity.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        if (customerOrder.isEmpty()) {
            System.out.println("No items ordered. Order cancelled.");
            // scanner.close(); // Close scanner here if no receipt is generated
            return;
        }

        generateReceipt(customerOrder, subtotal);
        scanner.close(); // Close scanner once all input is done
    }

    private void generateReceipt(ArrayList<OrderItem> order, double subtotal) {
        System.out.println("\n\n--- Your Receipt ---");
        System.out.println("Items Ordered:");
        for (OrderItem item : order) {
            System.out.println("  " + item.getItem().getName() + " x " + item.getQuantity() +
                               " @ $" + String.format("%.2f", item.getItem().getPrice()) +
                               " = $" + String.format("%.2f", item.getTotalCost()));
        }
        System.out.println("--------------------");

        System.out.printf("Subtotal: $%.2f%n", subtotal);

        double discountAmount = subtotal * discountPercentage;
        System.out.printf("Discount (%.0f%%): -$%.2f%n", (discountPercentage * 100), discountAmount);

        double subtotalAfterDiscount = subtotal - discountAmount;
        System.out.printf("Subtotal after discount: $%.2f%n", subtotalAfterDiscount);

        double taxAmount = subtotalAfterDiscount * taxRate;
        System.out.printf("Tax (%.1f%%): +$%.2f%n", (taxRate * 100), taxAmount);

        double finalTotal = subtotalAfterDiscount + taxAmount;
        System.out.printf("Final Total: $%.2f%n", finalTotal);

        System.out.println("--------------------");
        System.out.println("Thank you for your order!");
    }

    public static void main(String[] args) {
        // Initialize CoffeeShop with a 8% tax rate and 10% discount
        CoffeeShop myCoffeeShop = new CoffeeShop(0.08, 0.10);
        myCoffeeShop.processOrder();
    }
}