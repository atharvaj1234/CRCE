// CoffeeShop.java
import java.util.Scanner;
import java.util.ArrayList;

public class CoffeeShop {

    private String[] menuNames = {
        "Espresso", "Latte", "Cappuccino", "Americano",
        "Muffin", "Croissant", "Orange Juice"
    };
    private double[] menuPrices = {
        2.50, 4.00, 3.75, 3.00,
        2.75, 3.25, 2.00
    };

    private double taxRate;
    private double discountPercentage;

    public CoffeeShop(double taxRate, double discountPercentage) {
        this.taxRate = taxRate;
        this.discountPercentage = discountPercentage;
    }

    private void displayMenu() {
        System.out.println("\n--- Our Menu ---");
        for (int i = 0; i < menuNames.length; i++) {
            System.out.printf("%d. %s - $%.2f%n", (i + 1), menuNames[i], menuPrices[i]);
        }
        System.out.println("----------------\n");
    }

    public void processOrder() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> orderItems = new ArrayList<>();   // store menu indexes
        ArrayList<Integer> orderQuantities = new ArrayList<>(); // store corresponding quantities
        double subtotal = 0;

        System.out.println("Welcome to the Coffee Shop!");
        displayMenu();

        System.out.println("Enter your order (e.g., '1 2' for 2 Espressos).");
        System.out.println("Type 'done' when finished.");

        while (true) {
            System.out.print("Order item (number quantity or 'done'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) break;

            try {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    int itemNumber = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);

                    if (itemNumber < 1 || itemNumber > menuNames.length) {
                        System.out.println("Invalid item number. Check the menu.");
                        continue;
                    }
                    if (quantity <= 0) {
                        System.out.println("Quantity must be positive. Try again.");
                        continue;
                    }

                    int index = itemNumber - 1;
                    orderItems.add(index);
                    orderQuantities.add(quantity);

                    double cost = menuPrices[index] * quantity;
                    subtotal += cost;

                    System.out.printf("Added: %s x %d = $%.2f%n",
                            menuNames[index], quantity, cost);
                } else {
                    System.out.println("Invalid format. Use 'item_number quantity'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Use numbers for item and quantity.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (orderItems.isEmpty()) {
            System.out.println("No items ordered. Order cancelled.");
            return;
        }

        generateReceipt(orderItems, orderQuantities, subtotal);
        scanner.close();
    }

    private void generateReceipt(ArrayList<Integer> orderItems,
                                 ArrayList<Integer> orderQuantities,
                                 double subtotal) {
        System.out.println("\n\n--- Your Receipt ---");
        System.out.println("Items Ordered:");

        for (int i = 0; i < orderItems.size(); i++) {
            int index = orderItems.get(i);
            int qty = orderQuantities.get(i);
            double cost = menuPrices[index] * qty;

            System.out.printf("  %s x %d @ $%.2f = $%.2f%n",
                    menuNames[index], qty, menuPrices[index], cost);
        }
        System.out.println("--------------------");

        System.out.printf("Subtotal: $%.2f%n", subtotal);

        double discountAmount = subtotal * discountPercentage;
        System.out.printf("Discount (%.0f%%): -$%.2f%n", discountPercentage * 100, discountAmount);

        double subtotalAfterDiscount = subtotal - discountAmount;
        System.out.printf("Subtotal after discount: $%.2f%n", subtotalAfterDiscount);

        double taxAmount = subtotalAfterDiscount * taxRate;
        System.out.printf("Tax (%.1f%%): +$%.2f%n", taxRate * 100, taxAmount);

        double finalTotal = subtotalAfterDiscount + taxAmount;
        System.out.printf("Final Total: $%.2f%n", finalTotal);

        System.out.println("--------------------");
        System.out.println("Thank you for your order!");
    }

    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop(0.08, 0.10);
        shop.processOrder();
    }
}
