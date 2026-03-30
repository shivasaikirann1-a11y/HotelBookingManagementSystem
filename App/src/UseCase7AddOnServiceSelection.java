import java.util.*;

public class UseCase7AddOnServiceSelection {

    static class Service {
        String name;
        int cost;

        Service(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    static class AddOnServiceManager {

        HashMap<String, List<Service>> serviceMap = new HashMap<>();

        void addService(String reservationId, Service service) {

            serviceMap.putIfAbsent(reservationId, new ArrayList<>());
            serviceMap.get(reservationId).add(service);

            System.out.println("Added " + service.name + " to " + reservationId);
        }

        void displayServices(String reservationId) {

            List<Service> services = serviceMap.get(reservationId);

            if (services == null) {
                System.out.println("No services for " + reservationId);
                return;
            }

            int total = 0;

            System.out.println("\nServices for " + reservationId + ":");

            for (Service s : services) {
                System.out.println(s.name + " - " + s.cost);
                total += s.cost;
            }

            System.out.println("Total Cost: " + total);
        }
    }

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String resId1 = "SingleRoom-1";

        manager.addService(resId1, new Service("Breakfast", 200));
        manager.addService(resId1, new Service("WiFi", 100));
        manager.addService(resId1, new Service("Parking", 150));

        manager.displayServices(resId1);
    }
}