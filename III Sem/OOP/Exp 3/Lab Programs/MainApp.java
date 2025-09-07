// MainApp.java
public class MainApp {
    public static void main(String[] args) {
        // Create an object of the Calculator class
        Calculator myCalculator = new Calculator();

        // Test SimpleCalculator methods
        double num1 = 10.5;
        double num2 = 5.2;

        System.out.println("--- Simple Calculator Operations ---");
        double sum = myCalculator.add(num1, num2);
        System.out.printf("%.1f + %.1f = %.1f%n", num1, num2, sum);

        double difference = myCalculator.sub(num1, num2);
        System.out.printf("%.1f - %.1f = %.1f%n", num1, num2, difference);

        System.out.println("\n--- Scientific Calculator Operations ---");
        // Test SciCalculator methods
        int factNum = 5;
        try {
            long factResult = myCalculator.factorial(factNum);
            System.out.println("Factorial of " + factNum + " = " + factResult);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int negFactNum = -3;
        try {
            long factResultNeg = myCalculator.factorial(negFactNum);
            System.out.println("Factorial of " + negFactNum + " = " + factResultNeg);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        double sqNum = 7.0;
        double sqResult = myCalculator.square(sqNum);
        System.out.println("Square of " + sqNum + " = " + sqResult);

        double sqNumFraction = 3.5;
        double sqResultFraction = myCalculator.square(sqNumFraction);
        System.out.println("Square of " + sqNumFraction + " = " + sqResultFraction);
    }
}