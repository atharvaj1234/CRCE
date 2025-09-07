import java.util.Scanner;

public class GradeCalculator {

    public static char getGrade(double score) {
        if (score < 0 || score > 100) {
            System.out.println("Invalid score: Score must be between 0 and 100.");
            return 'X'; 
        } else if (score >= 91) {
            return 'A';
        } else if (score >= 81) { 
            return 'B';
        } else if (score >= 61) { 
            return 'C';
        } else if (score >= 41) { 
            return 'D';
        } else { 
            return 'F';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.println("--- Grade Calculator ---");
        System.out.print("Enter the percentage score: ");

        // Check if the user input is a valid number
        if (scanner.hasNextDouble()) {
            double userScore = scanner.nextDouble(); 

            char grade = getGrade(userScore); 

            if (grade != 'X') { // Only print the grade if it's valid
                System.out.println("The corresponding grade is: " + grade);
            }
        } else {
            System.out.println("Invalid input. Please enter a numeric score.");
        }

        scanner.close(); 
    }
}