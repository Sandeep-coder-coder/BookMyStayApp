import java.util.*;

// THREAD CLASS
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {

        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {
            Reservation reservation;

            synchronized (bookingQueue) {
                if (!bookingQueue.hasPendingRequests()) break;
                reservation = bookingQueue.getNextRequest();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}


// MAIN CLASS
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        // FINAL OUTPUT FORMAT
        System.out.println("\nRemaining Inventory:");

        Map<String, Integer> a = inventory.getRoomAvailability();

        System.out.println("Single: " + a.get("Single Room"));
        System.out.println("Double: " + a.get("Double Room"));
        System.out.println("Suite: " + a.get("Suite Room"));
    }
}