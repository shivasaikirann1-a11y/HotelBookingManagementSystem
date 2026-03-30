import java.io.*;
import java.util.*;

public class UseCase12DataPersistenceRecovery {

    static class Reservation implements Serializable {
        String customerName;
        String roomType;

        Reservation(String customerName, String roomType) {
            this.customerName = customerName;
            this.roomType = roomType;
        }
    }

    static class PersistenceService {

        void save(List<Reservation> data, String filename) {

            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
                out.writeObject(data);
                out.close();
                System.out.println("Data saved successfully");
            } catch (Exception e) {
                System.out.println("Error saving data");
            }
        }

        List<Reservation> load(String filename) {

            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
                List<Reservation> data = (List<Reservation>) in.readObject();
                in.close();
                return data;
            } catch (Exception e) {
                System.out.println("No previous data found");
                return new ArrayList<>();
            }
        }
    }

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        String file = "booking.dat";

        List<Reservation> history = service.load(file);

        history.add(new Reservation("Sai", "Single Room"));
        history.add(new Reservation("Kiran", "Double Room"));

        service.save(history, file);

        System.out.println("\nRecovered Data:\n");

        List<Reservation> recovered = service.load(file);

        for (Reservation r : recovered) {
            System.out.println(r.customerName + " - " + r.roomType);
        }
    }
}