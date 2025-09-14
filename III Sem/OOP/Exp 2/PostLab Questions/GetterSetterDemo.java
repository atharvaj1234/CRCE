// A simple class demonstrating encapsulation with getter and setter
class Employee {
    // Private fields (encapsulation)
    private String name;
    private int age;
    private double salary;

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        // Adding validation for better practice
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name!");
        }
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age
    public void setAge(int age) {
        if (age > 18) {  // validation
            this.age = age;
        } else {
            System.out.println("Age must be greater than 18.");
        }
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(double salary) {
        if (salary >= 0) {  // validation
            this.salary = salary;
        } else {
            System.out.println("Salary cannot be negative.");
        }
    }
}

// Main class
public class GetterSetterDemo {
    public static void main(String[] args) {
        Employee emp = new Employee();

        // Setting values using setters
        emp.setName("Alice");
        emp.setAge(25);
        emp.setSalary(50000);

        // Getting values using getters
        System.out.println("Employee Details:");
        System.out.println("Name   : " + emp.getName());
        System.out.println("Age    : " + emp.getAge());
        System.out.println("Salary : " + emp.getSalary());

        // Testing validations
        emp.setAge(15);         // Invalid age
        emp.setSalary(-2000);   // Invalid salary
        emp.setName("");        // Invalid name
    }
}
