// OrderItem.java
public class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    // Calculate the total cost for this specific order item (item price * quantity)
    public double getTotalCost() {
        return item.getPrice() * quantity;
    }

    // Optional: for easy printing of order items
    @Override
    public String toString() {
        return String.format("%-25s x %d = $%.2f", item.getName(), quantity, getTotalCost());
    }
}