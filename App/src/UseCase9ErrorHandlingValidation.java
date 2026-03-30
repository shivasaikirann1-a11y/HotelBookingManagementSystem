import java.util.*;

public class UseCase9ErrorHandlingValidation {

    static class InvalidBookingException extends Exception {
        InvalidBookingException(String message) {
            super(message);
        }
    }

    static class RoomInventory {

        HashMap<String, Integer> inventory = new HashMap<>();

        void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        void bookRoom(String type) throws InvalidBookingException {

            if (!inventory.containsKey(type)) {
                throw new InvalidBookingException("Invalid room type: " + type);
            }

            if (inventory.get(type) <= 0) {
                throw new InvalidBookingException("No rooms available for: " + type);
            }

            inventory.put(type, inventory.get(type) - 1);

            System.out.println("Booking successful for " + type);
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.addRoom("Single Room", 1);
        inventory.addRoom("Double Room", 0);

        try {

            inventory.bookRoom("Single Room");
            inventory.bookRoom("Double Room");
            inventory.bookRoom("Suite Room");

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}