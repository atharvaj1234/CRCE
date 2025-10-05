// Main class demonstrating all three exceptions
public class PostLabBookExceptionDemo {

    // User-defined exception for invalid book ID
static class BookIdInvalid extends Exception {
    public BookIdInvalid(String message) {
        super(message);
    }
}

// User-defined exception for book not found
static class BookNotFound extends Exception {
    public BookNotFound(String message) {
        super(message);
    }
}

// User-defined exception for unavailable book
static class BookUnavailable extends Exception {
    public BookUnavailable(String message) {
        super(message);
    }
}

// Book class for testing
static class Book {
    int id;
    String title;
    boolean available;

    public Book(int id, String title, boolean available) {
        this.id = id;
        this.title = title;
        this.available = available;
    }
}

    // Method to check book details and raise user-defined exceptions
    static void checkBook(Book[] books, int id)
            throws BookIdInvalid, BookNotFound, BookUnavailable {

        // Check for invalid ID
        if (id <= 0) {
            throw new BookIdInvalid("Book ID cannot be zero or negative: " + id);
        }

        boolean found = false;
        for (Book b : books) {
            if (b.id == id) {
                found = true;
                if (!b.available) {
                    throw new BookUnavailable("Book '" + b.title + "' is currently unavailable.");
                } else {
                    System.out.println("Book Found: " + b.title + " (Available)");
                }
                break;
            }
        }

        // If book not found
        if (!found) {
            throw new BookNotFound("Book with ID " + id + " not found in the system.");
        }
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Java Programming", true),
            new Book(2, "Network Security", false),
            new Book(3, "Operating Systems", true)
        };

        int[] testIds = {1, 2, 5, -3};

        for (int id : testIds) {
            try {
                System.out.println("\nChecking Book ID: " + id);
                checkBook(books, id);
            } catch (BookIdInvalid e) {
                System.err.println("Error: " + e.getMessage());
            } catch (BookNotFound e) {
                System.err.println("Error: " + e.getMessage());
            } catch (BookUnavailable e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Check operation completed for Book ID: " + id);
            }
        }

        System.out.println("\nAll operations executed successfully.");
    }
}
