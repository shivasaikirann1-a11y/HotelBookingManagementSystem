import java.util.HashMap;

public class UseCase3InventorySetup {

    static class RoomInventory {

        private HashMap<String, Integer> inventory;

        RoomInventory() {
            inventory = new HashMap<>();
        }

        void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        void updateAvailability(String type, int newCount) {
            inventory.put(type, newCount);
        }

        void displayInventory() {
            System.out.println("Room Inventory:\n");

            for (String type : inventory.keySet()) {
                System.out.println(type + " Available: " + inventory.get(type));
            }
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 3);
        inventory.addRoom("Suite Room", 2);

        inventory.displayInventory();

        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        inventory.displayInventory();
    }
}