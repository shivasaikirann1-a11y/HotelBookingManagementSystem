import java.util.*;

public class UseCase8BookingHistoryReport {

    static class Reservation {
        String customerName;
        String roomType;

        Reservation(String customerName, String roomType) {
            this.customerName = customerName;
            this.roomType = roomType;
        }
    }

    static class BookingHistory {

        List<Reservation> history = new ArrayList<>();

        void addReservation(Reservation r) {
            history.add(r);
        }

        void displayAll() {
            System.out.println("Booking History:\n");

            for (Reservation r : history) {
                System.out.println("Customer: " + r.customerName + ", Room: " + r.roomType);
            }
        }
    }

    static class ReportService {

        void generateReport(List<Reservation> history) {

            HashMap<String, Integer> report = new HashMap<>();

            for (Reservation r : history) {
                report.put(r.roomType, report.getOrDefault(r.roomType, 0) + 1);
            }

            System.out.println("\nBooking Report:\n");

            for (String type : report.keySet()) {
                System.out.println(type + " booked: " + report.get(type));
            }
        }
    }

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Sai", "Single Room"));
        history.addReservation(new Reservation("Kiran", "Double Room"));
        history.addReservation(new Reservation("Rahul", "Single Room"));
        history.addReservation(new Reservation("Amit", "Suite Room"));

        history.displayAll();

        ReportService report = new ReportService();
        report.generateReport(history.history);
    }
}