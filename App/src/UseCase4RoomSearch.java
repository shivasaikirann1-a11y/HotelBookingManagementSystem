import java.util.HashMap;

public class UseCase4RoomSearch {

    static class Room {
        String type;
        int price;
        int beds;

        Room(String type, int price, int beds) {
            this.type = type;
            this.price = price;
            this.beds = beds;
        }

        void display() {
            System.out.println("Room Type: " + type);
            System.out.println("Price: " + price);
            System.out.println("Beds: " + beds);
        }
    }

    static class RoomInventory {
        HashMap<String, Integer> inventory = new HashMap<>();

        void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }
    }

    static class SearchService {

        void searchRooms(Room[] rooms, RoomInventory inventory) {

            System.out.println("Available Rooms:\n");

            for (Room room : rooms) {

                int available = inventory.getAvailability(room.type);

                if (available > 0) {
                    room.display();
                    System.out.println("Available: " + available + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {

        Room r1 = new Room("Single Room", 1000, 1);
        Room r2 = new Room("Double Room", 2000, 2);
        Room r3 = new Room("Suite Room", 5000, 3);

        Room[] rooms = {r1, r2, r3};

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 0);
        inventory.addRoom("Suite Room", 2);

        SearchService search = new SearchService();
        search.searchRooms(rooms, inventory);
    }
}