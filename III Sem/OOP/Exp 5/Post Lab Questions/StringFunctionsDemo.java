import java.util.*;

public class StringFunctionsDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println("\n===============================");
        System.out.println("   IMMUTABLE STRING OPERATIONS  ");
        System.out.println("===============================");

        // Original String
        String str = input;
        System.out.println("Original String: " + str);

        // String operations
        System.out.println("Length: " + str.length());
        System.out.println("Character at index 0: " + str.charAt(0));
        System.out.println("Substring (0,3): " + str.substring(0, Math.min(3, str.length())));
        System.out.println("To Uppercase: " + str.toUpperCase());
        System.out.println("To Lowercase: " + str.toLowerCase());
        System.out.println("Concatenation: " + str.concat(" Java"));
        System.out.println("Replace 'a' with '@': " + str.replace('a', '@'));
        System.out.println("Trimmed String: " + str.trim());
        System.out.println("Index of 'a': " + str.indexOf('a'));
        System.out.println("Last Index of 'a': " + str.lastIndexOf('a'));
        System.out.println("Starts with 'H': " + str.startsWith("H"));
        System.out.println("Ends with 'd': " + str.endsWith("d"));
        System.out.println("Equals to 'java': " + str.equals("java"));
        System.out.println("Equals Ignore Case to 'java': " + str.equalsIgnoreCase("java"));
        System.out.println("Compare To 'Java': " + str.compareTo("Java"));
        System.out.println("Contains 'a': " + str.contains("a"));
        System.out.println("Split words:");
        for (String word : str.split(" ")) {
            System.out.println(" - " + word);
        }

        System.out.println("\n===============================");
        System.out.println("   MUTABLE STRING OPERATIONS   ");
        System.out.println("===============================");

        // Using StringBuilder
        StringBuilder sb = new StringBuilder(input);
        System.out.println("Original StringBuilder: " + sb);

        sb.append(" Language");
        System.out.println("After append(): " + sb);

        sb.insert(0, "Welcome ");
        System.out.println("After insert(): " + sb);

        sb.replace(0, 7, "Hi");
        System.out.println("After replace(): " + sb);

        sb.delete(0, 3);
        System.out.println("After delete(): " + sb);

        sb.reverse();
        System.out.println("After reverse(): " + sb);

        sb.reverse(); // revert back to normal
        System.out.println("After reverse() again: " + sb);

        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());
        System.out.println("Character at index 2: " + sb.charAt(2));
        sb.setCharAt(2, '*');
        System.out.println("After setCharAt(2, '*'): " + sb);

        // Convert to String for comparison
        String sbString = sb.toString();
        System.out.println("Converted to String: " + sbString);
        System.out.println("Compare To (String version): " + sbString.compareTo(str));

        System.out.println("\n===============================");
        System.out.println("   USING STRINGBUFFER (Thread-Safe) ");
        System.out.println("===============================");

        StringBuffer sbf = new StringBuffer(input);
        System.out.println("Original StringBuffer: " + sbf);

        sbf.append(" Example");
        System.out.println("After append(): " + sbf);

        sbf.insert(0, "Safe ");
        System.out.println("After insert(): " + sbf);

        sbf.replace(0, 4, "Thread");
        System.out.println("After replace(): " + sbf);

        sbf.delete(0, 6);
        System.out.println("After delete(): " + sbf);

        sbf.reverse();
        System.out.println("After reverse(): " + sbf);

        sc.close();
    }
}
