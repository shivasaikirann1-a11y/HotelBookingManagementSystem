/**
 * Use Case 2: Basic Room Types & Static Availability
 */
public class UseCase2RoomInitialization {

    // Abstract class
    static abstract class Room {
        String type;
        int price;
        int beds;

        Room(String type, int price, int beds) {
            this.type = type;
            this.price = price;
            this.beds = beds;
        }

        void displayDetails() {
            System.out.println("Room Type: " + type);
            System.out.println("Price: " + price);
            System.out.println("Beds: " + beds);
        }
    }

    // Single Room
    static class SingleRoom extends Room {
        SingleRoom() {
            super("Single Room", 1000, 1);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        DoubleRoom() {
            super("Double Room", 2000, 2);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        SuiteRoom() {
            super("Suite Room", 5000, 3);
        }
    }

    public static void main(String[] args) {

        // Create objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("Room Details & Availability:\n");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}