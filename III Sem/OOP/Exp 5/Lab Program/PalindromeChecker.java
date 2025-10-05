import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        // Step 1: Immutable String operations
        String processed = input.replaceAll("\\s+", "").toLowerCase(); // remove spaces and convert to lowercase
        String reversed = new StringBuilder(processed).reverse().toString(); // reverse using StringBuilder

        System.out.println("\n--- Using Immutable String ---");
        System.out.println("Processed String: " + processed);
        System.out.println("Reversed String: " + reversed);

        // equals()
        if (processed.equals(reversed))
            System.out.println("Palindrome check (equals): TRUE");
        else
            System.out.println("Palindrome check (equals): FALSE");

        // equalsIgnoreCase()
        if (processed.equalsIgnoreCase(reversed))
            System.out.println("Palindrome check (equalsIgnoreCase): TRUE");
        else
            System.out.println("Palindrome check (equalsIgnoreCase): FALSE");

        // compareTo()
        if (processed.compareTo(reversed) == 0)
            System.out.println("Palindrome check (compareTo): TRUE");
        else
            System.out.println("Palindrome check (compareTo): FALSE");

        // Step 2: Mutable StringBuilder
        StringBuilder sb = new StringBuilder(input);
        StringBuilder sbProcessed = new StringBuilder();

        // remove spaces and convert to lowercase manually
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c != ' ')
                sbProcessed.append(Character.toLowerCase(c));
        }

        StringBuilder sbReversed = new StringBuilder(sbProcessed).reverse();

        System.out.println("\n--- Using Mutable StringBuilder ---");
        System.out.println("Processed StringBuilder: " + sbProcessed);
        System.out.println("Reversed StringBuilder: " + sbReversed);

        // equals() and equalsIgnoreCase() don’t work directly for StringBuilder, so convert to String
        String str1 = sbProcessed.toString();
        String str2 = sbReversed.toString();

        System.out.println("Palindrome check (equals): " + str1.equals(str2));
        System.out.println("Palindrome check (equalsIgnoreCase): " + str1.equalsIgnoreCase(str2));
        System.out.println("Palindrome check (compareTo): " + (str1.compareTo(str2) == 0));

        sc.close();
    }
}
