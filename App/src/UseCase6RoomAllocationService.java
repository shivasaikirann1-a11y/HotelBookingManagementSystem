import java.util.*;

public class UseCase6RoomAllocationService {

    static class Reservation {
        String customerName;
        String roomType;

        Reservation(String customerName, String roomType) {
            this.customerName = customerName;
            this.roomType = roomType;
        }
    }

    static class RoomInventory {
        HashMap<String, Integer> inventory = new HashMap<>();

        void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        boolean isAvailable(String type) {
            return inventory.getOrDefault(type, 0) > 0;
        }

        void reduceRoom(String type) {
            inventory.put(type, inventory.get(type) - 1);
        }
    }

    static class BookingService {

        Queue<Reservation> queue = new LinkedList<>();
        HashSet<String> allocatedRooms = new HashSet<>();
        HashMap<String, Set<String>> roomAllocations = new HashMap<>();

        void addRequest(Reservation r) {
            queue.add(r);
        }

        void process(RoomInventory inventory) {

            int roomIdCounter = 1;

            while (!queue.isEmpty()) {

                Reservation r = queue.poll();

                if (inventory.isAvailable(r.roomType)) {

                    String roomId = r.roomType + "-" + roomIdCounter++;

                    if (!allocatedRooms.contains(roomId)) {

                        allocatedRooms.add(roomId);

                        roomAllocations.putIfAbsent(r.roomType, new HashSet<>());
                        roomAllocations.get(r.roomType).add(roomId);

                        inventory.reduceRoom(r.roomType);

                        System.out.println("Booking confirmed for " + r.customerName +
                                " | Room: " + r.roomType +
                                " | ID: " + roomId);
                    }
                } else {
                    System.out.println("No rooms available for " + r.customerName +
                            " (" + r.roomType + ")");
                }
            }
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);
        inventory.addRoom("Double Room", 1);

        BookingService service = new BookingService();

        service.addRequest(new Reservation("Sai", "Single Room"));
        service.addRequest(new Reservation("Kiran", "Single Room"));
        service.addRequest(new Reservation("Rahul", "Single Room"));
        service.addRequest(new Reservation("Amit", "Double Room"));

        service.process(inventory);
    }
}