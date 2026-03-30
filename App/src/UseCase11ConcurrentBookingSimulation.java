import java.util.*;

public class UseCase11ConcurrentBookingSimulation {

    static class RoomInventory {

        HashMap<String, Integer> inventory = new HashMap<>();

        RoomInventory() {
            inventory.put("Single Room", 2);
        }

        synchronized boolean bookRoom(String customer) {

            if (inventory.get("Single Room") > 0) {

                int count = inventory.get("Single Room");

                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

                inventory.put("Single Room", count - 1);

                System.out.println("Booked for " + customer);
                return true;
            }

            System.out.println("No room for " + customer);
            return false;
        }
    }

    static class BookingThread extends Thread {

        String customer;
        RoomInventory inventory;

        BookingThread(String customer, RoomInventory inventory) {
            this.customer = customer;
            this.inventory = inventory;
        }

        public void run() {
            inventory.bookRoom(customer);
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        BookingThread t1 = new BookingThread("Sai", inventory);
        BookingThread t2 = new BookingThread("Kiran", inventory);
        BookingThread t3 = new BookingThread("Rahul", inventory);

        t1.start();
        t2.start();
        t3.start();
    }
}