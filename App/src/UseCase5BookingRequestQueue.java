import java.util.LinkedList;
import java.util.Queue;

public class UseCase5BookingRequestQueue {

    static class Reservation {
        String customerName;
        String roomType;

        Reservation(String customerName, String roomType) {
            this.customerName = customerName;
            this.roomType = roomType;
        }

        void display() {
            System.out.println("Customer: " + customerName + ", Room: " + roomType);
        }
    }

    static class BookingQueue {

        Queue<Reservation> queue = new LinkedList<>();

        void addRequest(Reservation r) {
            queue.add(r);
            System.out.println("Request added for " + r.customerName);
        }

        void showQueue() {
            System.out.println("\nBooking Queue:\n");

            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Sai", "Single Room"));
        bookingQueue.addRequest(new Reservation("Kiran", "Double Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Suite Room"));

        bookingQueue.showQueue();
    }
}