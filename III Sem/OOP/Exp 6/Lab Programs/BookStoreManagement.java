import java.io.*;
import java.util.*;

// User-defined Exception for invalid book ID
class InvalidBookIdException extends Exception {
    public InvalidBookIdException(String message) {
        super(message);
    }
}

// User-defined Exception for unavailable books
class BookUnavailableException extends Exception {
    public BookUnavailableException(String message) {
        super(message);
    }
}

// Book Class
class Book {
    int id;
    String title;
    boolean available;

    public Book(int id, String title, boolean available) {
        this.id = id;
        this.title = title;
        this.available = available;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + (available ? "Available" : "Unavailable");
    }
}

// BookStore Class (with Exception Handling and File I/O)
class BookStore {
    private List<Book> inventory = new ArrayList<>();

    public void addBook(Book b) {
        inventory.add(b);
    }

    public void checkBook(int id) throws InvalidBookIdException, BookUnavailableException {
        boolean found = false;
        for (Book b : inventory) {
            if (b.id == id) {
                found = true;
                if (!b.available) {
                    throw new BookUnavailableException("Book ID " + id + " is not available.");
                }
                System.out.println("Book found: " + b.title + " is available.");
                return;
            }
        }
        if (!found) {
            throw new InvalidBookIdException("Invalid Book ID: " + id);
        }
    }

    public void saveInventoryToFile(String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book b : inventory) {
                bw.write(b.toString());
                bw.newLine();
            }
            System.out.println("Inventory successfully saved to file.");
        }
    }

    public void loadInventoryFromFile(String filename) throws IOException {
        inventory.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                inventory.add(new Book(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2].equalsIgnoreCase("Available")
                ));
            }
            System.out.println("Inventory loaded from file successfully.");
        }
    }
}

// Thread class for simulating concurrent access
class BookCheckThread extends Thread {
    private BookStore store;
    private int bookId;

    public BookCheckThread(BookStore store, int bookId) {
        this.store = store;
        this.bookId = bookId;
    }

    @Override
    public void run() {
        try {
            store.checkBook(bookId);
        } catch (InvalidBookIdException | BookUnavailableException e) {
            System.err.println("Thread " + Thread.currentThread().getName() + ": " + e.getMessage());
        }
    }
}

// Main class
public class BookStoreManagement {
    public static void main(String[] args) {
        BookStore store = new BookStore();

        store.addBook(new Book(1, "Cybersecurity Essentials", true));
        store.addBook(new Book(2, "Java Programming", false));
        store.addBook(new Book(3, "Data Structures", true));

        try {
            store.saveInventoryToFile("books.txt");
            store.loadInventoryFromFile("books.txt");

            Thread t1 = new BookCheckThread(store, 1);
            Thread t2 = new BookCheckThread(store, 2);
            Thread t3 = new BookCheckThread(store, 5); // Invalid ID

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

        } catch (IOException e) {
            System.err.println("File I/O Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        } finally {
            System.out.println("BookStore operation completed.");
        }
    }
}
