// Base class (Parent)
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
        System.out.println("Animal Constructor called for: " + name);
    }

    void sound() {
        System.out.println(name + " makes a sound.");
    }
}

// ------------------- SINGLE INHERITANCE -------------------
class Dog extends Animal {
    Dog(String name) {
        super(name); // Call parent constructor
    }

    @Override
    void sound() {
        super.sound(); // Call parent method
        System.out.println(name + " barks.");
    }
}

// ------------------- MULTILEVEL INHERITANCE -------------------
class Puppy extends Dog {
    Puppy(String name) {
        super(name);
    }

    void behavior() {
        System.out.println(name + " is a cute puppy!");
    }
}

// ------------------- HIERARCHICAL INHERITANCE -------------------
class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    void sound() {
        super.sound(); // Call parent method
        System.out.println(name + " meows.");
    }
}

class Cow extends Animal {
    Cow(String name) {
        super(name);
    }

    @Override
    void sound() {
        super.sound();
        System.out.println(name + " moos.");
    }
}

// ------------------- HYBRID INHERITANCE -------------------
// Hybrid = Combination of multiple inheritance styles (Java doesn’t support multiple inheritance with classes, 
// but it can be achieved with interfaces)

// Interface for hybrid inheritance
interface Pet {
    void play();
}

// Dog is already extending Animal, now also implementing Pet
class PetDog extends Dog implements Pet {
    PetDog(String name) {
        super(name);
    }

    public void play() {
        System.out.println(name + " loves to play fetch!");
    }
}

// ------------------- MAIN DRIVER -------------------
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("\n--- Single Inheritance ---");
        Dog dog = new Dog("Buddy");
        dog.sound();

        System.out.println("\n--- Multilevel Inheritance ---");
        Puppy puppy = new Puppy("Max");
        puppy.sound();
        puppy.behavior();

        System.out.println("\n--- Hierarchical Inheritance ---");
        Cat cat = new Cat("Kitty");
        cat.sound();
        Cow cow = new Cow("Bessie");
        cow.sound();

        System.out.println("\n--- Hybrid Inheritance (with Interface) ---");
        PetDog petDog = new PetDog("Charlie");
        petDog.sound();
        petDog.play();
    }
}
