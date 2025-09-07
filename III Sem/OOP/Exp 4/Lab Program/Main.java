import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Data Structure Demonstration ---");

        // 1. Array: Employee Database Management
        System.out.println("\n===== 1. Employee Salary Management (Array) =====");
        employeeSalaryManagement();

        // 2. ArrayList: Product Database Management
        System.out.println("\n===== 2. Product Inventory Management (ArrayList) =====");
        productInventoryManagement();

        // 3. Vector: Library Management System
        System.out.println("\n===== 3. Library Management System (Vector) =====");
        libraryManagementSystem();

        scanner.close(); // Close the scanner when all operations are done
        System.out.println("\n--- Demonstration Complete ---");
    }

    // --- 1. Array: Employee Salary Management ---
    public static void employeeSalaryManagement() {
        // Store Salary of 5 Employees using an array.
        double[] salaries = new double[5]; // Array to store 5 salaries

        System.out.println("Please enter the salaries for 5 employees:");
        for (int i = 0; i < salaries.length; i++) {
            System.out.print("Enter salary for Employee " + (i + 1) + ": $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a numeric salary.");
                scanner.next(); // Consume invalid input
                System.out.print("Enter salary for Employee " + (i + 1) + ": $");
            }
            salaries[i] = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
        }

        System.out.println("\n--- Employee Salary Report ---");
        // Display all salaries
        System.out.print("All Salaries: [");
        for (int i = 0; i < salaries.length; i++) {
            System.out.printf("$%.2f", salaries[i]);
            if (i < salaries.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Find highest, lowest, and average Salary
        if (salaries.length == 0) {
            System.out.println("No salaries to process.");
            return;
        }

        double highestSalary = salaries[0];
        double lowestSalary = salaries[0];
        double totalSalary = 0;

        for (double salary : salaries) {
            if (salary > highestSalary) {
                highestSalary = salary;
            }
            if (salary < lowestSalary) {
                lowestSalary = salary;
            }
            totalSalary += salary;
        }

        double averageSalary = totalSalary / salaries.length;

        System.out.printf("Highest Salary: $%.2f%n", highestSalary);
        System.out.printf("Lowest Salary: $%.2f%n", lowestSalary);
        System.out.printf("Average Salary: $%.2f%n", averageSalary);
        System.out.println("------------------------------------");
    }

    // --- 2. ArrayList: Product Database Management ---
    public static void productInventoryManagement() {
        ArrayList<String> productList = new ArrayList<>();
        productList.add("Laptop");
        productList.add("Mouse");
        productList.add("Keyboard");
        productList.add("Monitor");

        int choice;
        do {
            System.out.println("\nProduct Management Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Change Product Name");
            System.out.println("4. Display All Products");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name to add: ");
                    String newProduct = scanner.nextLine();
                    if (!newProduct.trim().isEmpty()) {
                        productList.add(newProduct);
                        System.out.println("'" + newProduct + "' added successfully.");
                    } else {
                        System.out.println("Product name cannot be empty.");
                    }
                    break;
                case 2:
                    if (productList.isEmpty()) {
                        System.out.println("No products to remove.");
                        break;
                    }
                    System.out.print("Enter product name to remove: ");
                    String productToRemove = scanner.nextLine();
                    if (productList.remove(productToRemove)) {
                        System.out.println("'" + productToRemove + "' removed successfully.");
                    } else {
                        System.out.println("'" + productToRemove + "' not found in the list.");
                    }
                    break;
                case 3:
                    if (productList.isEmpty()) {
                        System.out.println("No products to change.");
                        break;
                    }
                    System.out.print("Enter current product name to change: ");
                    String oldName = scanner.nextLine();
                    int index = productList.indexOf(oldName);
                    if (index != -1) {
                        System.out.print("Enter new product name: ");
                        String newName = scanner.nextLine();
                        if (!newName.trim().isEmpty()) {
                            productList.set(index, newName);
                            System.out.println("Product name changed from '" + oldName + "' to '" + newName + "'.");
                        } else {
                            System.out.println("New product name cannot be empty.");
                        }
                    } else {
                        System.out.println("'" + oldName + "' not found in the list.");
                    }
                    break;
                case 4:
                    displayProducts(productList);
                    break;
                case 5:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private static void displayProducts(ArrayList<String> products) {
        if (products.isEmpty()) {
            System.out.println("Product list is empty.");
            return;
        }
        System.out.println("\n--- Current Products ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        System.out.println("------------------------");
    }


    // --- 3. Vector: Library Management System ---

    // Inner class to represent a Book
    static class Book {
        String bookId;
        String name;
        double price;

        public Book(String bookId, String name, double price) {
            this.bookId = bookId;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Name: %-25s | Price: $%.2f", bookId, name, price);
        }
    }

    public static void libraryManagementSystem() {
        Vector<Book> library = new Vector<>();
        // Add some initial books
        library.add(new Book("B001", "The Lord of the Rings", 25.99));
        library.add(new Book("B002", "Pride and Prejudice", 12.50));
        library.add(new Book("B003", "1984", 15.00));

        int choice;
        do {
            System.out.println("\nLibrary Management Menu:");
            System.out.println("1. Add New Book");
            System.out.println("2. Search Book by Name");
            System.out.println("3. Display All Books");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Book Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Book Price: $");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a numeric price.");
                        scanner.next(); // Consume invalid input
                        System.out.print("Enter Book Price: $");
                    }
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    if (!id.trim().isEmpty() && !name.trim().isEmpty() && price > 0) {
                        library.add(new Book(id, name, price));
                        System.out.println("Book '" + name + "' added successfully.");
                    } else {
                        System.out.println("Invalid book details. ID, Name cannot be empty, Price must be positive.");
                    }
                    break;
                case 2:
                    System.out.print("Enter book name to search: ");
                    String searchName = scanner.nextLine();
                    boolean found = false;
                    for (Book book : library) {
                        if (book.name.equalsIgnoreCase(searchName)) { // Case-insensitive search
                            System.out.println("Book Found: " + book);
                            found = true;
                            break; // Stop after finding the first match
                        }
                    }
                    if (!found) {
                        System.out.println("Book '" + searchName + "' not found in the library.");
                    }
                    break;
                case 3:
                    displayBooks(library);
                    break;
                case 4:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
    }

    private static void displayBooks(Vector<Book> books) {
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
            return;
        }
        System.out.println("\n--- All Books in Library ---");
        System.out.println(String.format("%-10s | %-25s | %s", "Book ID", "Book Name", "Price"));
        System.out.println("---------------------------------------------------------------");
        for (Book book : books) {
            System.out.println(String.format("%-10s | %-25s | $%.2f", book.bookId, book.name, book.price));
        }
        System.out.println("---------------------------------------------------------------");
    }
}