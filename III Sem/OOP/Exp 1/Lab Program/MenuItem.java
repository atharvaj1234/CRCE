// MenuItem.java
public class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Optional: for easy printing of menu items
    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}