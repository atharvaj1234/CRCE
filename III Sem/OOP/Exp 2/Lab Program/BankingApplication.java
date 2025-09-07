import java.util.Scanner;

// 1. Account Class to encapsulate account data
class Account {
    private String accountNumber;
    private double balance;
    private String pin; // Storing PIN as String for simplicity; in real app, it would be hashed.

    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Authenticates the user with the provided PIN
    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    // Deposits money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f.%n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraws money from the account
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f.%n", amount);
            return true;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
    }
}

// Main BankingApplication class
public class BankingApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a single account for this simple simulation
        // Account Number: 12345
        // PIN: 1234
        // Initial Balance: $500.00
        Account userAccount = new Account("12345", "1234", 500.00);

        System.out.println("--- Welcome to Your Bank ---");
        System.out.println("Please insert your card (Account: " + userAccount.getAccountNumber() + ")");

        // Authentication Loop
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        boolean isAuthenticated = false;

        while (attempts < MAX_ATTEMPTS && !isAuthenticated) {
            System.out.print("Enter your 4-digit PIN: ");
            String enteredPin = scanner.nextLine();

            if (userAccount.authenticate(enteredPin)) {
                System.out.println("PIN accepted. Access granted!");
                isAuthenticated = true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. " + (MAX_ATTEMPTS - attempts) + " attempts remaining.");
            }
        }

        if (!isAuthenticated) {
            System.out.println("Too many incorrect PIN attempts. Card blocked. Please contact your bank.");
            scanner.close();
            return; // Exit the program if authentication fails
        }

        // Main Transaction Loop
        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: $");
                        if (scanner.hasNextDouble()) {
                            double depositAmount = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            userAccount.deposit(depositAmount);
                            System.out.printf("Current Balance: $%.2f%n", userAccount.getBalance());
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
                            userAccount.withdraw(withdrawAmount);
                            System.out.printf("Current Balance: $%.2f%n", userAccount.getBalance());
                        } else {
                            System.out.println("Invalid input. Please enter a numeric amount.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;
                    case 3:
                        System.out.printf("Your current balance is: $%.2f%n", userAccount.getBalance());
                        break;
                    case 4:
                        System.out.println("Thank you for banking with us. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number for your choice.");
                scanner.nextLine(); // Consume invalid input
                choice = 0; // Set to 0 to keep the loop running
            }

        } while (choice != 4);

        scanner.close();
    }
}