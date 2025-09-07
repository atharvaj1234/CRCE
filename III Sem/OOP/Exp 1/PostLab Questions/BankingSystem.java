import java.util.Scanner; // Import the Scanner class for user input

public class BankingSystem {

    // Global variable to store the account balance.
    // In a real system, this would be stored in a database or a more complex object.
    private static double balance = 1000.00; // Starting balance

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount The amount to deposit. Must be positive.
     */
    public static void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // Add the amount to the balance
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount The amount to withdraw. Must be positive and not exceed the balance.
     */
    public static void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount; // Subtract the amount from the balance
                System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f%n", amount, balance);
            } else {
                System.out.println("Insufficient funds. Current balance: $" + String.format("%.2f", balance));
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    /**
     * Checks and returns the current account balance.
     *
     * @return The current account balance.
     */
    public static double checkBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("--- Welcome to Simple Banking System ---");

        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for choice
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: $");
                        if (scanner.hasNextDouble()) {
                            double depositAmount = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            deposit(depositAmount);
                        } else {
                            System.out.println("Invalid input. Please enter a numeric amount.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: $");
                        if (scanner.hasNextDouble()) {
                            double withdrawAmount = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            withdraw(withdrawAmount);
                        } else {
                            System.out.println("Invalid input. Please enter a numeric amount.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;
                    case 3:
                        System.out.printf("Current balance: $%.2f%n", checkBalance());
                        break;
                    case 4:
                        System.out.println("Thank you for using Simple Banking System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number for your choice.");
                scanner.nextLine(); // Consume invalid input to prevent infinite loop
                choice = 0; // Set choice to 0 to keep the loop running
            }

        } while (choice != 4); // Loop until the user chooses to exit

        scanner.close(); // Close the scanner
    }
}