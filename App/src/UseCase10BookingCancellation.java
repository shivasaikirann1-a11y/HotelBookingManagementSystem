import java.util.*;

public class UseCase10BookingCancellation {

    static class RoomInventory {

        HashMap<String, Integer> inventory = new HashMap<>();

        void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        void increaseRoom(String type) {
            inventory.put(type, inventory.get(type) + 1);
        }

        boolean isValidRoom(String type) {
            return inventory.containsKey(type);
        }
    }

    static class BookingService {

        Stack<String> bookedRooms = new Stack<>();
        HashMap<String, String> reservationMap = new HashMap<>();

        int idCounter = 1;

        String bookRoom(String customer, String type, RoomInventory inventory) {

            if (!inventory.isValidRoom(type) || inventory.inventory.get(type) <= 0) {
                System.out.println("Booking failed for " + customer);
                return null;
            }

            String reservationId = type + "-" + idCounter++;

            bookedRooms.push(reservationId);
            reservationMap.put(reservationId, type);

            inventory.inventory.put(type, inventory.inventory.get(type) - 1);

            System.out.println("Booked: " + reservationId + " for " + customer);

            return reservationId;
        }

        void cancelLastBooking(RoomInventory inventory) {

            if (bookedRooms.isEmpty()) {
                System.out.println("No bookings to cancel");
                return;
            }

            String lastBooking = bookedRooms.pop();
            String roomType = reservationMap.get(lastBooking);

            inventory.increaseRoom(roomType);

            System.out.println("Cancelled booking: " + lastBooking);
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.addRoom("Single Room", 2);
        inventory.addRoom("Double Room", 1);

        BookingService service = new BookingService();

        service.bookRoom("Sai", "Single Room", inventory);
        service.bookRoom("Kiran", "Double Room", inventory);
        service.bookRoom("Rahul", "Single Room", inventory);

        System.out.println("\nCancelling last booking...\n");

        service.cancelLastBooking(inventory);
        service.cancelLastBooking(inventory);
    }
}